package com.map_performance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapPerformanceTest {

    public static void main(String[] args) {
        Map<Integer, Integer> map_test = new HashMap<Integer, Integer>();
        for (int i = 0; i < 1000000; i++) {
            map_test.put(i, i);
        }
        long start = System.currentTimeMillis();
        for (int key : map_test.keySet()) {
            map_test.get(key);
        }
        long end = System.currentTimeMillis();
        System.out.println("For->keySet->" + (end - start) + " ms");

        start = System.currentTimeMillis();
        for (Map.Entry<Integer, Integer> entry : map_test.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();
        System.out.println("For->Entry Set->" + (end - start) + " ms");

        start = System.currentTimeMillis();
        Iterator<Integer> iterator=map_test.keySet().iterator();
        Integer key;
        while(iterator.hasNext())
        {
            key=iterator.next();
        }
        end = System.currentTimeMillis();
        System.out.println("While->Key Iterator 迭代器->" + (end - start) + " ms");

        start =System.currentTimeMillis();
        Iterator<Map.Entry<Integer,Integer>> iterator_map_entry=map_test.entrySet().iterator();
        while (iterator_map_entry.hasNext())
        {
            Map.Entry<Integer,Integer> map=iterator_map_entry.next();
            map.getValue();
            map.getKey();
        }
        end=System.currentTimeMillis();
        System.out.println("While->Entry Iterator 迭代器->" + (end - start) + " ms");
    }
}
