package com.sycompany.bojstep;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Test {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minQue = new PriorityQueue<>();

        for (String operation : operations) {
            String[] op = operation.split(" ");
            switch (op[0]) {
                case "I":
                    int num = Integer.parseInt(op[1]);
                    maxQue.offer(num);
                    minQue.offer(num);
                    break;
                case "D":
                    if (maxQue.isEmpty()) continue;
                    if (op[1].equals("1")) {
                        int max = maxQue.poll();
                        minQue.remove(max);
                    } else {
                        int min = minQue.poll();
                        maxQue.remove(min);
                    }
                    break;
                default:
                    break;
            }
        }
        if(!maxQue.isEmpty() && !minQue.isEmpty()) {
            answer[0] = maxQue.poll();
            answer[1] = minQue.poll();
            return answer;
        }
        return new int[] {0, 0};
    }

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
        for (int i = 1; i<= m; i++) {
            result.append(map.getOrDefault(checkCards[i], 0));
        }
        System.out.println(result);
    }
}
