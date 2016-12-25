package prbm51;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. <b>N-Queens</b><br>
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.<br>
 * Given an integer n, return all distinct solutions to the n-queens puzzle.<br>
 *
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space
 * respectively.<br>
 *
 * For example,<br>
 * There exist two distinct solutions to the 4-queens puzzle:<br>
 *
 * [<br>
 * [".Q..", // Solution 1<br>
 * "...Q",<br>
 * "Q...",<br>
 * "..Q."],<br>
 *
 * ["..Q.", // Solution 2<br>
 * "Q...",<br>
 * "...Q",<br>
 * ".Q.."]<br>
 * ]<br>
 */
public class Solution {
    /**
     * AC, Time Complexity(excluding the output procedure) is O(n^2)
     */
    public List<List<String>> solveNQueens(int n) {
        // occupation tag for slant left, (i + j)
        int[] slantLeft = new int[2 * n - 1];
        // occupation tag for slant right, (i + n - j - 1)
        int[] slantRight = new int[2 * n - 1];
        // y axis occupation tag
        int[] y = new int[n];
        // solution set
        List<List<String>> s = new ArrayList<List<String>>();
        getSolutions(s, y, slantLeft, slantRight, n, 0);
        return s;
    }

    public void getSolutions(List<List<String>> s, int[] y, int[] slantLeft, int[] slantRight, int n, int selected) {
        int i = selected;
        selected++;
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
                // set occupied
                y[j] = selected;
                // output solution
                int iy[] = new int[n];
                for (int ii = 0; ii < n; ii++) {
                    iy[y[ii] - 1] = ii;
                }
                List<String> ns = new ArrayList<String>();
                for (int ii = 0; ii < n; ii++) {
                    StringBuffer sb = new StringBuffer();
                    for (int jj = 0; jj < n; jj++) {
                        if (jj == iy[ii]) {
                            sb.append('Q');
                        } else {
                            sb.append('.');
                        }
                    }
                    ns.add(sb.toString());
                }
                s.add(ns);
                // output solution end
                // restore status for next step
                y[j] = 0;
            } else {
                // set occupied
                y[j] = selected;
                slantLeft[i + j] = 1;
                slantRight[i + n - j - 1] = 1;
                // look for next position
                // recusion
                getSolutions(s, y, slantLeft, slantRight, n, selected);
                // restore status for next step
                y[j] = 0;
                slantLeft[i + j] = 0;
                slantRight[i + n - j - 1] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solveNQueens(8));
    }
}