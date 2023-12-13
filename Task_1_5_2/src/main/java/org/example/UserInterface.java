package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * Класс, в которым реализовано взаиможействие с пользователем.
 */
public class UserInterface {
    private File jsonFile;
    private Notebook notebook;

    private DateTimeFormatter formatter;

    @Option(name = "-add", usage = "add a new note with the specified title and description")
    private boolean add = false;

    @Option(name = "-show", usage = "show all notes sorted by date")
    private boolean show = false;

    @Option(name = "-rm", usage = "remove note by title")
    private boolean rm = false;

    @Option(name = "--help", usage = "show all commands")
    private boolean help = false;

    @Argument
    private List<String> arguments = new ArrayList<String>();

    /**
     * Создание интерфейса.
     *
     * @param filename - имя файла с заметками
     * @throws IOException - исключение
     */
    public UserInterface(String filename) throws IOException {
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
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
        try {
            this.notebook = new Notebook(Json.readNotes(jsonFile));
        } catch (IOException e) {
            System.out.println("Read json failed");
        }
    }

    /**
     * метод для парсинга командной строки.
     *
     * @param args - аргументы командной строки
     */
    private void parseCommand(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if (arguments.isEmpty()) {
                System.out.println("No arguments");
                return;
            }
        } catch (Exception e) {
            notebookUsage();
            return;
        }
    }

    /**
     * метод для исполенния команд.
     *
     * @param args - аргументы командной строки
     */
    public void execCommand(String[] args) {
        this.parseCommand(args);

        if (add) {
            addOp();
        } else if (rm) {
            rmOp();
        } else if (show) {
            showOp();
        } else if (help) {
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

        String description;
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
        if (arguments.size() >= 3) {
            var start = LocalDateTime.parse(arguments.get(0), formatter);
            var end = LocalDateTime.parse(arguments.get(1), formatter);
            ;
            for (var note : notebook.getAllNotes()) {
                var time = LocalDateTime.parse(note.getNoteDate(), formatter);
                if (time.isAfter(start) && time.isBefore(end)) {
                    for (int i = 2; i < arguments.size(); i++) {
                        if (note.getTitle().contains(arguments.get(i))) {
                            System.out.println(note.getNoteDate() + " "
                                    + note.getTitle() + " " + note.getDescription());
                            break;
                        }
                    }
                }
            }
        } else if (arguments.isEmpty()) {
            for (var note : notebook.getAllNotes()) {
                System.out.println(note.getNoteDate() + " " + note.getTitle()
                        + " " + note.getDescription());
            }
        }
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