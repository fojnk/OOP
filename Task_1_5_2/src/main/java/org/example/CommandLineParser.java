package org.example;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {
    private CmdLineParser parser;
    @Option(name = "-add", usage = "add a new note with the specified title and description")
    private boolean add = false;

    @Option(name = "-show", usage = "show all notes sorted by date")
    private boolean show = false;

    @Option(name = "-rm", usage = "remove note by title")
    private boolean rm = false;

    @Option(name = "-help", usage = "show all commands")
    private boolean help = false;

    @Argument
    private List<String> arguments = new ArrayList<String>();

    public CommandLineParser() {
        parser = new CmdLineParser(this);
    }

    public List<String> parseCommand(String[] args) throws CmdLineException {
        parser.parseArgument(args);
        return arguments;
    }

    public boolean checkAdd() {
        return add;
    }

    public boolean checkRm() {
        return rm;
    }

    public boolean checkShow() {
        return show;
    }

    public boolean checkHelp() {
        return help;
    }
}