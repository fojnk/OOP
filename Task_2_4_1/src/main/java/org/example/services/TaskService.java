package org.example.services;

import lombok.SneakyThrows;
import org.example.models.Student;
import org.example.models.Task;
import org.example.models.TaskResult;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
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

    static public boolean checkstyle(Task task) {
        return true;
    }

    static public boolean checkDeadlines(Task task) {
        return true;
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
            var failures = Integer.valueOf(nList.getNamedItem("failures").getNodeValue());
            var total = Integer.parseInt(nList.getNamedItem("skipped").getNodeValue()) + passed + failures;
            res.setAmountOfTests(total);
            res.setPassedTests(passed);
            res.setFailedTests(failures);
        } catch (IOException | SAXException | ParserConfigurationException | NullPointerException ignore) {
        }

        return res;
    }

}