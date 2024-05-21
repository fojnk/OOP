package org.example.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.models.GroovyConfig;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Info extends GroovyConfig {
    private List<Group> groups;
    private List<Student> studentsList;
    private List<Task> allTasks;
    private Settings settings;
}