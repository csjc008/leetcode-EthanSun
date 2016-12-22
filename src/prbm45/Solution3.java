package prbm45;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * TLE
 *
 */
public class Solution3 {

    public class Params {
        public Integer jumped = 0;
        public Integer start  = 0;
        public Integer prev   = 0;
        public boolean isEnd  = false;
        @Override
        public String toString() {
            return "[" + start + "," + jumped + "," + prev + "]";
        }
    }

    public int jump(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> recm = new HashMap<Integer, Integer>();
        Map<Integer, Integer> tmpm = new HashMap<Integer, Integer>();
        Deque<Params> q = new LinkedList<Params>();
        Params pe = new Params();
        pe.isEnd = true;
        q.push(pe);
        q.push(new Params());
        tmpm.put(0, Integer.MAX_VALUE);
        while (!q.isEmpty()) {
            Params p = q.pop();
            if (p.isEnd) {
                int j = tmpm.get(p.start);
                if (!recm.containsKey(p.start)) {
                    recm.put(p.start, tmpm.get(p.start));
                } else {
                    j = recm.get(p.start);
                }
                if (j < Integer.MAX_VALUE && tmpm.get(p.prev) > (j + 1)) {
                    tmpm.put(p.prev, j + 1);
                }
                continue;
            }
            if (recm.containsKey(p.start)) {
                int j = recm.get(p.start);
                if (j < Integer.MAX_VALUE) {
                    if (tmpm.get(p.prev) > (j + 1)) {
                        tmpm.put(p.prev, j + 1);
                    }
                }
                continue;
            }
            if (p.start == len - 1) {
                tmpm.put(p.start, 0);
                if (tmpm.get(p.prev) > 1) {
                    tmpm.put(p.prev, 1);
                }
                continue;
            }
            if (nums[p.start] == 0) {
                continue;
            }
            if (nums[p.start] + p.start >= len - 1) {
                if (tmpm.get(p.start) > 1) {
                    tmpm.put(p.start, 1);
                }
                if (tmpm.get(p.prev) > 2) {
                    tmpm.put(p.prev, 2);
                }
                continue;
            }
            for (int j = 1; j <= nums[p.start]; j++) {
                // for (int j = nums[p.start]; j >= 1; j--) {
                Params npe = new Params();
                npe.start = p.start + j;
                npe.prev = p.start;
                npe.isEnd = true;
                q.push(npe);
                Params np = new Params();
                np.jumped = p.jumped + 1;
                np.start = p.start + j;
                np.prev = p.start;
                q.push(np);
                if (!tmpm.containsKey(p.start + j)) {
                    tmpm.put(p.start + j, Integer.MAX_VALUE);
                }
            }
        }
        return recm.get(0);
    }

    public static void main(String[] args) {
        Solution3 s = new Solution3();
        // System.out.println(s.jump(new int[] { 9, 8, 2, 2, 0, 2, 2, 0, 4, 1,
        // 5, 7, 9, 6, 6, 0, 6, 5, 0, 5 }));
        // System.out.println(s.jump(new int[] { 5, 6, 4, 4, 6, 9, 4, 4, 7, 4,
        // 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9,
        // 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5 }));
        // System.out.println(s.jump(new int[] { 8, 2, 4, 4, 4, 9, 5, 2, 5, 8,
        // 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0,
        // 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1,
        // 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2,
        // 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7,
        // 5 }));
        // System.out.println(s.jump(new int[] { 2, 3, 1, 1, 4 }));
        // System.out.println(s.jump(new int[] { 2, 3, 0, 1, 4 }));
        int size = 2502;
        int[] l = new int[size];
        for (int i = 0; i < size - 2; i++) {
            l[i] = (size - i - 2);
        }
        l[size - 2] = 1;
        l[size - 1] = 0;
        System.out.println(s.jump(l));
    }
}