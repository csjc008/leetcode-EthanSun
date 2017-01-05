package prbm53;

/**
 * 53. <b>Maximum Subarray</b><br>
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest sum.<br>
 *
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],<br>
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.<br>
 *
 * <b>More practice:</b><br>
 * If you have figured out the O(n) solution, try coding another solution using
 * the divide and conquer approach, which is more subtle.
 *
 */
public class Solution {

    /**
     * AC, Time Complexity is O(n)
     */
    public int maxSubArray(int[] nums) {
        long[] sums = new long[nums.length + 1];
        sums[0] = 0;
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sums[i + 1] = sum;
        }
        long curMin = Long.MAX_VALUE;
        long curMax = Long.MIN_VALUE;
        long maxGap = Long.MIN_VALUE;
        for (int i = 0; i < sums.length - 1; i++) {
            if (sums[i] < curMin) {
                curMin = sums[i];
                curMax = sums[i + 1];
            }
            if (sums[i + 1] > curMax) {
                curMax = sums[i + 1];
            }
            maxGap = (curMax - curMin) > maxGap ? (curMax - curMin) : maxGap;
        }
        return (int) maxGap;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
    }
}