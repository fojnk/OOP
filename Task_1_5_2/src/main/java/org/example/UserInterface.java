package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.w3c.dom.Node;

import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.example.Json.fromJson;

public class UserInterface {
    private File jsonFile;
    private Notebook notebook;
    private Json JsonModule;

    private  ObjectMapper mapper;

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
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        var ref = new TypeReference<List<Note>>() {};
        try {
            List<Note> nodes = mapper.readValue(jsonFile, ref);
            this.notebook = new Notebook(nodes);
        } catch (IOException e) {
            System.out.println("Read json failed");
        }
    }

    public void ParseCommand(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if( arguments.isEmpty() )
                System.out.println("No argument is given");


        } catch( Exception e ) {
            NotebookUsage();
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
        }
        this.mapper.writeValue(jsonFile, notebook.getAllNotes());
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
}