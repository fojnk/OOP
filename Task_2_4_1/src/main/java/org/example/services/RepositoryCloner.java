package org.example.services;

import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RepositoryCloner {
    @SneakyThrows
    static public boolean cloneRepo(String url, String dest, String branch) {
        File theDir = new File(dest);
        if (!theDir.exists()){
            if (!theDir.mkdirs()) {
                return false;
            }
        }
        ProcessBuilder pb = new ProcessBuilder()
                .command("git", "clone", "-b", branch, url)
                .directory(theDir);
        var p = pb.start();
        int exit = p.waitFor();
        return exit == 0;
    }
}