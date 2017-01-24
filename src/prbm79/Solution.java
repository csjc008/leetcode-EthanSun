package prbm79;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 79. <b>Word Search</b><br>
 * Given a 2D board and a word, find if the word exists in the grid.<br>
 *
 * The word can be constructed from letters of sequentially adjacent cell, where
 * "adjacent" cells are those horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.<br>
 *
 * For example,<br>
 * Given board =<br>
 *
 * [<br>
 * ['A','B','C','E'],<br>
 * ['S','F','C','S'],<br>
 * ['A','D','E','E']<br>
 * ]<br>
 * word = "ABCCED", -> returns true,<br>
 * word = "SEE", -> returns true,<br>
 * word = "ABCB", -> returns false.<br>
 *
 */
public class Solution {

    public class Point {

        public Point() {
        }

        public Point(int _x, int _y) {
            this.x = _x;
            this.y = _y;
        }

        public int x;
        public int y;

        @Override
        public int hashCode() {
            return x * 13 + y;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) {
                return false;
            }
            Point pt = (Point) obj;
            return pt.x == x && pt.y == y;
        }
    }

    /**
     * AC, not fast
     */
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        int m = board.length;
        if (m == 0) {
            return false;
        }
        int n = board[0].length;
        if (n == 0) {
            return false;
        }
        List<Point> spt = new ArrayList<Point>();
        char sc = word.charAt(0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == sc) {
                    spt.add(new Point(i, j));
                }
            }
        }
        if (spt.size() == 0) {
            return false;
        }
        if (word.length() == 1) {
            return true;
        }
        Set<Point> ptset = new HashSet<Point>();
        for (Point pt : spt) {
            ptset.clear();
            ptset.add(pt);
            if (exist(board, word, 1, ptset, pt)) {
                return true;
            }
        }
        return false;
    }

    public boolean exist(char[][] board, String word, int sidx, Set<Point> ptset, Point pt) {
        char c = word.charAt(sidx);
        Point npt = new Point();
        npt.x = pt.x - 1;
        npt.y = pt.y;
        if (npt.x >= 0 && !ptset.contains(npt) && board[npt.x][npt.y] == c) {
            if (sidx == word.length() - 1) {
                return true;
            }
            ptset.add(npt);
            boolean b = exist(board, word, sidx + 1, ptset, npt);
            if (b) {
                return true;
            }
            ptset.remove(npt);
        }

        npt.x = pt.x + 1;
        npt.y = pt.y;
        if (npt.x < board.length && !ptset.contains(npt) && board[npt.x][npt.y] == c) {
            if (sidx == word.length() - 1) {
                return true;
            }
            ptset.add(npt);
            boolean b = exist(board, word, sidx + 1, ptset, npt);
            if (b) {
                return true;
            }
            ptset.remove(npt);
        }

        npt.x = pt.x;
        npt.y = pt.y - 1;
        if (npt.y >= 0 && !ptset.contains(npt) && board[npt.x][npt.y] == c) {
            if (sidx == word.length() - 1) {
                return true;
            }
            ptset.add(npt);
            boolean b = exist(board, word, sidx + 1, ptset, npt);
            if (b) {
                return true;
            }
            ptset.remove(npt);
        }

        npt.x = pt.x;
        npt.y = pt.y + 1;
        if (npt.y < board[0].length && !ptset.contains(npt) && board[npt.x][npt.y] == c) {
            if (sidx == word.length() - 1) {
                return true;
            }
            ptset.add(npt);
            boolean b = exist(board, word, sidx + 1, ptset, npt);
            if (b) {
                return true;
            }
            ptset.remove(npt);
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] a = new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        Solution s = new Solution();
        System.out.println(s.exist(a, "ABCCED"));
        System.out.println(s.exist(a, "SEE"));
        System.out.println(s.exist(a, "ABCB"));
        System.out.println(s.exist(a, "ABCC"));
        System.out.println(s.exist(a, "ABCCC"));
    }
}