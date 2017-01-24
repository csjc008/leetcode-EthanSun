package prbm80;

/**
 * 80. <b>Remove Duplicates from Sorted Array II</b><br>
 * Follow up for "Remove Duplicates":<br>
 * What if duplicates are allowed at most twice?<br>
 *
 * For example,<br>
 * Given sorted array nums = [1,1,1,2,2,3],<br>
 *
 * Your function should return length = 5, with the first five elements of nums
 * being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new
 * length.<br>
 */
public class Solution {
    /**
     * AC
     */
    public int removeDuplicates(int[] nums) {
        Integer current = null;
        Integer prev = null;
        int size = 0;
        for (int n : nums) {
            if (current == null || current.intValue() != n || prev == null || prev.intValue() != current.intValue()) {
                prev = current;
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
        nums = new int[] { 1, 1, 1, 2, 2, 3 };
        System.out.println(s.removeDuplicates(nums));
    }
}