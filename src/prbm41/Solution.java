package prbm41;

/**
 * 41. First Missing Positive<br>
 * Given an unsorted integer array, find the first missing positive integer.<br>
 *
 * For example, <br>
 * Given [1,2,0] return 3, <br>
 * and [3,4,-1,1] return 2.<br>
 *
 * Your algorithm should run in O(n) time and uses constant space.
 */
public class Solution {
    /**
     * This algorithm's time complexity is O(n), consumes constant space.<br>
     * For all numbers within [1,size] range, it should occupy the array
     * position of index 0 to size-1.<br>
     * For other numbers, leave as it is(whatever position). <br>
     * For same numbers within [1,size] range, either one occupies the right
     * position is ok.<br>
     * For each compare and swap operation of the following code, one element is
     * at the right position. So for n1 (n1&lt;n) rounds compare and swap and
     * (n-n1) rounds compare, every element is at the right place.
     */
    public int firstMissingPositive(int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return 1;
        }
        for (int i = 0; i < size; i++) {
            while (true) {
                if (nums[i] <= 0 || nums[i] > size) {
                    break;
                }
                if (nums[i] != (i + 1)) {
                    int cur = nums[i];
                    int t = nums[cur - 1];
                    if (cur == t) {
                        // otherwise will loop infinitelly
                        break;
                    }
                    nums[cur - 1] = cur;
                    nums[i] = t;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (nums[i] != (i + 1)) {
                return i + 1;
            }
        }
        return size + 1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.firstMissingPositive(new int[] { 1, 1 }));
        System.out.println(s.firstMissingPositive(new int[] { 1, 2, 0 }));
        System.out.println(s.firstMissingPositive(new int[] { 3, 4, -1, 1 }));
        System.out.println(s.firstMissingPositive(new int[] { 2 }));
    }
}