package com.sycompany.bojstep;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Iterator;

public class Test {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String[] item : clothes) {
            String type = item[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        Iterator<Integer> it = map.values().iterator();
        int answer = 1;
        while (it.hasNext()) {
            answer *= it.next() + 1;
        }

        return answer - 1;
    }
}
