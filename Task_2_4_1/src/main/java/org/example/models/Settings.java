package org.example.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.models.GroovyConfig;

@EqualsAndHashCode(callSuper = true)
@Data
public class Settings extends GroovyConfig {
    private String branch;
}