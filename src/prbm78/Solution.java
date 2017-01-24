package prbm78;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. <b>Subsets</b><br>
 * Given a set of distinct integers, nums, return all possible subsets.<br>
 *
 * Note: The solution set must not contain duplicate subsets.<br>
 *
 * For example,<br>
 * If nums = [1,2,3], a solution is:<br>
 *
 * [<br>
 * [3],<br>
 * [1],<br>
 * [2],<br>
 * [1,2,3],<br>
 * [1,3],<br>
 * [2,3],<br>
 * [1,2],<br>
 * []<br>
 * ]<br>
 *
 */
public class Solution {
    /**
     * AC
     */
    public List<List<Integer>> subsets(int[] nums) {
        return subsets(nums, nums.length);
    }

    private List<List<Integer>> subsets(int[] nums, int n) {
        if (n == 0) {
            List<List<Integer>> retList = new ArrayList<List<Integer>>();
            retList.add(new ArrayList<Integer>());
            return retList;
        }
        List<List<Integer>> subList = subsets(nums, n - 1);
        int m = subList.size();
        for (int i = 0; i < m; i++) {
            List<Integer> nlist = new ArrayList<Integer>(subList.get(i));
            nlist.add(nums[n - 1]);
            subList.add(nlist);
        }
        return subList;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.subsets(new int[] { 0 }));
        System.out.println(s.subsets(new int[] { 1, 2 }));
        System.out.println(s.subsets(new int[] { 1 }));
        System.out.println(s.subsets(new int[] {}));
        System.out.println(s.subsets(new int[] { 1, 2, 3, 4 }));
    }
}