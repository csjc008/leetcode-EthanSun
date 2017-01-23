package prbm73;

public class Solution {
    /**
     * no use of extra space, but cannot solve matrix with negative elements.
     * WA.
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
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] < 0) {
                continue;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (matrix[i][0] == 0) {
                        matrix[i][0] = -2;
                    } else {
                        matrix[i][0] = -1;
                    }
                    if (matrix[0][j] == 0) {
                        matrix[0][j] = -2;
                    } else {
                        matrix[0][j] = -1;
                    }
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] <= 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] <= 0) {
                for (int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        boolean b1 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0 || matrix[i][0] == -2) {
                b1 = true;
            }
        }
        boolean b2 = false;
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0 || matrix[0][j] == -2) {
                b2 = true;
            }
        }
        for (int i = 0; i < m; i++) {
            if (b1 || matrix[i][0] < 0) {
                matrix[i][0] = 0;
            }
        }
        for (int j = 0; j < n; j++) {
            if (b2 || matrix[0][j] < 0) {
                matrix[0][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
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