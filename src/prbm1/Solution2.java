package prbm1;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> nmap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            nmap.put(nums[i], i);
        }

        int i = 0;
        int j = 0;
        for (; i < nums.length; i++) {
            if (nmap.containsKey(target - nums[i])) {
                j = nmap.get(target - nums[i]);
                if (i != j) {
                    break;
                }
            }
        }
        i++;
        j++;
        if (i < j) {
            return new int[] { i, j };
        } else {
            return new int[] { j, i };
        }

    }

    public static void main(String[] args) {
        int[] a1 = { 3, 2, 4 };
        Solution2 s = new Solution2();
        int[] ret = s.twoSum(a1, 6);
        System.out.println("" + ret[0] + " " + ret[1]);
    }
}
