package prbm14;

public class Solution {
    public String intToRoman(int num) {
        int[] digits = new int[4];
        for (int i = 0; i < 4; i++) {
            digits[3 - i] = num % 10;
            num = num / 10;
        }
        // 3999 -> 3,9,9,9
        StringBuffer ret = new StringBuffer();
        // thousand
        for (int i = 0; i < digits[0]; i++) {
            ret.append('M');
        }
        // hundred
        if (digits[1] == 9) {
            ret.append("CM");
        } else if (digits[1] == 4) {
            ret.append("CD");
        } else {
            int b = digits[1];
            if (b >= 5) {
                ret.append('D');
                b = b - 5;
            }
            for (int i = 0; i < b; i++) {
                ret.append('C');
            }
        }
        // ten
        if (digits[2] == 9) {
            ret.append("XC");
        } else if (digits[2] == 4) {
            ret.append("XL");
        } else {
            int b = digits[2];
            if (b >= 5) {
                ret.append('L');
                b = b - 5;
            }
            for (int i = 0; i < b; i++) {
                ret.append('X');
            }
        }
        // one
        if (digits[3] == 9) {
            ret.append("IX");
        } else if (digits[3] == 4) {
            ret.append("IV");
        } else {
            int b = digits[3];
            if (b >= 5) {
                ret.append('V');
                b = b - 5;
            }
            for (int i = 0; i < b; i++) {
                ret.append('I');
            }
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("1980=" + s.intToRoman(1980));
        System.out.println("45=" + s.intToRoman(45));
        System.out.println("20=" + s.intToRoman(20));
        System.out.println("3999=" + s.intToRoman(3999));
    }
}