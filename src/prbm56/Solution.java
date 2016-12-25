package prbm56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 56. <b>Merge Intervals</b><br>
 * Given a collection of intervals, merge all overlapping intervals.<br>
 *
 * For example,<br>
 * Given [1,3],[2,6],[8,10],[15,18],<br>
 * return [1,6],[8,10],[15,18].<br>
 *
 */
public class Solution {
    /**
     * AC, Time Complexity is O(nlogn)
     */
    public List<Interval> merge(List<Interval> intervals) {
        // solution array
        List<Interval> retlist = new ArrayList<Interval>();
        if (intervals.size() == 0) {
            return retlist;
        }
        // sort the input array
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                // first, put the the interval forward with greater start
                if (o1.start > o2.start) {
                    return 1;
                } else if (o1.start < o2.start) {
                    return -1;
                }
                // second, put the interval forward with greater end
                if (o1.end > o2.end) {
                    return 1;
                } else if (o1.end < o2.end) {
                    return -1;
                }
                return 0;
            }
        });
        // set previous to the first interval
        Interval prev = new Interval(intervals.get(0).start, intervals.get(0).end);
        for (Interval cur : intervals) {
            if (cur.start > prev.end) {
                // if next start is greater than previous end
                // indicates that there will be a new interval
                retlist.add(prev);
                prev = new Interval(cur.start, cur.end);
                continue;
            }
            if (cur.end > prev.end) {
                // if next start is smaller or equal than previous end
                // and next end is greater than previous end
                // incates that there is an overlap
                // extend previous range
                prev.end = cur.end;
            }
        }
        retlist.add(prev);
        return retlist;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.merge(Arrays.asList(new Interval(1, 3), new Interval(2, 6))));
        System.out.println(s.merge(
                Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(15, 18), new Interval(8, 10))));
        System.out.println(s.merge(Arrays.asList(new Interval(2, 4), new Interval(3, 6), new Interval(1, 7))));
    }
}