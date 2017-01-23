package prbm75;

/**
 * 75. <b>Sort Colors</b><br>
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.<br>
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.<br>
 *
 * Note:<br>
 * You are not suppose to use the library's sort function for this problem.<br>
 *
 *
 * Follow up:<br>
 * A rather straight forward solution is a two-pass algorithm using counting
 * sort.<br>
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite
 * array with total number of 0's, then 1's and followed by 2's.<br>
 *
 * Could you come up with an one-pass algorithm using only constant space?<br>
 *
 */
public class Solution {

    /**
     * AC, but two pass
     */
    public void sortColors(int[] nums) {
        int[] c = new int[3];
        for (int n : nums) {
            c[n]++;
        }
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < c[i]; j++) {
                nums[idx] = i;
                idx++;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] a1 = new int[] { 0, 1, 2 };
        int[] a2 = new int[] { 2, 0, 1 };
        int[] a3 = new int[] { 2, 2, 1, 1, 0, 1, 2 };
        s.sortColors(a1);
        s.sortColors(a2);
        s.sortColors(a3);
        System.out.println(a1);
    }
}