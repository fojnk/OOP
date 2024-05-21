package org.example.models;

import lombok.Data;

@Data
public class TaskResult {
    Boolean build = false;
    Boolean javadoc = false;
    Boolean checkstyle = false;
    Boolean softDeadline = false;
    Boolean hardDeadline = false;
    Integer amountOfTests = 0;
    Integer passedTests = 0;
    Integer failedTests = 0;
    Double mark = (double) 0;
}