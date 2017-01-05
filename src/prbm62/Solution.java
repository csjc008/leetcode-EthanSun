package prbm62;

/**
 * 62. <b>Unique Paths</b><br>
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).<br>
 *
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).<br>
 *
 * How many possible unique paths are there?<br>
 *
 * <b>Note</b>: m and n will be at most 100.
 */
public class Solution {

    /**
     * AC<br>
     * quite simple, calculate C(m+n,m) is ok.
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }
        m = m - 1;
        n = n - 1;
        if (n < m) {
            int tmp = n;
            n = m;
            m = tmp;
        }
        long upper = 1;
        for (int i = m + n; i >= (n + 1); i--) {
            upper *= i;
        }
        long lower = 1;
        for (int i = m; i >= 1; i--) {
            lower *= i;
        }
        return (int) (upper / lower);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.uniquePaths(1, 2));
        System.out.println(s.uniquePaths(2, 100));
    }
}