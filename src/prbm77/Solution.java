package prbm77;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. <b>Combinations</b><br>
 * Given two integers n and k, return all possible combinations of k numbers out
 * of 1 ... n.<br>
 *
 * For example,<br>
 * If n = 4 and k = 2, a solution is:<br>
 *
 * [<br>
 * [2,4],<br>
 * [3,4],<br>
 * [2,3],<br>
 * [1,2],<br>
 * [1,3],<br>
 * [1,4],<br>
 * ]<br>
 *
 */
public class Solution {
    /**
     * AC, fast enough, may have an unrecursive solution
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> retList = new ArrayList<List<Integer>>();
        if (n < k) {
            return retList;
        }
        if (n == k) {
            List<Integer> ret = new ArrayList<Integer>();
            for (int i = 1; i <= n; i++) {
                ret.add(i);
            }
            retList.add(ret);
            return retList;
        }
        if (k == 1) {
            for (int i = 1; i <= n; i++) {
                List<Integer> ret = new ArrayList<Integer>();
                ret.add(i);
                retList.add(ret);
            }
            return retList;
        }
        List<List<Integer>> sub1 = combine(n - 1, k);
        List<List<Integer>> sub2 = combine(n - 1, k - 1);
        for (List<Integer> s : sub2) {
            s.add(n);
            sub1.add(s);
        }
        return sub1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.combine(3, 1));
        System.out.println(s.combine(4, 2));
        System.out.println(s.combine(4, 3));
    }
}