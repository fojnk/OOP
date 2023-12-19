package org.example;

import org.kohsuke.args4j.CmdLineException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Класс, в которым реализовано взаиможействие с пользователем.
 */
public class NotebookApp {
    private File jsonFile;
    private Notebook notebook;
    private DateTimeFormatter formatter;
    private CommandLineParser cmdParser;
    private List<String> arguments;

    /**
     * Создание интерфейса.
     *
     * @param filename - имя файла с заметками
     */
    public NotebookApp(String filename) {
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        getFile(filename);
        this.cmdParser = new CommandLineParser();
    }

    /**
     * конструктор для создания приложения со своим форматированмием.
     *
     * @param filename  - путь до файла
     * @param formatter - форматирвоание
     */
    public NotebookApp(String filename, DateTimeFormatter formatter) {
        this.formatter = formatter;
        getFile(filename);
        this.cmdParser = new CommandLineParser();
    }

    /**
     * метод для получения файла по имени.
     *
     * @param filename - имя файла
     */
    private void getFile(String filename) {
        this.jsonFile = new File(filename);
        if (!jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
                PrintWriter writer = new PrintWriter(jsonFile);
                writer.write("[]");
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * метод для исполенния команд.
     *
     * @param args - аргументы командной строки
     */
    public void execCommand(String[] args) {
        try {
            this.notebook = new Notebook(Json.readNotes(jsonFile), formatter);
        } catch (IOException e) {
            System.out.println("Read json failed");
            return;
        }

        try {
            arguments = cmdParser.parseCommand(args);
        } catch (CmdLineException e) {
            System.out.println("Invalid input");
            return;
        }

        if (cmdParser.checkAdd()) {
            addOp();
        } else if (cmdParser.checkRm()) {
            rmOp();
        } else if (cmdParser.checkShow()) {
            showOp();
        } else if (cmdParser.checkHelp()) {
            help();
        } else {
            System.out.println("Unknown operation");
            notebookUsage();
            return;
        }

        try {
            Json.writeNotes(jsonFile, notebook.getAllNotes());
        } catch (IOException e) {
            System.out.println("Write notes failed");
        }
    }

    /**
     * операция добавления заметки.
     */
    private void addOp() {
        if (arguments.isEmpty() || arguments.size() > 2) {
            notebookUsage();
            return;
        }

        if (arguments.size() == 1) {
            notebook.addNote(arguments.get(0), "");
        } else {
            notebook.addNote(arguments.get(0), arguments.get(1));
        }
    }

    /**
     * удаление заметки.
     */
    private void rmOp() {
        if (arguments.size() != 1) {
            notebookUsage();
            return;
        }
        notebook.removeNotes(arguments.get(0));
    }

    /**
     * просмотр заметок.
     */
    private void showOp() {
        var list = notebook.showNotes(arguments);
        for (var note : list) {
            System.out.println("____________________________________");
            System.out.println(note.getTitle() + "  |  " + note.getNoteDate());
            System.out.println("^^^^^^^^^^^ Description ^^^^^^^^^^^");
            System.out.println(note.getDescription());
            System.out.println("____________________________________");
        }
        //new WindowNotebookApp(list);
    }

    /**
     * небольшая инструкция.
     */
    private void notebookUsage() {
        System.out.println("notebook [command] args");
        help();
    }

    /**
     * тоже небольшая инструкция.
     */
    private void help() {
        System.out.println("All commands:\n-add: a new note with the specified title and description");
        System.out.println("-show: show all notes sorted by date");
        System.out.println("-rm: remove notes by title");
        System.out.println("--help: show all commands");
    }
}