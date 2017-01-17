package prbm9;

/**
 * 9. <b>Palindrome Number</b><br>
 * Determine whether an integer is a palindrome. <b>Do this without extra
 * space.</b><br>
 *
 * <b>Some hints:</b><br>
 * Could negative integers be palindromes? (ie, -1)<br>
 *
 * If you are thinking of converting the integer to string, note the restriction
 * of using extra space.<br>
 *
 * You could also try reversing an integer. However, if you have solved the
 * problem "Reverse Integer", you know that the reversed integer might overflow.
 * How would you handle such case?<br>
 *
 * There is a more generic way of solving this problem.<br>
 *
 */
public class Solution {
    /**
     * AC
     */
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