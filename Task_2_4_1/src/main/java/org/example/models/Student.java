package org.example.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends GroovyConfig{
    private String name;
    private String username;
    private String repository;
    private List<Task> tasks;
}