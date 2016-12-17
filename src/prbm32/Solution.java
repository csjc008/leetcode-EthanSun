package prbm32;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 32. Longest Valid Parentheses<br>
 * Given a string containing just the characters '(' and ')', find the length of
 * the longest valid (well-formed) parentheses substring.<br>
 *
 * For "(()", the longest valid parentheses substring is "()", which has length
 * = 2.<br>
 *
 * Another example is ")()())", where the longest valid parentheses substring is
 * "()()", which has length = 4.
 *
 */
public class Solution {
    public int longestValidParentheses(String s) {
        Deque<Integer> stk = new LinkedList<Integer>();
        int[] marker = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stk.push(i);
            }
            if (c == ')') {
                if (stk.isEmpty()) {
                    continue;
                }
                int prev = stk.pop();
                marker[i] = 1;
                marker[prev] = 1;
            }
        }
        int maxValid = 0;
        int valid = 0;
        for (int i = 0; i < marker.length; i++) {
            if (marker[i] == 1) {
                valid++;
            } else {
                maxValid = valid > maxValid ? valid : maxValid;
                valid = 0;
            }
        }
        maxValid = valid > maxValid ? valid : maxValid;
        return maxValid;
    }

    public int longestValidParentheses2(String s) {
        // stk and size can simulate stack with more speed
        int[] stk = new int[s.length()];
        int size = 0;
        int[] marker = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stk[size] = i;
                size++;
            }
            if (c == ')') {
                if (size == 0) {
                    continue;
                }
                int prev = stk[size - 1];
                size--;
                marker[i] = 1;
                marker[prev] = 1;
            }
        }
        int maxValid = 0;
        int valid = 0;
        for (int i = 0; i < marker.length; i++) {
            if (marker[i] == 1) {
                valid++;
            } else {
                maxValid = valid > maxValid ? valid : maxValid;
                valid = 0;
            }
        }
        maxValid = valid > maxValid ? valid : maxValid;
        return maxValid;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.longestValidParentheses2("(()"));
        System.out.println(s.longestValidParentheses2(")()())"));
        System.out.println(s.longestValidParentheses2("(())"));
        System.out.println(s.longestValidParentheses2("()(()"));
        System.out.println(s.longestValidParentheses2("()(())"));
        System.out.println(s.longestValidParentheses2("(()(()"));
        System.out.println(s.longestValidParentheses2("(())()"));
        System.out.println(s.longestValidParentheses2("(()()"));
        System.out.println(s.longestValidParentheses2("(()())()"));
    }
}
