package prbm8;

/**
 * 8. <b>String to Integer (atoi)</b><br>
 * Implement atoi to convert a string to an integer.<br>
 *
 * <b>Hint:</b> Carefully consider all possible input cases. If you want a
 * challenge, please do not see below and ask yourself what are the possible
 * input cases. <br>
 *
 * <b>Notes:</b> It is intended for this problem to be specified vaguely (ie, no
 * given input specs). You are responsible to gather all the input requirements
 * up front.<br>
 *
 * <b>Requirements for atoi:</b><br>
 * The function first discards as many whitespace characters as necessary until
 * the first non-whitespace character is found. Then, starting from this
 * character, takes an optional initial plus or minus sign followed by as many
 * numerical digits as possible, and interprets them as a numerical value.<br>
 *
 * The string can contain additional characters after those that form the
 * integral number, which are ignored and have no effect on the behavior of this
 * function.<br>
 *
 * If the first sequence of non-whitespace characters in str is not a valid
 * integral number, or if no such sequence exists because either str is empty or
 * it contains only whitespace characters, no conversion is performed.<br>
 *
 * If no valid conversion could be performed, a zero value is returned. If the
 * correct value is out of the range of representable values, INT_MAX
 * (2147483647) or INT_MIN (-2147483648) is returned.<br>
 */
public class Solution {

    /**
     * AC
     */
    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        int flag = 1;
        int start = 0;
        long sum = 0;
        if (str.charAt(0) == '-') {
            flag = -1;
            start = 1;
        } else if (str.charAt(0) == '+') {
            flag = 1;
            start = 1;
        }
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) > '9' || str.charAt(i) < '0') {
                return this.getSuitInt(sum * flag);
            }
            sum = sum * 10 + (str.charAt(i) - '0');
            if (sum >= Integer.MAX_VALUE) {
                break;
            }
        }
        return this.getSuitInt(sum * flag);
    }

    private int getSuitInt(long sum) {
        if (sum > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (sum < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) sum;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.myAtoi("0"));
        System.out.println(s.myAtoi("4"));
        System.out.println(s.myAtoi("18384"));
        System.out.println(s.myAtoi("-10203"));
        System.out.println(s.myAtoi("+304040"));
        System.out.println(s.myAtoi("9223372036854775809"));
    }
}
