package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ApostolicoCrochemoreAlgorithm {

    public static List<Integer> find(String filename, String substring) {
        if (substring.isEmpty()) { return null; }

        List<Integer> answer = new LinkedList<>();
        int l = 1;
        while (l < substring.length() && substring.charAt(l - 1) == substring.charAt(l))
        { l ++; }
        if (l == substring.length()) { l = 0; }

        int[] borderArray = new int[substring.length() + 1];
        getBorderArray(substring, borderArray);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int off = 0;
            while ((line = br.readLine()) != null) {
                ApostolicoCrochemoreAlg(substring, line, answer, borderArray, l, off);
                off += line.length() + 1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }

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
            }
            else {
                borderArray[i] = j;
            }
        }
    }

    private static void ApostolicoCrochemoreAlg(String substring, String text, List<Integer> answer, int[] borderArray
            , int l, int off) {

        int i = l;
        int j = 0;
        int k = 0;

        while (j <= text.length() - substring.length()) {
            while (i < substring.length() && substring.charAt(i) == text.charAt(j + i)) {
                i ++;
            }
            if (i >= substring.length()) {
                while (k < l && substring.charAt(k) == text.charAt(k + j)) {
                    k ++;
                }
                if (k >= l) {
                    answer.add(j + off);
                }
            }
            j += i - borderArray[i];
            if (i == l) {
                k = Math.max(0, k - 1);
            }
            else if (borderArray[i] <= l) {
                k = Math.max(0, borderArray[i]);
                i = l;
            }
            else {
                k = l;
                i = borderArray[i];
            }
        }
    }
}