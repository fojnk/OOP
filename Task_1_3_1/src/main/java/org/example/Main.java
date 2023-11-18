package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import static org.example.ApostolicoCrochemoreAlgorithm.find;

/**
 * главный метод.
 */
public class Main {
    /**
     * вход в программу.
     *
     * @param args - аргументы командной строки
     * @throws IOException - исключение ввода/вывода
     */
    public static void main(String[] args) throws IOException {
        System.out.println(find("test.txt", "бра", StandardCharsets.UTF_8));
        // больше 1Гб пока не получилось сделать
        long lenght = 1024L * 1024 * 1024;
        generateBigFile("hello", lenght);
        System.out.println(find("bigFile.txt", "hello", StandardCharsets.UTF_8));
    }

    /**
     * метод для генерации большого файла.
     *
     * @param lastSubstring - строка, которая будет в конце файла
     * @param length        - размер файла
     * @throws IOException - исключение ввода/вывода
     */
    public static void generateBigFile(String lastSubstring, long length) throws IOException {
        MappedByteBuffer out = new RandomAccessFile("./src/main/resources/bigFile.txt", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length + lastSubstring.length());
        for (long i = 0; i < length; i++)
            out.put((byte) 'x');
        for (long i = length; i < length + lastSubstring.length(); i++) {
            out.put((byte) lastSubstring.charAt((int) (i - length)));
        }
    }
}