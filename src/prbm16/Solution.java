package prbm16;

/**
 * 16. 3Sum Closest<br>
 * Given an array S of n integers, find three integers in S such that the sum is
 * closest to a given number, target. Return the sum of the three integers. You
 * may assume that each input would have exactly one solution.<br>
 *
 * For example, given array S = {-1 2 1 -4}, and target = 1.<br>
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).<br>
 * passed, 16ms, beats 98.06%
 */
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n > max) {
                max = n;
            }
            if (n < min) {
                min = n;
            }
        }
        int[] bucket = new int[max - min + 1];
        // for (int i = 0; i < bucket.length; i++) {
        // bucket[i] = 0;
        // }
        for (int n : nums) {
            bucket[n - min]++;
        }
        int shift = getClosestShift(bucket, target, min, max, null, null);
        return shift + target;
    }

    public int getClosestShift(int[] bucket, int target, int min, int max, Integer first, Integer second) {
        if (first != null && second != null) {
            if (first == second && bucket[first - min] < 2) {
                return Integer.MAX_VALUE;
            }
            int ideal = target - first - second;
            if (ideal < min) {
                // third to be min
                if ((min == first || min == second) && bucket[0] < 2) {
                    return Integer.MAX_VALUE;
                }
                if (first == min && second == min && bucket[0] < 3) {
                    return Integer.MAX_VALUE;
                }
                return first + second + min - target;
            }
            if (ideal > max) {
                // third to be max
                if ((max == first || max == second) && bucket[bucket.length - 1] < 2) {
                    return Integer.MAX_VALUE;
                }
                if (first == max && second == max && bucket[bucket.length - 1] < 3) {
                    return Integer.MAX_VALUE;
                }
                return first + second + max - target;
            }
            int fpos = first - min;
            int spos = second - min;
            if(fpos==spos&&bucket[fpos]<2){
                return Integer.MAX_VALUE;
            }
            int shift = 0;
            while (true) {
                int pos1 = ideal - min + shift;
                int pos2 = ideal - min - shift;
                if (pos1 < bucket.length && bucket[pos1] > 0) {
                    if (pos1 == fpos && bucket[fpos] < 2) {

                    } else if (pos1 == fpos && pos1 == spos && bucket[fpos] < 3) {

                    } else if (pos1 == spos && bucket[spos] < 2) {

                    } else {
                        return shift;
                    }
                }
                if (pos2 >= 0 && bucket[pos2] > 0) {
                    if (pos2 == fpos && bucket[fpos] < 2) {

                    } else if (pos2 == fpos && pos2 == spos && bucket[fpos] < 3) {

                    } else if (pos2 == spos && bucket[spos] < 2) {

                    } else {
                        return -shift;
                    }
                }
                shift++;
            }
        }
        if (second == null) {
            if (first == null) {
                // all null
                int minShift = Integer.MAX_VALUE;
                for (int i = 0; i < bucket.length; i++) {
                    if (bucket[i] > 0) {
                        int shift = getClosestShift(bucket, target, min, max, i + min, null);
                        if (shift == 0) {
                            return 0;
                        } else {
                            if (Math.abs(shift) < Math.abs(minShift)) {
                                minShift = shift;
                            }
                        }
                    }
                }
                return minShift;
            } else {
                // second null
                int minShift = Integer.MAX_VALUE;
                for (int i = 0; i < bucket.length; i++) {
                    if (bucket[i] > 0) {
                        int shift = getClosestShift(bucket, target, min, max, first, i + min);
                        if (shift == 0) {
                            return 0;
                        } else {
                            if (Math.abs(shift) < Math.abs(minShift)) {
                                minShift = shift;
                            }
                        }
                    }
                }
                return minShift;
            }
        }
        return Integer.MAX_VALUE;
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.threeSumClosest(new int[] { -2, -1, -2, 4, 3, 1, 2, 0 }, 1));
        System.out.println(s.threeSumClosest(new int[] { -1, 2, 1, -4 }, 1));
        System.out.println(s.threeSumClosest(new int[] { -1, 2, 1, -4 }, -1));
        System.out.println(s.threeSumClosest(new int[] { 0, 0, 0 }, 1));
    }
}
