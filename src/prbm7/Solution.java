package prbm7;

/**
 * 7. <b>Reverse Integer</b><br>
 * Reverse digits of an integer.<br>
 *
 * Example1: x = 123, return 321<br>
 * Example2: x = -123, return -321<br>
 * <br>
 *
 * Have you thought about this?<br>
 * Here are some good questions to ask before coding. Bonus points for you if
 * you have already thought through this!<br>
 *
 * If the integer's last digit is 0, what should the output be? ie, cases such
 * as 10, 100.<br>
 *
 * Did you notice that the reversed integer might overflow? Assume the input is
 * a 32-bit integer, then the reverse of 1000000003 overflows. How should you
 * handle such cases?<br>
 *
 * For the purpose of this problem, assume that your function returns 0 when the
 * reversed integer overflows.<br>
 *
 */
public class Solution {
    /**
     * AC
     */
    public int reverse(int x) {
        int x1 = Math.abs(x);
        int rev = 0;
        while (x1 > 0) {
            if (rev > (Integer.MAX_VALUE - (x1 - (x1 / 10) * 10)) / 10) {
                return 0;
            }
            rev = rev * 10 + (x1 - (x1 / 10) * 10);
            x1 = x1 / 10;
        }
        if (x > 0) {
            return rev;
        } else {
            return -rev;
        }
    }
}