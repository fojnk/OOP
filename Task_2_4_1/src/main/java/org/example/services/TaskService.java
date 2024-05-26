package org.example.services;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.example.models.TaskResult;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.puppycrawl.tools.checkstyle.Main;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class TaskService {
    static public boolean runTask(String taskPath, String taskName) {
        var result = true;

        if (!taskName.equals("javadoc") && !taskName.equals("test")) return false;

        try (ProjectConnection connection = GradleConnector.newConnector()
                .forProjectDirectory(new File(taskPath))
                .connect()) {
            connection.newBuild().forTasks(taskName).run();
        } catch (RuntimeException e) {
            result = false;
        }
        return result;
    }

    static public boolean checkstyle(String storage, String taskName) throws Exception {

        int status = SystemLambda.catchSystemExit(() -> {
            var config = storage + "/.github/google_checks.xml";
            Main.main("-c", config, "-o", storage + "result.txt", storage + "/" + taskName + "/src/main/java");
        });

        if (status != 0) return false;

        BufferedReader reader = new BufferedReader(new FileReader(storage + "result.txt"));
        int counter = 0;
        while(reader.ready()) {
            if (reader.readLine().contains("[WARN]")) {
                counter++;
            }
        }
        reader.close();
        return counter <= 10;
    }

    static public TaskResult analyzeTestResults(String pathToTask) {
        var res = new TaskResult();
        try {
            File testDir = new File(pathToTask + "/build/test-results/test/");
            String pathToXML = "";
            for (var file : Objects.requireNonNull(testDir.listFiles())) {
                var filename = file.getName();
                if (filename.endsWith(".xml")) {
                    pathToXML = pathToTask + "/build/test-results/test/" + filename;
                    break;
                }
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(pathToXML));
            doc.getDocumentElement().normalize();
            var nList = doc.getElementsByTagName("testsuite").item(0).getAttributes();
            var passed = Integer.valueOf(nList.getNamedItem("tests").getNodeValue());
            var skipped = Integer.valueOf(nList.getNamedItem("skipped").getNodeValue());
            var total = Integer.parseInt(nList.getNamedItem("failures").getNodeValue()) + passed + skipped;
            res.setAmountOfTests(total);
            res.setPassedTests(passed);
            res.setSkippedTests(skipped);
        } catch (IOException | SAXException | ParserConfigurationException | NullPointerException ignore) {
        }

        return res;
    }

}