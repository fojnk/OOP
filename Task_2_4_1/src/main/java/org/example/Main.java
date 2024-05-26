package org.example;

import lombok.SneakyThrows;
import org.example.models.*;
import org.example.services.Render;
import org.example.services.RepositoryCloner;
import org.example.services.TaskService;

import java.net.URI;
import java.util.HashMap;
import java.util.Objects;

public class Main {

    private static String basePath = "/Users/vovapetrov/Desktop/";
    private static HashMap<Student, HashMap<Task, TaskResult>> results;

    @SneakyThrows
    public static void main(String[] args) throws Exception {
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
                var status = RepositoryCloner.cloneRepo(student.getRepository(), path, info.getSettings().getBranch());
                if (status != 0 && status != 128) continue;
                for (var task : info.getAllTasks()) {
                    var taskPath = path + "/OOP/" + task.getName();
                    var result1 = TaskService.runTask( taskPath, "test");
                    var result2 = TaskService.runTask( taskPath, "javadoc");
                    var result3 = TaskService.checkstyle(path + "/OOP/", task.getName());
                    var res = TaskService.analyzeTestResults(taskPath);
                    res.setBuild(result1);
                    res.setJavadoc(result2);
                    res.setCheckstyle(result3);
                    if (result1 && result2 && (res.getAmountOfTests() == res.getPassedTests())) {
                        if (result3) {
                            res.setMark(1.0);
                        } else {
                            res.setMark(0.5);
                        }

                    }
                    System.out.println(task.getName() + ": " + " " + result1 + " " + result2 + " " + result3 +
                            " " + res.getAmountOfTests() + " " + res.getPassedTests() + " " + res.getSkippedTests());
                    results.get(student).put(task, res);
                }
            }
        }

        Render.render(info.getAllTasks(), results);

    }
}
