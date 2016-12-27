package prbm5;

/**
 * 5. <b>Longest Palindromic Substring</b><br>
 * Given a string s, find the longest palindromic substring in s. You may assume
 * that the maximum length of s is 1000.<br>
 *
 * <b>Example:</b><br>
 *
 * Input: "babad"<br>
 *
 * Output: "bab"<br>
 *
 * Note: "aba" is also a valid answer.<br>
 *
 * <b>Example:</b><br>
 *
 * Input: "cbbd"<br>
 *
 * Output: "bb"
 *
 */
public class Solution {
    /**
     * AC
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        int maxl = 0;
        int maxr = 0;
        int maxLen = 1;
        for (int k = 0; k < len * 2 - 1; k++) {
            int base = 0;
            int plen = 1;
            int left = k / 2;
            int right = k / 2;
            if (k % 2 == 1) {
                base = 1;
                plen = 0;
                left = (k - 1) / 2;
                right = (k + 1) / 2;
            }
            for (int i = base; k - i >= 0 && (k + i) / 2 <= len - 1; i = i + 2) {
                int l = (k - i) / 2;
                int r = (k + i) / 2;
                if (s.charAt(l) != s.charAt(r)) {
                    break;
                }
                left = l;
                right = r;
                plen = r - l + 1;
            }
            if (plen > maxLen) {
                maxl = left;
                maxr = right;
                maxLen = plen;
            }
        }
        return s.substring(maxl, maxr + 1);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.longestPalindrome("a"));
        System.out.println(s.longestPalindrome("aba"));
        System.out.println(s.longestPalindrome("aa"));
        System.out.println(s.longestPalindrome("abac"));
        System.out.println(s.longestPalindrome("baab"));
    }
}