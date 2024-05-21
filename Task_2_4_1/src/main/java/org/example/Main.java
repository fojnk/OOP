package org.example;

import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import lombok.SneakyThrows;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.example.models.*;
import org.example.services.Render;
import org.example.services.RepositoryCloner;
import org.example.services.TaskService;

import java.awt.*;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Objects;

public class Main {

    private static String basePath = "/Users/vovapetrov/Desktop/";
    private static HashMap<Student, HashMap<Task, TaskResult>> results;

    @SneakyThrows
    public static void main(String[] args) throws URISyntaxException {
        results = new HashMap<>();
        Info info = new Info();
        URI configPath = Objects.requireNonNull(Main.class.getClassLoader()
                .getResource("config.groovy")).toURI();
        info.runFrom(configPath);
        info.postProcess();

        for (Group group : info.getGroups()) {
            System.out.println(group.getNumber());
            for (Student student : group.getStudents()) {
                results.put(student, new HashMap<>());
                System.out.println(student.getUsername() + " (" + student.getName() +")");
                String path = basePath + group.getNumber() + "/" + student.getUsername();
                RepositoryCloner.cloneRepo(student.getRepository(), path, info.getSettings().getBranch());
                for (var task : info.getAllTasks()) {
                    var taskPath = path + "/OOP/" + task.getName();
                    var result1 = TaskService.runTask( taskPath, "test");
                    var result2 = TaskService.runTask( taskPath, "javadoc");
                    var res = TaskService.analyzeTestResults(taskPath);
                    System.out.println(task.getName() + ": " + " " + result1 + " " + result2 +
                            " " + res.getAmountOfTests() + " " + res.getPassedTests() + " " + res.getFailedTests());
                    results.get(student).put(task, res);
                }
            }
        }

        Render.render(info.getAllTasks(), results);

    }
}
