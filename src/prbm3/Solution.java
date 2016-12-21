package prbm3;

import java.util.HashSet;
import java.util.Set;

/**
 * <b>Longest Substring Without Repeating Characters</b><br>
 * Given a string, find the length of the longest substring without repeating
 * characters.<br>
 *
 * Examples:<br>
 *
 * Given "abcabcbb", the answer is "abc", which the length is 3.<br>
 *
 * Given "bbbbb", the answer is "b", with the length of 1.<br>
 *
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
 * answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        // a set to record chars for current substring
        Set<Character> cset = new HashSet<Character>();
        // length of longest non-repeat substring
        int lgst = 0;
        // length of current substring
        int curLen = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            curLen++;
            // if encounters a duplicate character
            if (cset.contains(c)) {
                // record non-repeat length
                lgst = (curLen - 1) > lgst ? (curLen - 1) : lgst;
                // reduce character from the head of current substring,
                // until current repeat letter is removed
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
        System.out.println(s.lengthOfLongestSubstring("abcbcdabb"));
        System.out.println(s.lengthOfLongestSubstring("aab"));
        System.out.println(s.lengthOfLongestSubstring("dvdf"));
        System.out.println(s.lengthOfLongestSubstring("advdf"));
    }
}