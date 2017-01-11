package prbm65;

import java.util.HashSet;
import java.util.Set;

/**
 * 65. <b>Valid Number</b><br>
 * Validate if a given string is numeric.<br>
 *
 * Some examples:<br>
 * "0" => true<br>
 * " 0.1 " => true<br>
 * "abc" => false<br>
 * "1 a" => false<br>
 * "2e10" => true<br>
 * <b>Note:</b> It is intended for the problem statement to be ambiguous. You
 * should gather all requirements up front before implementing one.<br>
 *
 */
public class Solution {

    public static final Set<Character> cset;

    static {
        cset = new HashSet<Character>();
        for (int i = 0; i < 10; i++) {
            cset.add((char) ('0' + i));
        }
        cset.add('+');
        cset.add('-');
        cset.add('e');
        cset.add('E');
        cset.add('.');
    }

    /**
     * AC
     */
    public boolean isNumber(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        s = s.trim();
        if (s.contains(" ")) {
            return false;
        }
        if (containsInvalidChar(s)) {
            return false;
        }
        s = s.toLowerCase();
        int ecount = countChar(s, 'e');
        if (ecount > 1) {
            return false;
        }
        String[] ss = s.split("e", -1);
        if (ss.length > 2 || ss.length == 0) {
            return false;
        }
        boolean b1 = isFloat(ss[0]);
        if (!b1) {
            return false;
        }
        if (ss.length > 1 && !isInteger(ss[1])) {
            return false;
        }
        return true;
    }

    private boolean containsInvalidChar(String s) {
        for (char c : s.toCharArray()) {
            if (!cset.contains(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFloat(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int pluscount = countChar(s, '+');
        int minuscount = countChar(s, '-');
        int dotcount = countChar(s, '.');
        if (pluscount > 1 || pluscount == 1 && s.charAt(0) != '+') {
            return false;
        }
        if (minuscount > 1 || minuscount == 1 && s.charAt(0) != '-') {
            return false;
        }
        if (pluscount + minuscount > 1) {
            return false;
        }
        if (dotcount > 1) {
            return false;
        }
        if (!containsNumChar(s)) {
            return false;
        }
        return true;
    }

    private boolean isInteger(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        if (s == null || s.length() == 0) {
            return false;
        }
        int pluscount = countChar(s, '+');
        int minuscount = countChar(s, '-');
        int dotcount = countChar(s, '.');
        if (pluscount > 1 || pluscount == 1 && s.charAt(0) != '+') {
            return false;
        }
        if (minuscount > 1 || minuscount == 1 && s.charAt(0) != '-') {
            return false;
        }
        if (pluscount + minuscount > 1) {
            return false;
        }
        if (dotcount > 0) {
            return false;
        }
        if (!containsNumChar(s)) {
            return false;
        }
        return true;
    }

    private int countChar(String s, char c) {
        int cnt = 0;
        for (char cs : s.toCharArray()) {
            if (cs == c) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean containsNumChar(String s) {
        for (char cs : s.toCharArray()) {
            if (cs >= '0' && cs <= '9') {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        // System.out.println(s.isNumber("1"));// true
        // System.out.println(s.isNumber(" 0.1"));// true
        // System.out.println(s.isNumber("abc"));// false
        // System.out.println(s.isNumber("1 a"));// false
        // System.out.println(s.isNumber("2e10"));// true
        System.out.println(s.isNumber("2.0e"));// false
        System.out.println(s.isNumber("2.0e10"));// true
        System.out.println(s.isNumber("e10"));// false
        System.out.println(s.isNumber("2.0e-10"));// true
        System.out.println(s.isNumber("2.0e+10"));// true
        System.out.println(s.isNumber("2e10e10"));// false
        System.out.println(s.isNumber("2ee"));// false
        System.out.println(s.isNumber("-2.0"));// true
        System.out.println(s.isNumber("+2.0"));// true
        System.out.println(s.isNumber("-2.0-"));// false
        System.out.println(s.isNumber("2."));// true
        System.out.println(s.isNumber("+."));// false
        System.out.println(s.isNumber(".01"));// true
        System.out.println(s.isNumber("2.0e2.0"));// false
    }
}