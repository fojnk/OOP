package org.example.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RepositoryCloner {
    static public boolean cloneRepo(String url, String dest, String branch) throws IOException, InterruptedException {
        File theDir = new File(dest);
        if (!theDir.exists()){
            System.out.println("Creating dir...");
            if (!theDir.mkdirs()) {
                return false;
            }
        }
        System.out.println("Start process");
        ProcessBuilder pb = new ProcessBuilder()
                .command("git", "clone", "-b", branch, url)
                .directory(theDir);
        System.out.println("Clonning repository " + url + " to " + dest);
        var p = pb.start();
        int exit = p.waitFor();
        System.out.println(exit);
        return exit == 0;
    }
}