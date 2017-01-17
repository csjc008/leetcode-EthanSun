package prbm72;

import java.util.ArrayList;
import java.util.List;

/**
 * 72. <b>Edit Distance</b><br>
 * Given two words word1 and word2, find the minimum number of steps required to
 * convert word1 to word2. (each operation is counted as 1 step.)<br>
 *
 * You have the following 3 operations permitted on a word:<br>
 *
 * a) Insert a character<br>
 * b) Delete a character<br>
 * c) Replace a character<br>
 *
 */
public class Solution {

    /**
     * AC
     */
    public int minDistance(String word1, String word2) {
        int front = sameFrontLen(word1, word2);
        word1 = word1.substring(front);
        word2 = word2.substring(front);
        int back = sameBackLen(word1, word2);
        word1 = word1.substring(0, word1.length() - back);
        word2 = word2.substring(0, word2.length() - back);
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }
        int maxSame = 0;
        List<Integer> idxList = new ArrayList<Integer>();
        for (int shift = -(word1.length() - 1); shift < word2.length(); shift++) {
            int s1 = -shift > 0 ? -shift : 0;
            int s2 = s1 + shift;
            int samecnt = 0;
            if (Math.min(word1.length() - s1 + 1, word2.length() - s2 + 1) <= maxSame) {
                break;
            }
            while (s1 < word1.length() && s2 < word2.length()) {
                if (word1.charAt(s1) == word2.charAt(s2)) {
                    samecnt++;
                    if (samecnt >= maxSame) {
                        // ws1 = s1 - (samecnt - 1);
                        // we1 = s1;
                        // ws2 = s2 - (samecnt - 1);
                        // we2 = s2;
                        if (samecnt > maxSame) {
                            maxSame = samecnt;
                            idxList.clear();
                        }
                        idxList.add(s1 - (samecnt - 1));
                        idxList.add(s1);
                        idxList.add(s2 - (samecnt - 1));
                        idxList.add(s2);
                    }
                } else {
                    samecnt = 0;
                }
                s1++;
                s2++;
            }
        }

        if (maxSame > 0) {
            // word1.substring(0, ws1) ~ word1.substring(we1 + 1);
            // word2.substring(0, ws2) ~ word2.substring(we2 + 1);
            int minSteps = Math.max(word1.length(), word2.length());
            for (int i = 0; i < idxList.size() / 4; i++) {
                int steps = minDistance(word1.substring(0, idxList.get(i * 4)),
                        word2.substring(0, idxList.get(i * 4 + 2)))
                        + minDistance(word1.substring(idxList.get(i * 4 + 1) + 1),
                                word2.substring(idxList.get(i * 4 + 3) + 1));
                if (steps < minSteps) {
                    minSteps = steps;
                }
            }
            return minSteps;
        }
        return Math.max(word1.length(), word2.length());
    }

    private int sameFrontLen(String word1, String word2) {
        int i = 0;
        while (i <= word1.length() - 1 && i <= word2.length() - 1) {
            if (word1.charAt(i) != word2.charAt(i)) {
                break;
            }
            i++;
        }
        return i;
    }

    private int sameBackLen(String word1, String word2) {
        int i1 = word1.length() - 1;
        int i2 = word2.length() - 1;
        while (i1 >= 0 && i2 >= 0) {
            if (word1.charAt(i1) != word2.charAt(i2)) {
                break;
            }
            i1--;
            i2--;
        }
        return word1.length() - 1 - i1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minDistance("", ""));
        System.out.println(s.minDistance("ab", "abc"));
        System.out.println(s.minDistance("cba", "abc"));
        System.out.println(s.minDistance("a", "a"));
        System.out.println(s.minDistance("dinitropi", "benzalpo"));
        System.out.println(s.minDistance("dinitrophenylhydrazine", "benzalphenylhydrazone"));
        System.out.println(s.minDistance("teacher", "botcher"));
        System.out.println(s.minDistance("distance", "springbok"));
        System.out.println(s.minDistance("teacher", "chacker"));
        System.out.println(s.minDistance("industry", "interest"));
    }
}
