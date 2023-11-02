#!/bin/bash

javac ./src/main/java/org/example/Main.java ./src/main/java/org/example/Heapsort.java -d ./mybuild/bin/
javadoc -d ./mybuild/javadoc/ ./src/main/java/org/example/Main.java ./src/main/java/org/example/Heapsort.java
java -classpath ./mybuild/bin/ org.example.Main