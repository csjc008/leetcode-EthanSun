package prbm1;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public int[] twoSum(int[] nums, int target) {

        class Pair {
            int idx;
            int val;
        }

        Pair[] pnums = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pnums[i] = new Pair();
            pnums[i].idx = i + 1;
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
