package org.example.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Settings extends GroovyConfig {
    private String branch;
}