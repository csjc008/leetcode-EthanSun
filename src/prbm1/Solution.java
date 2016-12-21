package prbm1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1. <b>Two Sum</b><br>
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.<br>
 *
 * You may assume that each input would have exactly one solution.<br>
 *
 * Example:<br>
 * Given nums = [2, 7, 11, 15], target = 9,<br>
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,<br>
 * return [0, 1].
 *
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {

        class Pair {
            int idx;
            int val;
        }

        Pair[] pnums = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pnums[i] = new Pair();
            pnums[i].idx = i;
            pnums[i].val = nums[i];
        }

        Arrays.sort(pnums, new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.val > o2.val) {
                    return 1;
                } else if (o1.val < o2.val) {
                    return -1;
                } else {
                    return 0;
                }
            }

        });

        int lp = 0;
        int rp = nums.length - 1;
        while (true) {
            int sum = pnums[lp].val + pnums[rp].val;
            if (sum == target) {
                break;
            } else if (sum > target) {
                rp--;
            } else {
                lp++;
            }
        }
        if (pnums[lp].idx < pnums[rp].idx) {
            return new int[] { pnums[lp].idx, pnums[rp].idx };
        } else {
            return new int[] { pnums[rp].idx, pnums[lp].idx };
        }
    }

    public static void main(String[] args) {
        int[] a1 = { 3, 2, 4 };
        Solution s = new Solution();
        int[] ret = s.twoSum(a1, 6);
        System.out.println("" + ret[0] + " " + ret[1]);
    }
}
