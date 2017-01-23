package prbm73;

/**
 * 73. <b>Set Matrix Zeroes</b><br>
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
 * Do it in place.<br>
 *
 * Follow up:<br>
 * Did you use extra space?<br>
 * A straight forward solution using O(mn) space is probably a bad idea.<br>
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * <br>
 * Could you devise a constant space solution?<br>
 *
 */
public class Solution2 {
    /**
     * AC, but space complexity is O(m+n), not O(1)
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return;
        }
        int[] tm = new int[m];
        int[] tn = new int[n];
        for (int i = 0; i < m; i++) {
            if (tm[i] == 1) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    tm[i] = 1;
                    tn[j] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (tm[i] == 1) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            if (tn[j] == 1) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        int[][] m1 = new int[][] { { 1, 2, 0 }, { 1, 0, 1 }, { 4, 5, 6 } };
        int[][] m2 = new int[][] { { 1, 0 } };
        int[][] m3 = new int[][] { { 0, 1 } };
        int[][] m4 = new int[][] { { 1 }, { 0 } };
        s.setZeroes(m1);
        s.setZeroes(m2);
        s.setZeroes(m3);
        s.setZeroes(m4);
        System.out.println(m1);
    }
}