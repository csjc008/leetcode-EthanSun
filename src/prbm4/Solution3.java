package prbm4;

public class Solution3 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
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
        int min = Math.min(nums1[0], nums2[0]);
        int max = Math.max(nums1[l1 - 1], nums2[l2 - 1]);
        if (max == min) {
            return max;
        }
        boolean isOdd = (l1 + l2) % 2 == 1;
        double l = min - 1;
        double r = max + 1;
        double p = (l + r) / 2;
        while (true) {
            // calculate number of elements smaller than p
            int left1 = getBiggestSmallerThanP(nums1, p);
            int left2 = getBiggestSmallerThanP(nums2, p);
            if (left1 + left2 + 2 == (l1 + l2) / 2 + 1) {
                if (isOdd) {
                    if (left1 == -1) {
                        return nums2[left2];
                    } else if (left2 == -1) {
                        return nums1[left1];
                    } else {
                        return Math.max(nums1[left1], nums2[left2]);
                    }
                }
                // even
                if (nums1[left1] >= nums2[left2]) {
                    return (nums1[left1] + (left1 > 0 ? Math.max(nums1[left1 - 1], nums2[left2]) : nums2[left2]))
                            / 2.0d;
                } else {
                    return (nums2[left2] + (left2 > 0 ? Math.max(nums2[left2 - 1], nums1[left1]) : nums1[left1]))
                            / 2.0d;
                }
            } else if (left1 + left2 + 2 == (l1 + l2) / 2 + 2 && (nums1[left1] == nums2[left2])) {
                if (isOdd) {
                    return nums1[left1];
                }
                return (nums2[left2] + Math.max(left2 > 0 ? nums2[left2 - 1] : nums2[left2],
                        left1 > 0 ? nums1[left1 - 1] : nums1[left1])) / 2.0d;
            } else if (left1 + left2 + 2 < (l1 + l2) / 2 + 1) {
                // p move right
                l = p;
                p = (l + r) / 2;
            } else {
                // p move left
                r = p;
                p = (l + r) / 2;
            }
        }
    }

    /**
     * return index
     */
    public int getBiggestSmallerThanP(int[] nums, double dp) {
        if (nums[nums.length - 1] < dp) {
            return nums.length - 1;
        }
        int l = 0;
        int r = nums.length - 1;
        int p = (l + r) / 2;
        while (l != r) {
            if (nums[p] < dp && nums[p + 1] >= dp) {
                break;
            }
            if (nums[p] >= dp) {
                // p move left
                r = p;
                p = (l + r) / 2;
            } else if (nums[p] < dp) {
                // p move right
                l = p;
                p = (l + r) / 2;
                if (l == p) {
                    l++;
                    p++;
                }
            }
        }
        if (nums[p] < dp) {
            return p;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        Solution3 s = new Solution3();
        // System.out.println(s.getBiggestSmallerThanP(new int[] { 1, 2, 3, 4 },
        // -1));
        // System.out.println(s.getBiggestSmallerThanP(new int[] { 1, 2, 3, 4 },
        // 2));
        // System.out.println(s.getBiggestSmallerThanP(new int[] { 1, 2, 3, 4 },
        // 5));
        // System.out.println(s.getBiggestSmallerThanP(new int[] { 1, 4 },
        // 2.5));
        // System.out.println(s.findMedianSortedArrays(new int[] { 1, 4 }, new
        // int[] { 2, 3 }));
        // System.out.println(s.findMedianSortedArrays(new int[] { 2, 3 }, new
        // int[] { 1, 4 }));
        // System.out.println(s.findMedianSortedArrays(new int[] { 1, 3 }, new
        // int[] { 2, 4, 5, 6, 7, 8 }));
        // System.out.println(s.findMedianSortedArrays(new int[] { 1 }, new
        // int[] { 2, 3, 4 }));
        // System.out.println(s.findMedianSortedArrays(new int[] { 1 }, new
        // int[] { 3 }));
        // System.out.println(s.findMedianSortedArrays(new int[] { 1, 2 }, new
        // int[] { 3 }));
        // System.out.println(s.findMedianSortedArrays(new int[] { 1 }, new
        // int[] { 2, 3 }));
        // System.out.println(s.findMedianSortedArrays(new int[] { 1, 3 }, new
        // int[] { 2 }));
        // System.out.println(s.findMedianSortedArrays(new int[] { 2 }, new
        // int[] { 1, 3 }));
        // System.out.println(s.findMedianSortedArrays(new int[] { 2 }, new
        // int[] { 1, 2, 3 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 1, 3, 3 }, new int[] { 1, 1, 3, 3 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 1, 2, 3 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 1, 3 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 2, 3 }, new int[] { 3, 4 }));
        System.out.println(s.findMedianSortedArrays(new int[] {}, new int[] { 1 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 1, 2 }));
        System.out.println(s.findMedianSortedArrays(new int[] { 1 }, new int[] { 2 }));
    }
}
