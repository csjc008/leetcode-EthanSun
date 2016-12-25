package prbm52;

/**
 * 52. <b>N-Queens II</b><br>
 * Follow up for N-Queens problem.<br>
 *
 * Now, instead outputting board configurations, return the total number of
 * distinct solutions.<br>
 */
public class Solution {
    /**
     * AC, Time Complexity is O(n^2)
     */
    public int totalNQueens(int n) {
        // occupation tag for slant left, (i + j)
        int[] slantLeft = new int[2 * n - 1];
        // occupation tag for slant right, (i + n - j - 1)
        int[] slantRight = new int[2 * n - 1];
        // y axis occupation tag
        int[] y = new int[n];
        return getSolutions(y, slantLeft, slantRight, n, 0);
    }

    /**
     *
     * @return solution number for current path
     */
    public int getSolutions(int[] y, int[] slantLeft, int[] slantRight, int n, int selected) {
        int i = selected;
        selected++;
        int snum = 0;
        for (int j = 0; j < n; j++) {
            // at step selected, select point (i,j) at board
            if (y[j] > 0) {
                // y index is occupied
                continue;
            }
            if (slantLeft[i + j] > 0 || slantRight[i + n - j - 1] > 0) {
                // slant position occupied
                continue;
            }
            if (selected == n) {
                // solution found
                snum++;
            } else {
                // set occupied
                y[j] = selected;
                slantLeft[i + j] = 1;
                slantRight[i + n - j - 1] = 1;
                // look for next position
                snum += getSolutions(y, slantLeft, slantRight, n, selected);
                // restore status for next step
                y[j] = 0;
                slantLeft[i + j] = 0;
                slantRight[i + n - j - 1] = 0;
            }
        }
        return snum;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.totalNQueens(4));
    }
}