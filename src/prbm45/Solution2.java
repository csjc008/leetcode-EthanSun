package prbm45;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Badly TLE
 *
 */
public class Solution2 {

    public class Params {
        public Integer jumped = 0;
        public Integer start  = 0;

        @Override
        public String toString() {
            return "[" + start + "," + jumped + "]";
        }
    }

    public int jump(int[] nums) {
        int len = nums.length;
        // Map<Integer, Integer> recm = new HashMap<Integer, Integer>();
        Deque<Params> q = new LinkedList<Params>();
        q.push(new Params());
        int min = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Params p = q.pop();
            // System.out.println("pop:" + p);
            // if (recm.containsKey(p.start)) {
            // int j = recm.get(p.start);
            // if (j < Integer.MAX_VALUE) {
            // min = (j + p.jumped) < min ? (j + p.jumped) : min;
            // }
            // continue;
            // }
            if (p.jumped > min) {
                continue;
            }
            if (p.start == len - 1) {
                min = p.jumped < min ? p.jumped : min;
                // recm.put(p.start, 0);
                continue;
            }
            if (nums[p.start] == 0) {
                // recm.put(p.start, Integer.MAX_VALUE);
                continue;
            }
            if (nums[p.start] + p.start >= len - 1) {
                // recm.put(p.start, 1);
                min = (p.jumped + 1) < min ? (p.jumped + 1) : min;
                continue;
            }

            for (int j = 1; j <= nums[p.start]; j++) {
                Params np = new Params();
                np.jumped = p.jumped + 1;
                np.start = p.start + j;
                q.push(np);
                // System.out.println("add:" + np);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.jump(new int[] { 9, 8, 2, 2, 0, 2, 2, 0, 4, 1, 5, 7, 9, 6, 6, 0, 6, 5, 0, 5 }));
        // System.out.println(s.jump(new int[] { 5, 6, 4, 4, 6, 9, 4, 4, 7, 4,
        // 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9,
        // 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5 }));
        System.out.println(s.jump(new int[] { 8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0,
                4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2,
                5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5 }));
        // System.out.println(s.jump(new int[] { 2, 3, 1, 1, 4 }));
        // System.out.println(s.jump(new int[] { 2, 3, 0, 1, 4 }));
        // int size = 99999;
        // int[] l = new int[size];
        // for (int i = 0; i < size; i++) {
        // l[i] = 1;
        // }
        // System.out.println(s.jump(l));
    }
}