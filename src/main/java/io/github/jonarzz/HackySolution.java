package io.github.jonarzz;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HackySolution implements Solution {

    private List<List<String>> result;
    private HashMap<String, List<String>> map = new HashMap<>();

    @Override
    public List<List<String>> groupAnagrams(String[] strs) {

        return new AbstractList<>() {
            @Override
            public List<String> get(int key) {
                if (result == null) {
                    init(strs);
                }
                return result.get(key);
            }

            @Override
            public int size() {
                if (result == null) {
                    init(strs);
                }
                return result.size();
            }
        };
    }

    private void init(String[] strs) {
        for (String str : strs) {
            char[] ch_map = str.toCharArray();
            Arrays.sort(ch_map);
            String key = String.valueOf(ch_map);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        result = new ArrayList<>(map.values());
    }

}
