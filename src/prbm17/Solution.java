package prbm17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static Map<String, List<String>> numMap;

    static {
        numMap = new HashMap<String, List<String>>();
        numMap.put("2", Arrays.asList("a", "b", "c"));
        numMap.put("3", Arrays.asList("d", "e", "f"));
        numMap.put("4", Arrays.asList("g", "h", "i"));
        numMap.put("5", Arrays.asList("j", "k", "l"));
        numMap.put("6", Arrays.asList("m", "n", "o"));
        numMap.put("7", Arrays.asList("p", "q", "r", "s"));
        numMap.put("8", Arrays.asList("t", "u", "v"));
        numMap.put("9", Arrays.asList("w", "x", "y", "z"));
    }

    /**
     * improvement: could use numMap as cache!
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        // System.out.println(numMap);
        List<String> slist = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return slist;
        }
        if (digits.length() == 1) {
            if (numMap.containsKey(digits)) {
                return numMap.get(digits);
            } else {
                return slist;
            }
        }
        String fst = digits.substring(0, 1);
        List<String> medlist = letterCombinations(digits.substring(1));
        if (!numMap.containsKey(fst)) {
            return medlist;
        }
        List<String> fstlist = numMap.get(fst);
        for (String s : fstlist) {
            for (String m : medlist) {
                slist.add(s + m);
            }
        }
        return slist;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.letterCombinations("23"));
        System.out.println(s.letterCombinations(""));
        System.out.println(s.letterCombinations("2"));
        System.out.println(s.letterCombinations("236"));
    }
}
