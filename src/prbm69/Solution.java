package prbm69;

/**
 * 69. <b>Sqrt(x)</b><br>
 * Implement int sqrt(int x).<br>
 *
 * Compute and return the square root of x.<br>
 *
 */
public class Solution {

    /**
     * AC
     */
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        int ceil = x;
        int bot = 0;
        int cur = ceil / 2;
        int flag = 1;
        while (true) {
            long sq = (long) cur * cur;
            if (sq == x) {
                return cur;
            } else if (sq < x) {
                if (flag == 1 && ceil == cur + 1) {
                    return cur;
                }
                bot = cur;
                if (bot + 1 == ceil) {
                    cur = ceil;
                } else {
                    cur = (bot + ceil) / 2;
                }
                flag = -1;
            } else {
                ceil = cur;
                cur = (bot + ceil) / 2;
                flag = 1;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.mySqrt(1));
        System.out.println(s.mySqrt(2));
        System.out.println(s.mySqrt(4));
        System.out.println(s.mySqrt(9));
        System.out.println(s.mySqrt(5 * 5 + 5));
        System.out.println(s.mySqrt(2147395599));
    }
}