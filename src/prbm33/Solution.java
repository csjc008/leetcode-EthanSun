package prbm33;

/**
 * 33. Search in Rotated Sorted Array<br>
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <br>
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).<br>
 *
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.<br>
 *
 * You may assume no duplicate exists in the array.<br>
 */
public class Solution {
    public int search(int[] nums, int target) {
        // first, find the pivot
        int size = nums.length;
        int s = 0;
        int e = size - 1;
        int p = (size - 1) / 2;
        while (true) {
            if (nums[p] < nums[(p + size - 1) % size]) {
                break;
            }
            if (p == e) {
                break;
            }
            if (nums[p] > nums[e]) {
                // lowest on the right
                s = p;
                p = (s + e) / 2;
                if (p == s) {
                    p++;
                }
            } else {
                // lowest on the left
                e = p;
                p = (s + e) / 2;
            }
        }
        // second, find the target
        s = 0;
        e = size - 1;
        int c = (size - 1) / 2;
        while (true) {
            if (nums[(c + p) % size] == target) {
                return (c + p) % size;
            }
            if (c == e) {
                return -1;
            }
            if (nums[(c + p) % size] < target) {
                // target on the right
                s = c;
                c = (s + e) / 2;
                if (c == s) {
                    c++;
                }
            } else {
                // target on the left
                e = c;
                c = (s + e) / 2;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        // 4
        System.out.println(s.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0));
        // 5
        System.out.println(s.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 1));
        // 1
        System.out.println(s.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 5));
        // 3
        System.out.println(s.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 7));
        // 6
        System.out.println(s.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 2));
        // 1
        System.out.println(s.search(new int[] { 7, 0, 1, 2, 4, 5, 6 }, 0));
        // 2
        System.out.println(s.search(new int[] { 7, 0, 1, 2, 4, 5, 6 }, 1));
        // 0
        System.out.println(s.search(new int[] { 7, 0, 1, 2, 4, 5, 6 }, 7));
        // -1
        System.out.println(s.search(new int[] { 7, 0, 1, 2, 4, 5, 6 }, 3));
        // -1
        System.out.println(s.search(new int[] { 7, 0, 1, 2, 4, 5, 6 }, 8));
        // -1
        System.out.println(s.search(new int[] { 7, 0, 1, 2, 4, 5, 6 }, -1));
        // 5
        System.out.println(s.search(new int[] { 7, 8, 0, 1, 2, 4, 5, 6 }, 4));
        // 1
        System.out.println(s.search(new int[] { 1, 3 }, 3));
        // 0
        System.out.println(s.search(new int[] { 1, 3 }, 1));
        // -1
        System.out.println(s.search(new int[] { 1, 3 }, 2));
        // -1
        System.out.println(s.search(new int[] { 1, 3 }, 0));
    }
}