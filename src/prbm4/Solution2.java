package prbm4;

public class Solution2 {
    /**
     * AC Time Complexity: O(log(m+n))
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length) {
            int[] numst = nums1;
            nums1 = nums2;
            nums2 = numst;
        }
        int l1 = nums1.length;
        int l2 = nums2.length;
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
        boolean isOdd = (l1 + l2) % 2 == 1;
        if (isOdd) {
            return findNth(nums1, 0, l1 - 1, nums2, 0, l2 - 1, (l1 + l2) / 2 + 1);
        } else {
            return ((double) findNth(nums1, 0, l1 - 1, nums2, 0, l2 - 1, (l1 + l2) / 2)
                    + (double) findNth(nums1, 0, l1 - 1, nums2, 0, l2 - 1, (l1 + l2) / 2 + 1)) / 2;
        }
    }

    public int findNth(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2, int nth) {
        if (s1 == e1) {
            if (s2 == e2) {
                return nth == 1 ? Math.min(nums1[s1], nums2[s2]) : Math.max(nums1[s1], nums2[s2]);
            }
            int nval = nums2[nth + s2 - 1];
            if (nval <= nums1[s1]) {
                return nval;
            } else {
                return Math.max(nums1[s1], nums2[nth + s2 - 2]);
            }
        }
        if (s2 == e2) {
            return findNth(nums2, s2, e2, nums1, s1, e1, nth);
        }
        int p1 = (s1 + e1) / 2;
        int p2 = (s2 + e2) / 2;
        if (nums1[p1] >= nums2[p2]) {
            boolean f1 = false;
            boolean f2 = false;
            int lmargin = p1 - s1 + 1 + p2 - s2 + 1;
            int ns1 = s1;
            int ne1 = e1;
            int ns2 = s2;
            int ne2 = e2;
            int nnth = nth;
            if (nth < lmargin && p1 > 0) {
                // nums1: the right of p1(including) can be discarded
                ne1 = p1 - 1;
                f1 = true;
            } else if (nth <= lmargin) {
                // nums1: the right of p1(excluding) can be discarded
                ne1 = p1;
                f1 = true;
            }
            int rmargin = e1 - p1 + 1 + e2 - p2 + 1;
            int rnth = e1 - s1 + 1 + e2 - s2 + 1 - nth + 1;
            if (rmargin > rnth && e2 > p2) {
                // nums2: the left of p2(including) can be discarded
                nnth = nth - (p2 - s2) - 1;
                ns2 = p2 + 1;
                f2 = true;
            } else if (rmargin >= rnth) {
                // nums2: the left of p2(excluding) can be discarded
                nnth = nth - (p2 - s2);
                ns2 = p2;
                f2 = true;
            }
            if (f1 || f2) {
                return findNth(nums1, ns1, ne1, nums2, ns2, ne2, nnth);
            }
        }
        return findNth(nums2, s2, e2, nums1, s1, e1, nth);
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        // System.out.println(s.findNth(new int[] { 1 }, 0, 0, new int[] { 3, 4
        // }, 0, 1, 2));
        // System.out.println(s.findNth(new int[] { 3 }, 0, 0, new int[] { 1, 4
        // }, 0, 1, 2));
        // System.out.println(s.findNth(new int[] { 1, 2 }, 0, 1, new int[] { 3,
        // 4 }, 0, 1, 2));
        // System.out.println(s.findNth(new int[] { 1, 2 }, 0, 1, new int[] { 3,
        // 4 }, 0, 1, 3));
        // System.out.println(s.findNth(new int[] { 1, 2 }, 0, 1, new int[] { 3,
        // 4 }, 0, 1, 4));
        // System.out.println(s.findNth(new int[] { 1, 2 }, 0, 1, new int[] { 3,
        // 4 }, 0, 1, 1));
        // System.out.println(s.findNth(new int[] { 1, 2, 5 }, 0, 2, new int[] {
        // 3, 4 }, 0, 1, 3));
        // System.out.println(s.findNth(new int[] { 1, 4 }, 0, 1, new int[] { 2,
        // 3 }, 0, 1, 2));
        // System.out.println(s.findNth(new int[] { 1, 4 }, 0, 1, new int[] { 2,
        // 3 }, 0, 1, 3));
        System.out.println(s.findNth(new int[] { 1 }, 0, 0, new int[] { 2 }, 0, 0, 2));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 4 }, new int[] { 2, 3 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 2, 3 }, new int[] { 1, 4 }));
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
        System.out.println(s.findMedianSortedArrays(new int[] { 1 }, new int[] { 2 }));
    }
}
