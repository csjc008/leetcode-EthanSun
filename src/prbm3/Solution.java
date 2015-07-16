package prbm3;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> cset = new HashSet<Character>();
        int lgst = 0;
        int curLen = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            curLen++;
            if (cset.contains(c)) {
                lgst = (curLen - 1) > lgst ? (curLen - 1) : lgst;
                for (int j = i - cset.size(); j < i; j++) {
                    curLen--;
                    cset.remove(s.charAt(j));
                    if (s.charAt(j) == c) {
                        break;
                    }
                }
            }
            cset.add(c);
        }
        lgst = curLen > lgst ? curLen : lgst;
        return lgst;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.lengthOfLongestSubstring("bbbbbb"));
        System.out.println(s.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(s.lengthOfLongestSubstring("aab"));
        System.out.println(s.lengthOfLongestSubstring("dvdf"));
        System.out.println(s.lengthOfLongestSubstring("advdf"));
    }
}