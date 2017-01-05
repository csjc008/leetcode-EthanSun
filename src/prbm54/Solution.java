package prbm54;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. <b>Spiral Matrix</b><br>
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.<br>
 *
 * For example,<br>
 * Given the following matrix:<br>
 *
 * [<br>
 * [ 1, 2, 3 ],<br>
 * [ 4, 5, 6 ],<br>
 * [ 7, 8, 9 ]<br>
 * ]<br>
 * You should return [1,2,3,6,9,8,7,4,5].<br>
 */
public class Solution {
    /**
     * AC
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> retlist = new ArrayList<Integer>();
        int m = matrix.length;
        if (m == 0) {
            return retlist;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return retlist;
        }
        int xs = 0, xe = m - 1;
        int ys = 0, ye = n - 1;
        while (true) {
            retlist.add(matrix[xs][ys]);
            for (int j = ys + 1; j <= ye; j++) {
                retlist.add(matrix[xs][j]);
            }
            for (int i = xs + 1; i <= xe; i++) {
                retlist.add(matrix[i][ye]);
            }
            if (xs != xe) {
                for (int j = ye - 1; j >= ys; j--) {
                    retlist.add(matrix[xe][j]);
                }
            }
            if (ys != ye) {
                for (int i = xe - 1; i >= xs + 1; i--) {
                    retlist.add(matrix[i][ys]);
                }
            }

            if (xs == xe || ys == ye || xs + 1 == xe || ys + 1 == ye) {
                break;
            }
            xs++;
            xe--;
            ys++;
            ye--;
        }
        return retlist;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.spiralOrder(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }));
        System.out.println(s.spiralOrder(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } }));
        System.out.println(s.spiralOrder(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } }));
    }
}