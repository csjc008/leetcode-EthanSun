package prbm63;

/**
 * 63. <b>Unique Paths II</b><br>
 * Follow up for "Unique Paths":<br>
 *
 * Now consider if some obstacles are added to the grids. How many unique paths
 * would there be?<br>
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <br>
 *
 * For example,<br>
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.<br>
 *
 * [<br>
 * [0,0,0],<br>
 * [0,1,0],<br>
 * [0,0,0]<br>
 * ]<br>
 * The total number of unique paths is 2.<br>
 *
 * <b>Note:</b> m and n will be at most 100.
 */
public class Solution {

    /**
     * AC, O(m*n)
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) {
            return 0;
        }
        int n = obstacleGrid[0].length;
        if (n == 0) {
            return 0;
        }
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        int[][] cnt = new int[m][n];
        int mx = m - 1;
        int nx = n - 1;

        while (mx >= 0 && nx >= 0) {
            if (mx == m - 1 && nx == n - 1) {
                cnt[mx][nx] = 1;
            } else if (obstacleGrid[mx][nx] == 1) {
                cnt[mx][nx] = 0;
            } else {
                cnt[mx][nx] = cnt[mx + 1][nx] + cnt[mx][nx + 1];
            }
            for (int i = mx - 1; i >= 0; i--) {
                if (obstacleGrid[i][nx] == 1) {
                    cnt[i][nx] = 0;
                    continue;
                }
                // (i,nx)
                int c = 0;
                // (i+1,nx)
                if (i + 1 < m) {
                    c += cnt[i + 1][nx];
                }
                // (i,nx+1)
                if (nx + 1 < n) {
                    c += cnt[i][nx + 1];
                }
                cnt[i][nx] = c;
            }
            for (int j = nx - 1; j >= 0; j--) {
                if (obstacleGrid[mx][j] == 1) {
                    cnt[mx][j] = 0;
                    continue;
                }
                // (mx,j)
                int c = 0;
                // (mx+1,j)
                if (mx + 1 < m) {
                    c += cnt[mx + 1][j];
                }
                // (mx,j+1)
                if (j + 1 < n) {
                    c += cnt[mx][j + 1];
                }
                cnt[mx][j] = c;
            }
            mx--;
            nx--;
        }

        return cnt[0][0];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.uniquePathsWithObstacles(new int[][] { { 0, 0 }, { 1, 1 }, { 0, 0 } }));
        System.out.println(s.uniquePathsWithObstacles(new int[][] { { 0, 1 }, { 1, 0 } }));
        System.out.println(s.uniquePathsWithObstacles(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }));
        System.out.println(s.uniquePathsWithObstacles(new int[][] { { 0, 1, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }));
        System.out.println(s.uniquePathsWithObstacles(new int[][] { { 0, 1, 0 }, { 0, 0, 1 }, { 0, 0, 0 } }));
        System.out.println(
                s.uniquePathsWithObstacles(new int[][] { { 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 0 } }));

    }
}