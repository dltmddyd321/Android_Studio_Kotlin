package com.sycompany.bojstep;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        Set<Integer> cardSet = new HashSet<>();
        String[] cards = reader.readLine().split(" ");
        for (String card : cards) {
            cardSet.add(Integer.parseInt(card));
        }

        int m = Integer.parseInt(reader.readLine());
        String[] checkCards = reader.readLine().split(" ");
        StringBuilder result = new StringBuilder();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (String card : checkCards) {
            map.put(Integer.parseInt(card), map.getOrDefault(Integer.parseInt(card), 0) + 1);
        }
        for (int i = 0; i<= m - 1; i++) {
            result.append(map.getOrDefault(checkCards[i], 0));
        }
        System.out.println(result);
    }
    public static List<String> result = new ArrayList<>();
    public static List<Character> vowels = List.of('A', 'E', 'I', 'O', 'U');

    public static void check(String currentWord) {
        if (currentWord.length() <= 5) {
            if (!currentWord.isEmpty()) {
                result.add(currentWord);
            }
            for (Character vowel : vowels) {
                check(currentWord + vowel);
            }
        }
    }

    public static int solution(String word) {
        check("");
        return result.indexOf(word) + 1;
    }
}
