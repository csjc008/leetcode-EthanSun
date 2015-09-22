package prbm8;

public class Solution {
    public final static long INT_MAX = 2147483647;
    public final static long INT_MIN = -2147483648;

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
            if (sum >= INT_MAX) {
                break;
            }
        }
        return this.getSuitInt(sum * flag);
    }

    private int getSuitInt(long sum) {
        if (sum > INT_MAX) {
            return (int) INT_MAX;
        } else if (sum < INT_MIN) {
            return (int) INT_MIN;
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
