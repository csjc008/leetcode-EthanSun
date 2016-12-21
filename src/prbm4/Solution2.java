package prbm4;

public class Solution2 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length) {
            int[] numst = nums1;
            nums1 = nums2;
            nums2 = numst;
        }
        int l1 = nums1.length;
        int l2 = nums2.length;
        boolean isOdd = (l1 + l2) % 2 == 1;
        if (l1 == 0) {
            if (l2 % 2 == 1) {
                return nums2[l2 / 2];
            } else {
                return ((double) nums2[l2 / 2] + nums2[l2 / 2 - 1]) / 2;
            }
        }
        if (l2 == 0) {
            if (l1 % 2 == 1) {
                return nums1[l1 / 2];
            } else {
                return ((double) nums1[l1 / 2] + nums1[l1 / 2 - 1]) / 2;
            }
        }
        if (nums1[l1 - 1] <= nums2[0]) {
            if (isOdd) {
                return nums1[(l1 + l2) / 2];
            } else {
                return ((double) nums1[(l1 + l2) / 2 - 1] + (l1 == l2 ? nums2[0] : nums1[(l1 + l2) / 2])) / 2;
            }
        }
        int l = 0;
        int r = l1 - 1;
        int p = (l1 - 1) / 2;
        int q = 0;
        int midL=0;

        while (true) {
            q = (l1 + l2) / 2 - p - 1;
            // if (p == 0 || p == (l1 - 1)) {
            // midL = Math.max(nums1[p], getElement(nums2, q));
            // break;
            // }
            if (l == r) {
                if (isOdd) {
                    midL = Math.max(nums1[p], getElement(nums2, nums1, q));
                } else {
                    midL = Math.min(nums1[p], getElement(nums2, nums1, q));
                }
                break;
            }
            if (nums1[p] > getElement(nums1, nums2, q + 1)) {
                // p should move left
                r = p;
                p = (l + r) / 2;
                continue;
            }
            if (getElement(nums1, nums2, q) > getElement(nums2, nums1, p + 1)) {
                // p should move right
                l = p;
                p = (l + r) / 2;
                if (l == p && l < r) {
                    l++;
                    p++;
                }
                continue;
            }
            if (isOdd) {
                midL = Math.max(nums1[p], getElement(nums2, nums1, q));
            } else {
                midL = Math.min(nums1[p], getElement(nums2, nums1, q));
            }
            break;
        }
        if (isOdd) {
            return midL;
        } else {
            int midR = Math.max(nums1[p], getElement(nums2, nums1, q));
            return ((double) midL + midR) / 2;
        }
    }

    public int getElement(int[] nums1, int[] nums2, int idx) {
        if (idx > (nums1.length - 1)) {
            return nums2[idx - nums1.length];
        }
        return nums1[idx];
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2, 4, 5, 6, 7, 8 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1 }, new int[] { 2, 3, 4 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1 }, new int[] { 2, 3 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 2 }, new int[] { 1, 3 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 2 }, new int[] { 1, 2, 3 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 1, 3 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 2, 3 }, new int[] { 3, 4 }));
        System.out.println(s.findMedianSortedArrays(new int[] {}, new int[] { 1 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 1, 2 }));

    }
}
