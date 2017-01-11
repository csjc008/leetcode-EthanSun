package prbm215;

/**
 * 215. <b>Kth Largest Element in an Array</b><br>
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.<br>
 *
 * For example,<br>
 * Given [3,2,1,5,6,4] and k = 2, return 5.<br>
 *
 * <b>Note:</b> <br>
 * You may assume k is always valid, 1 ≤ k ≤ array's length.<br>
 *
 */
public class Solution {

    /**
     * AC, O(n), can be proved
     */
    public int findKthLargest(int[] nums, int k) {
        return getKth(nums, 0, nums.length - 1, k);
    }

    public int getKth(int[] nums, int si, int ei, int k) {
        if (si == ei && k == 1) {
            return nums[si];
        }
        if (ei - si == 1) {
            return k == 1 ? Math.max(nums[si], nums[ei]) : Math.min(nums[si], nums[ei]);
        }
        int p = nums[ei];
        int s = si;
        int e = ei - 1;
        while (s < e) {
            while (s < e && nums[s] >= p) {
                s++;
            }
            while (s < e && nums[e] <= p) {
                e--;
            }
            if (s < e) {
                // swap
                int tmp = nums[s];
                nums[s] = nums[e];
                nums[e] = tmp;
            }
        }
        if (p >= nums[s]) {
            // number p to position s
            int tmp = nums[s];
            nums[s] = p;
            nums[ei] = tmp;
        } else {
            if (k - 1 == ei - si) {
                return p;
            } else {
                return getKth(nums, si, ei - 1, k);
            }
        }
        if (s - si == k - 1) {
            return p;
        } else if (s - si > k - 1) {
            return getKth(nums, si, s - 1, k);
        } else {
            return getKth(nums, s, ei, k - (s - si));
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findKthLargest(new int[] { 1, 1 }, 2));
        System.out.println(s.findKthLargest(new int[] { 1, 1 }, 1));
        System.out.println(s.findKthLargest(new int[] { 1, 1, 1 }, 2));
        System.out.println(s.findKthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 2));
        System.out.println(s.findKthLargest(new int[] { 3, 2, 1, 6, 5, 4 }, 2));
        System.out.println(s.findKthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 3));
        System.out.println(s.findKthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 1));
        System.out.println(s.findKthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 4));
        System.out.println(s.findKthLargest(new int[] { 1, 1, 1, 1, 1 }, 2));
        System.out.println(s.findKthLargest(new int[] { 1 }, 1));
    }
}