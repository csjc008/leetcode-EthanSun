package prbm76;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. <b>Minimum Window Substring</b><br>
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).<br>
 *
 * For example,<br>
 * S = "ADOBECODEBANC"<br>
 * T = "ABC"<br>
 * Minimum window is "BANC".<br>
 *
 * Note:<br>
 * If there is no such window in S that covers all characters in T, return the
 * empty string "".<br>
 *
 * If there are multiple such windows, you are guaranteed that there will always
 * be only one unique minimum window in S.<br>
 */
public class Solution {
    /**
     * AC, but not fast enough. O(n)
     */
    public String minWindow(String s, String t) {
        if (t == null || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> ts = new HashMap<Character, Integer>();
        for (char c : t.toCharArray()) {
            if (ts.containsKey(c)) {
                ts.put(c, ts.get(c) + 1);
            } else {
                ts.put(c, 1);
            }
        }

        Map<Character, Integer> tts = new HashMap<Character, Integer>(ts);

        Map<Character, Integer> m = new HashMap<Character, Integer>();
        int front = 0;
        int back = 0;
        for (; front < s.length(); front++) {
            char c = s.charAt(front);
            if (!ts.containsKey(c)) {
                continue;
            }
            if (tts.containsKey(c)) {
                if (tts.get(c) == 1) {
                    tts.remove(c);
                } else {
                    tts.put(c, tts.get(c) - 1);
                }
            }
            if (m.containsKey(c)) {
                m.put(c, m.get(c) + 1);
            } else {
                m.put(c, 1);
            }
            if (tts.size() == 0) {
                break;
            }
        }
        if (front == s.length()) {
            return "";
        }
        int minLen = front - back + 1;
        int minFront = front;
        int minBack = back;
        while (true) {
            while (true) {
                char c = s.charAt(back);
                if (m.containsKey(c)) {
                    // important! must be intValue()!
                    if (m.get(c).intValue() == ts.get(c).intValue()) {
                        break;
                    }
                    m.put(c, m.get(c) - 1);
                }
                back++;
            }
            if (front - back + 1 < minLen) {
                minLen = front - back + 1;
                minFront = front;
                minBack = back;
            }
            while (true) {
                front++;
                if (front >= s.length()) {
                    return s.substring(minBack, minFront + 1);
                }
                char c = s.charAt(front);
                if (!ts.containsKey(c)) {
                    continue;
                }
                m.put(c, m.get(c) + 1);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(s.minWindow("ADOBECODEBANC", "BCD"));
        System.out.println(s.minWindow("aba", "aa"));
    }
}