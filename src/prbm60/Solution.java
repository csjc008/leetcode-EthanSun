package prbm60;

/**
 * 60. <b>Permutation Sequence</b><br>
 * The set [1,2,3,…,n] contains a total of n! unique permutations.<br>
 *
 * By listing and labeling all of the permutations in order,<br>
 * We get the following sequence (ie, for n = 3):<br>
 *
 * "123"<br>
 * "132"<br>
 * "213"<br>
 * "231"<br>
 * "312"<br>
 * "321"<br>
 * Given n and k, return the kth permutation sequence.<br>
 *
 * Note: Given n will be between 1 and 9 inclusive.<br>
 */
public class Solution {

    /**
     * AC, Time Complexity is O(n^2)
     */
    public String getPermutation(int n, int k) {
        if (n <= 0) {
            return "";
        }
        int[] div = new int[n];
        div[0] = 1;
        for (int i = 1; i < n; i++) {
            div[i] = div[i - 1] * i;
        }
        StringBuilder sb = new StringBuilder();
        int[] occu = new int[n];
        int rest = k - 1;
        for (int i = n - 1; i >= 0; i--) {
            int nth = rest / div[i];
            rest = rest % div[i];
            sb.append(getnth(occu, nth));
        }
        return sb.toString();
    }

    private int getnth(int[] occu, int nth) {
        int cnt = 0;
        for (int i = 0; i < occu.length; i++) {
            if (occu[i] > 0) {
                continue;
            }
            if (cnt == nth) {
                occu[i] = 1;
                return i + 1;
            }
            cnt++;
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.getPermutation(1, 1));
        System.out.println(s.getPermutation(2, 2));
        System.out.println(s.getPermutation(3, 1));
        System.out.println(s.getPermutation(3, 3));
        System.out.println(s.getPermutation(3, 5));
        System.out.println(s.getPermutation(9, 100));
    }
}