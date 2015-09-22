package prbm9;

public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int digits = 0;
        int tmp = x;
        while (tmp != 0) {
            tmp = tmp / 10;
            digits++;
        }
        for (int i = 1; i <= digits / 2; i++) {
            if (getDigit(x, i) != getDigit(x, digits + 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private int getDigit(int target, int pos) {
        for (int i = 0; i < pos - 1; i++) {
            target = target / 10;
        }
        return target % 10;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isPalindrome(1));
        System.out.println(s.isPalindrome(12));
        System.out.println(s.isPalindrome(11));
        System.out.println(s.isPalindrome(121));
        System.out.println(s.isPalindrome(1212));
        System.out.println(s.isPalindrome(-1));
        System.out.println(s.isPalindrome(-11));
        System.out.println(s.isPalindrome(-121));
        System.out.println(s.isPalindrome(-2147447412));
    }
}