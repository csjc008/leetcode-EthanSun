package prbm13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private Map<Character, Integer> romanMap;

    public Solution() {
        romanMap = new HashMap<Character, Integer>();
        romanMap.put('I', 1);
        romanMap.put('X', 10);
        romanMap.put('C', 100);
        romanMap.put('M', 1000);
        romanMap.put('V', 5);
        romanMap.put('L', 50);
        romanMap.put('D', 500);
    }

    public int romanToInt(String s) {
        int sum = 0;
        List<Integer> numArray = new ArrayList<Integer>();
        for (Character c : s.toCharArray()) {
            if (romanMap.containsKey(c)) {
                numArray.add(romanMap.get(c));
            }
        }
        int size = numArray.size();
        for (int i = 0; i < size; i++) {
            int e = numArray.get(i);
            if (i != (size - 1) && e < numArray.get(i + 1)) {
                sum = sum - e;
            } else {
                sum = sum + e;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.romanToInt("MCMLXXX"));
    }
}