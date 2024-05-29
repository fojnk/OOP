package org.example;

import org.example.models.Info;
import org.example.services.RepositoryCloner;
import org.example.services.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class MainTest {
    @Test
    public void ServiceTest() throws Exception {
        var basePath = "/Users/vovapetrov/Desktop/fojnk/";
        var respository = "https://github.com/fojnk/OOP.git";
        int status = RepositoryCloner.cloneRepo(respository, basePath, "main");
        if (status == 0 || status == 128) {
            var result1 = TaskService.runTask(basePath + "OOP/" + "Task_1_1_1/", "test");
            var result2 = TaskService.runTask(basePath + "OOP/" + "Task_1_1_1/", "javadoc");
            var result3 = TaskService.checkstyle(basePath + "OOP/", "Task_1_1_1/");
            var res = TaskService.analyzeTestResults(basePath + "OOP/" + "Task_1_1_1/");
            Assertions.assertTrue(result1);
            Assertions.assertTrue(result2);
            Assertions.assertTrue(result3);
            Assertions.assertEquals(res.getAmountOfTests(), 4);
            Assertions.assertEquals(res.getPassedTests(), 4);
            Assertions.assertEquals(res.getSkippedTests(), 0);
        }
    }

    @Test
    public void DSLTest() throws URISyntaxException {
        Info info = new Info();
        URI configPath = Objects.requireNonNull(Main.class.getClassLoader()
                .getResource("config.groovy")).toURI();
        info.runFrom(configPath);
        info.postProcess();
        var group = info.getGroups().get(0);
        Assertions.assertEquals(group.getNumber(), "22216");
        var student = group.getStudents().get(0);
        Assertions.assertEquals(student.getUsername(), "fojnk");
        var settings = info.getSettings();
        Assertions.assertEquals(settings.getBranch(), "main");
        var task = info.getAllTasks().get(0);
        Assertions.assertEquals(task.getName(), "Task_1_1_1");
    }
}