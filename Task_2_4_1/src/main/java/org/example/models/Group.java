package org.example.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.models.Student;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Group extends GroovyConfig{
    public String number;
    private List<Student> students;
}