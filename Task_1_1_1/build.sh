#!/bin/bash

javac ./src/main/java/org/example/Main.java ./src/main/java/org/example/Heapsort.java -d ./src/bin
java -cp .src/bin org.example.Main