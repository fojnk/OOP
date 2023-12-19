package org.example;

import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * Парсер командной строки.
 */
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

    /**
     * конструктор класса.
     */
    public CommandLineParser() {
        parser = new CmdLineParser(this);
    }

    /**
     * Парсин команды.
     *
     * @param args - командка
     * @return - аргументы команды
     * @throws CmdLineException - исключение командной строки
     */
    public List<String> parseCommand(String[] args) throws CmdLineException {
        parser.parseArgument(args);
        return arguments;
    }

    /**
     * Проверить добавление.
     *
     * @return - true of false
     */
    public boolean checkAdd() {
        return add;
    }

    /**
     * Проверить удаление.
     *
     * @return - true of false
     */
    public boolean checkRm() {
        return rm;
    }

    /**
     * Проверить show.
     *
     * @return - true of false
     */
    public boolean checkShow() {
        return show;
    }

    /**
     * Проверить help.
     *
     * @return - true of false
     */
    public boolean checkHelp() {
        return help;
    }
}