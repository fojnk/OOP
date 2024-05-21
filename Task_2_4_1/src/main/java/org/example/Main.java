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
            System.out.println(group.number);
            for (Student student : group.getStudents()) {
                System.out.println(student.getRepository());
                System.out.println(RepositoryCloner.cloneRepo(student.getRepository(), basePath + student.name, "main"));
                for (Task task : student.getTasks()) {
                    System.out.println(task.name);
                }
            }
        }

    }
}
