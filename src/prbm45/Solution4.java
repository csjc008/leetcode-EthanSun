package prbm45;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 45. <b>Jump Game II</b><br>
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.<br>
 *
 * Each element in the array represents your maximum jump length at that
 * position.<br>
 *
 * Your goal is to reach the last index in the minimum number of jumps.<br>
 *
 * For example: Given array A = [2,3,1,1,4]<br>
 *
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from
 * index 0 to 1, then 3 steps to the last index.)<br>
 *
 * <b>Note</b>: You can assume that you can always reach the last index.<br>
 *
 */
public class Solution4 {

    public class Params {
        public Params() {
        }

        public Params(Integer idx, Integer leap) {
            this.idx = idx;
            this.leap = leap;
        }

        public Integer idx;
        public Integer leap;
    }

    /**
     * AC<br>
     * Time Complexity is O(nlogn)
     *
     */
    public int jump(int[] nums) {
        int len = nums.length;
        List<Params> plist = new ArrayList<Params>();
        int[] val = new int[len];
        for (int i = 0; i < len - 1; i++) {
            val[i] = Integer.MAX_VALUE;
        }
        val[len - 1] = 0;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] > 0) {
                plist.add(new Params(i, nums[i]));
            }
        }
        Collections.sort(plist, new Comparator<Params>() {
            @Override
            public int compare(Params o1, Params o2) {
                if ((o1.idx + o1.leap) > (o2.idx + o2.leap)) {
                    return 1;
                } else if ((o1.idx + o1.leap) < (o2.idx + o2.leap)) {
                    return -1;
                }
                return 0;
            }
        });

        Deque<Integer> prevlist = new LinkedList<Integer>();
        prevlist.addFirst(len - 1);
        while (!prevlist.isEmpty() && !plist.isEmpty()) {
            Integer marker = prevlist.pollLast();
            int plen = plist.size();
            for (int i = plen - 1; i >= 0; i--) {
                Params p = plist.get(i);
                if (p.idx < marker && (p.idx + p.leap) >= marker) {
                    // p.idx can reach marker in one step
                    if (val[p.idx] > val[marker] + 1) {
                        val[p.idx] = val[marker] + 1;
                        prevlist.addFirst(p.idx);
                        plist.remove(i);
                    }

                }
                if ((p.idx + p.leap) < marker) {
                    break;
                }
            }
        }
        return val[0];
    }

    public static void main(String[] args) {
        Solution4 s = new Solution4();
        System.out.println(s.jump(new int[] { 9, 8, 2, 2, 0, 2, 2, 0, 4, 1, 5, 7, 9, 6, 6, 0, 6, 5, 0, 5 }));
        System.out.println(s.jump(new int[] { 5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9,
                6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5 }));
        System.out.println(s.jump(new int[] { 8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0,
                4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2,
                5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5 }));
        System.out.println(s.jump(new int[] { 2, 3, 1, 1, 4 }));
        System.out.println(s.jump(new int[] { 2, 3, 0, 1, 4 }));
        int size = 25002;
        int[] l = new int[size];
        for (int i = 0; i < size - 2; i++) {
            l[i] = (size - i - 2);
        }
        l[size - 2] = 1;
        l[size - 1] = 0;
        System.out.println(s.jump(l));
    }
}