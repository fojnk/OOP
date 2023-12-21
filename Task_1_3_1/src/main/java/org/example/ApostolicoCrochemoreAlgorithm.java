package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * класс для алгоритма поиска подстрок.
 */
public class ApostolicoCrochemoreAlgorithm {
    private static int MIN_BUF_SIZE = 2048;

    /**
     * метод, который находит все вхождения подстроки.
     *
     * @param filename  - имя файла
     * @param substring - подстрока
     * @param charset   - кодировка
     * @return - список индексов
     */
    public static List<Integer> find(String filename, String substring, Charset charset) {
        if (substring.isEmpty()) {
            return null;
        }
        if (MIN_BUF_SIZE < substring.length()) {
            MIN_BUF_SIZE = substring.length() + 1024;
        }

        List<Integer> answer = new LinkedList<>();
        // data for algorithm
        int l = 1;
        while (l < substring.length() && substring.charAt(l - 1) == substring.charAt(l)) {
            l++;
        }
        if (l == substring.length()) {
            l = 0;
        }
        int[] borderArray = new int[substring.length() + 1];
        getBorderArray(substring, borderArray);

        //buffer
        char[] buffer = new char[MIN_BUF_SIZE];
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (
                InputStream inputStream = classloader.getResourceAsStream(filename);
                InputStreamReader streamReader = new InputStreamReader(inputStream, charset);
                BufferedReader br = new BufferedReader(streamReader);
        ) {
            String text = null;
            int off = 0;
            StringBuilder helper = new StringBuilder();
            while (br.read(buffer, 0, MIN_BUF_SIZE) != -1) {
                if (off != 0) {
                    helper.delete(0, helper.length());
                    for (int i = text.length() - substring.length() + 1; i < text.length(); i++) {
                        helper.append(text.charAt(i));
                    }
                }
                helper.append(new String(buffer));
                text = helper.toString();
                ApostolicoCrochemoreAlg(substring, text, answer, borderArray, l, off);
                off += text.length() - substring.length() + 1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }

    /**
     * предпосчет сдвигов в подстроке.
     *
     * @param s           - подстрока
     * @param borderArray - сдвиги
     */
    private static void getBorderArray(String s, int[] borderArray) {
        int i = 0;
        int j = borderArray[0] = -1;
        while (i < s.length()) {
            while (j > -1 && s.charAt(i) != s.charAt(j)) {
                j = borderArray[j];
            }
            i++;
            j++;
            if (i < s.length() && s.charAt(i) == s.charAt(j)) {
                borderArray[i] = borderArray[j];
            } else {
                borderArray[i] = j;
            }
        }
    }

    /**
     * алгорим поиска подстроки в строке.
     *
     * @param substring   - подстрока
     * @param text        - текст
     * @param answer      - список, в который будут добавляться ответы
     * @param borderArray - список смещений в подстроке
     * @param l           - макс смещение относительно начала подстроки
     * @param off         - смещение в файле
     */
    private static void ApostolicoCrochemoreAlg(String substring, String text, List<Integer> answer, int[] borderArray
            , int l, int off) {

        int i = l;
        int j = 0;
        int k = 0;

        while (j <= text.length() - substring.length()) {
            while (i < substring.length() && substring.charAt(i) == text.charAt(j + i)) {
                i++;
            }
            if (i >= substring.length()) {
                while (k < l && substring.charAt(k) == text.charAt(k + j)) {
                    k++;
                }
                if (k >= l) {
                    answer.add(j + off);
                }
            }
            j += i - borderArray[i];
            if (i == l) {
                k = Math.max(0, k - 1);
            } else if (borderArray[i] <= l) {
                k = Math.max(0, borderArray[i]);
                i = l;
            } else {
                k = l;
                i = borderArray[i];
            }
        }
    }
}