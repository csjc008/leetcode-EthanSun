package prbm74;

/**
 * 74. <b>Search a 2D Matrix</b><br>
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:<br>
 *
 * Integers in each row are sorted from left to right.<br>
 * The first integer of each row is greater than the last integer of the
 * previous row.<br>
 * For example,<br>
 *
 * Consider the following matrix:<br>
 *
 * [<br>
 * [1, 3, 5, 7],<br>
 * [10, 11, 16, 20],<br>
 * [23, 30, 34, 50]<br>
 * ]<br>
 * Given target = 3, return true.<br>
 */
public class Solution {
    /**
     * AC
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return false;
        }
        int trow = -1;
        for (int i = 0; i < m; i++) {
            if (target < matrix[i][0]) {
                break;
            } else if (target == matrix[i][0]) {
                return true;
            } else {
                trow++;
            }
        }
        if (trow == -1) {
            return false;
        }
        int[] row = matrix[trow];
        int l = 0;
        int r = n - 1;
        int p = (l + r) / 2;
        while (true) {
            if (target < row[l] || target > row[r]) {
                return false;
            }
            if (target == row[p]) {
                return true;
            }
            if (l == r) {
                return false;
            }
            if (target > row[p]) {
                l = p;
                p = (l + r) / 2;
                if (l == p && l < r) {
                    l++;
                    p++;
                }
            } else {
                r = p;
                p = (l + r) / 2;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] mtx = new int[][] { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } };
        System.out.println(s.searchMatrix(mtx, 3));
        System.out.println(s.searchMatrix(mtx, 1));
        System.out.println(s.searchMatrix(mtx, 0));
        System.out.println(s.searchMatrix(mtx, 7));
        System.out.println(s.searchMatrix(mtx, 5));
        System.out.println(s.searchMatrix(mtx, 22));
        System.out.println(s.searchMatrix(mtx, 23));
        System.out.println(s.searchMatrix(mtx, 50));
        System.out.println(s.searchMatrix(mtx, 51));
    }
}