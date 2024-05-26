package org.example.services;

import lombok.SneakyThrows;

import java.io.File;

public class RepositoryCloner {
    @SneakyThrows
    static public int cloneRepo(String url, String dest, String branch) {
        File theDir = new File(dest);
        if (!theDir.exists()){
            if (!theDir.mkdirs()) {
                return -1;
            }
        }
        ProcessBuilder pb = new ProcessBuilder()
                .command("git", "clone", "-b", branch, url)
                .directory(theDir);
        var p = pb.start();
        int exit = p.waitFor();
        return exit;
    }
}