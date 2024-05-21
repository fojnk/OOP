package org.example;

import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import lombok.SneakyThrows;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.example.models.Group;
import org.example.models.Info;
import org.example.models.Student;
import org.example.models.Task;
import org.example.services.RepositoryCloner;
import org.example.services.TaskService;

import java.awt.*;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main {

    private static String basePath = "/Users/vovapetrov/Desktop/";

    @SneakyThrows
    public static void main(String[] args) throws URISyntaxException {
        Info info = new Info();
        URI configPath = Objects.requireNonNull(Main.class.getClassLoader()
                .getResource("config.groovy")).toURI();
        info.runFrom(configPath);
        info.postProcess();
        for (Group group : info.getGroups()) {
            System.out.println(group.getNumber());
            for (Student student : group.getStudents()) {
                System.out.println(student.getUsername() + " (" + student.getName() +")");
                String path = basePath + group.getNumber() + "/" + student.getUsername();
                RepositoryCloner.cloneRepo(student.getRepository(), path, info.getSettings().getBranch());
                for (var task : info.getAllTasks()) {
                    var result1 = TaskService.runTask( path + "/OOP/" + task.getName(), "test");
                    var result2 = TaskService.runTask( path + "/OOP/" + task.getName(), "javadoc");
                    var res = TaskService.analyzeTestResults(path + "/OOP/" + task.getName());
                    System.out.println(task.getName() + ": " + " " + result1 + " " + result2 +
                            " " + res.getAmountOfTests() + " " + res.getPassedTests() + " " + res.getFailedTests());
                }
            }
        }

    }
}
