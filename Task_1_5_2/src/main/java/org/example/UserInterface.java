package org.example;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    private File jsonFile;
    private Notebook notebook;

    @Option(name="-add", usage="add a new note with the specified title and description")
    private boolean add = false;

    @Option(name="-show", usage="show all notes sorted by date")
    private boolean show = false;

    @Option(name="-rm", usage="remove note by title")
    private boolean rm = false;

    @Option(name="--help", usage="show all commands")
    private boolean help = false;

    @Argument
    private List<String> arguments = new ArrayList<String>();

    public UserInterface(String filename) throws IOException {
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

    public void ParseCommand(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if( arguments.isEmpty() ) {
                System.out.println("No argument is given");
                return;
            }
        } catch( Exception e ) {
            NotebookUsage();
            return;
        }

        if (add) {
            addOp();
        } else if (rm) {
            rmOp();
        } else if (show) {
            showOp();
        } else if (help) {
            Help();
        } else {
            System.out.println("Unknown operation");
            NotebookUsage();
            return;
        }
        try {
            Json.writeNotes(jsonFile, notebook.getAllNotes());
        } catch (IOException e) {
            System.out.println("Write notes failed");
        }
    }

    private void addOp() {
        if (arguments.isEmpty() || arguments.size() > 2) {
            NotebookUsage();
            return;
        }

        String description;
        if (arguments.size() == 1) {
            notebook.addNote(arguments.get(0), "");
        }
        else {
            notebook.addNote(arguments.get(0), arguments.get(1));
        }
    }

    private void rmOp() {
        if (arguments.size() != 1) {
            NotebookUsage();
            return;
        }
        notebook.removeNotes(arguments.get(0));
    }

    private void showOp() {
        for (var note: notebook.getAllNotes()) {
            System.out.println(note.getNoteDate() + note.getTitle() + note.getDescription());
        }
    }

    private void NotebookUsage() {
        System.out.println("notebook [command] args");
        Help();
    }

    private void Help() {
        System.out.println("All commands:\n-add: a new note with the specified title and description");
        System.out.println("-show: show all notes sorted by date");
        System.out.println("-rm: remove notes by title");
        System.out.println("--help: show all commands");
    }
}