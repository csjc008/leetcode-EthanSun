package prbm45;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution5 {

    /**
     * AC<br>
     * Time Complexity is O(nlogn)<br>
     * simpler structures, a little faster
     *
     */
    public int jump(final int[] nums) {
        int len = nums.length;
        List<Integer> plist = new ArrayList<Integer>();
        int[] val = new int[len];
        for (int i = 0; i < len - 1; i++) {
            val[i] = Integer.MAX_VALUE;
        }
        val[len - 1] = 0;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] > 0) {
                plist.add(i);
            }
        }
        Collections.sort(plist, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if ((o1 + nums[o1]) > (o2 + nums[o2])) {
                    return 1;
                } else if ((o1 + nums[o1]) < (o2 + nums[o2])) {
                    return -1;
                }
                return 0;
            }
        });
        int[] prevlist = new int[len];
        int s = 0, e = 1, size = 1;
        prevlist[0] = len - 1;
        while (size > 0 && !plist.isEmpty()) {
            Integer marker = prevlist[s];
            s++;
            size--;
            int plen = plist.size();
            for (int i = plen - 1; i >= 0; i--) {
                Integer p = plist.get(i);
                if (p >= marker) {
                    continue;
                }
                if ((p + nums[p]) >= marker) {
                    // p can reach marker in one step
                    if (val[p] > val[marker] + 1) {
                        val[p] = val[marker] + 1;
                        prevlist[e] = p;
                        e++;
                        size++;
                        plist.remove(i);
                    }
                } else {
                    break;
                }
            }
        }
        return val[0];
    }

    public static void main(String[] args) {
        Solution5 s = new Solution5();
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