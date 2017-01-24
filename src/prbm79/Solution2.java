package prbm79;

public class Solution2 {

    /**
     * tuned, AC, fast enough
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
        char sc = word.charAt(0);
        boolean[][] bmat = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == sc) {
                    if (word.length() == 1) {
                        return true;
                    }
                    bmat[i][j] = true;
                    if (exist(board, word, 1, bmat, i, j)) {
                        return true;
                    }
                    bmat[i][j] = false;
                }
            }
        }

        return false;
    }

    private boolean exist(char[][] board, String word, int sidx, boolean[][] bmat, int x, int y) {
        int nx, ny;
        nx = x - 1;
        ny = y;
        if (nx >= 0 && forward(board, word, sidx, bmat, nx, ny)) {
            return true;
        }
        nx = x + 1;
        ny = y;
        if (nx < board.length && forward(board, word, sidx, bmat, nx, ny)) {
            return true;
        }
        nx = x;
        ny = y - 1;
        if (ny >= 0 && forward(board, word, sidx, bmat, nx, ny)) {
            return true;
        }
        nx = x;
        ny = y + 1;
        if (ny < board[0].length && forward(board, word, sidx, bmat, nx, ny)) {
            return true;
        }
        return false;
    }

    private boolean forward(char[][] board, String word, int sidx, boolean[][] bmat, int nx, int ny) {
        if (!bmat[nx][ny] && board[nx][ny] == word.charAt(sidx)) {
            if (sidx == word.length() - 1) {
                return true;
            }
            bmat[nx][ny] = true;
            boolean b = exist(board, word, sidx + 1, bmat, nx, ny);
            if (b) {
                return true;
            }
            bmat[nx][ny] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] a = new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        Solution2 s = new Solution2();
        System.out.println(s.exist(a, "ABCCED"));
        System.out.println(s.exist(a, "SEE"));
        System.out.println(s.exist(a, "ABCB"));
        System.out.println(s.exist(a, "ABCC"));
        System.out.println(s.exist(a, "ABCCC"));
        System.out.println(s.exist(new char[][] { { 'a' } }, "a"));
    }
}