package prbm26;

/**
 * 26. <b>Remove Duplicates from Sorted Array</b><br>
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.<br>
 *
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.<br>
 *
 * For example,<br>
 * Given input array nums = [1,1,2],<br>
 *
 * Your function should return length = 2, with the first two elements of nums
 * being 1 and 2 respectively. It doesn't matter what you leave beyond the new
 * length.<br>
 */
public class Solution {
    /**
     * AC
     */
    public int removeDuplicates(int[] nums) {
        Integer current = null;
        int size = 0;
        for (int n : nums) {
            if (current == null || current.intValue() != n) {
                current = n;
                nums[size] = current;
                size++;
            }
        }
        return size;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] { 1, 1, 2 };
        System.out.println(s.removeDuplicates(nums));
        nums = new int[] { 1, 1 };
        System.out.println(s.removeDuplicates(nums));
    }
}