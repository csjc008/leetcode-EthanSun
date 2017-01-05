package prbm59;

/**
 * 59. <b>Spiral Matrix II</b><br>
 *
 * Given an integer n, generate a square matrix filled with elements from 1 to
 * n2 in spiral order.<br>
 *
 * For example,<br>
 * Given n = 3,<br>
 *
 * You should return the following matrix:<br>
 * [<br>
 * [ 1, 2, 3 ],<br>
 * [ 8, 9, 4 ],<br>
 * [ 7, 6, 5 ]<br>
 * ]<br>
 */
public class Solution {
    /**
     * AC
     */
    public int[][] generateMatrix(int n) {
        if (n == 0) {
            return new int[0][0];
        }
        int[][] ret = new int[n][n];
        int xs = 0, xe = n - 1;
        int ys = 0, ye = n - 1;
        int cnt = 0;
        while (true) {
            cnt++;
            ret[xs][ys] = cnt;
            for (int j = ys + 1; j <= ye; j++) {
                cnt++;
                ret[xs][j] = cnt;
            }
            for (int i = xs + 1; i <= xe; i++) {
                cnt++;
                ret[i][ye] = cnt;
            }
            if (xs != xe) {
                for (int j = ye - 1; j >= ys; j--) {
                    cnt++;
                    ret[xe][j] = cnt;
                }
            }
            if (ys != ye) {
                for (int i = xe - 1; i >= xs + 1; i--) {
                    cnt++;
                    ret[i][ys] = cnt;
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
        return ret;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.generateMatrix(0));
    }
}