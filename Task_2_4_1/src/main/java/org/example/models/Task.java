package org.example.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Task extends GroovyConfig{
    private String name;
    private String description;
    private Integer cost;
    private LocalDate softDeadline;
    private LocalDate hardDeadline;
}