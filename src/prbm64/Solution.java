package prbm64;

/**
 * 64. <b>Minimum Path Sum</b><br>
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * <br>
 * 
 * Note: You can only move either down or right at any point in time.<br>
 *
 */
public class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int[][] cnt = new int[m][n];
        cnt[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) {
                    cnt[i][j] = cnt[i][j - 1] + grid[i][j];
                }
                if (i > 0 && j == 0) {
                    cnt[i][j] = cnt[i - 1][j] + grid[i][j];
                }
                if (i > 0 && j > 0) {
                    cnt[i][j] = Math.min(cnt[i - 1][j], cnt[i][j - 1]) + grid[i][j];
                }
            }
        }
        return cnt[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minPathSum(new int[][] { { 1 } }));
        System.out.println(s.minPathSum(new int[][] { { 0, 1 }, { 2, 3 }, { 4, 5 } }));
        System.out.println(s.minPathSum(new int[][] { { 0, 0 }, { 1, 1 }, { 0, 0 } }));
    }
}