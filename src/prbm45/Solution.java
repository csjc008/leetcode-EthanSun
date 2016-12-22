package prbm45;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * using recusion, StackOverflow when input is very huge
     */
    public int jump(int[] nums) {
        Map<Integer, Integer> recm = new HashMap<Integer, Integer>();
        return jump(nums, 0, recm);
    }

    public int jump(int[] nums, int start, Map<Integer, Integer> recm) {
        if (start == nums.length - 1) {
            return 0;
        }
        if (recm.containsKey(start)) {
            return recm.get(start);
        }
        if (nums[start] == 0) {
            recm.put(start, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }
        if (nums[start] + start >= nums.length - 1) {
            recm.put(start, 1);
            return 1;
        }
        int min = Integer.MAX_VALUE;
        for (int j = 1; j <= nums[start]; j++) {
            int ts = jump(nums, start + j, recm);
            if (ts < Integer.MAX_VALUE) {
                min = (ts + 1) < min ? (ts + 1) : min;
            }
        }
        recm.put(start, min);
        return min;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        // System.out.println(s.jump(new int[] { 2, 3, 1, 1, 4 }));
        // System.out.println(s.jump(new int[] { 2, 3, 0, 1, 4 }));
        // System.out.println(s.jump(new int[] { 5, 6, 4, 4, 6, 9, 4, 4, 7, 4,
        // 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9,
        // 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5 }));
        // System.out.println(s.jump(new int[] { 9, 8, 2, 2, 0, 2, 2, 0, 4, 1,
        // 5, 7, 9, 6, 6, 0, 6, 5, 0, 5 }));
        // System.out.println(s.jump(new int[] { 8, 2, 4, 4, 4, 9, 5, 2, 5, 8,
        // 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0,
        // 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1,
        // 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2,
        // 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7,
        // 5 }));
        int size = 2502;
        int[] l = new int[size];
        for (int i = 0; i < size - 2; i++) {
            l[i] = (size - i - 2);
        }
        l[size - 2] = 1;
        l[size - 1] = 0;
        System.out.println(s.jump(l));
    }
}