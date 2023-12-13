package org.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        System.out.println(LocalDateTime.now().format(formatter));
        UserInterface userin = new UserInterface("notebook.json");
        userin.ExecCommand(new String[] {"-show", "my title", "hshahfdlasj"});
    }
}