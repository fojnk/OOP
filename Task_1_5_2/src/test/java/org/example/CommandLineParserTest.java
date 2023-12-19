package org.example;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.kohsuke.args4j.CmdLineException;

/**
 * Класс для проверки парсинга командной строки.
 */
public class CommandLineParserTest {

    /**
     * Парсинг тесты.
     *
     * @param cmd  - команда
     * @param add  - ожидаемое значение
     * @param rm   - ожидаемое значение
     * @param show - ожидаемое значение
     * @param help - ожидаемое значение
     * @throws CmdLineException - исключение командной строки
     */
    @ParameterizedTest
    @MethodSource("generateDataForParsing")
    public void parsingTests(String[] cmd, boolean add, boolean rm,
                             boolean show, boolean help) throws CmdLineException {
        var cmdp = new CommandLineParser();
        cmdp.parseCommand(cmd);
        Assertions.assertEquals(add, cmdp.checkAdd());
        Assertions.assertEquals(rm, cmdp.checkRm());
        Assertions.assertEquals(show, cmdp.checkShow());
        Assertions.assertEquals(help, cmdp.checkHelp());
    }

    /**
     * Генерация данных для тестов.
     *
     * @return - агрументы вида(команда, ожидаемые значения...)
     */
    static Stream<Arguments> generateDataForParsing() {
        return Stream.of(
                Arguments.arguments(new String[]{"-add"}, true, false, false, false),
                Arguments.arguments(new String[]{"-rm"}, false, true, false, false),
                Arguments.arguments(new String[]{"-show"}, false, false, true, false),
                Arguments.arguments(new String[]{"-help"}, false, false, false, true),
                Arguments.arguments(new String[]{"-help", "-add", "-rm", "-show"}, true, true, true, true),
                Arguments.arguments(new String[]{"-add", "-rm"}, true, true, false, false)
        );
    }

}