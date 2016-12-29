package prbm57;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57. <b>Insert Interval</b><br>
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).<br>
 *
 * You may assume that the intervals were initially sorted according to their
 * start times.<br>
 *
 * <b>Example 1:</b><br>
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].<br>
 *
 * <b>Example 2:</b><br>
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as
 * [1,2],[3,10],[12,16].<br>
 *
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class Solution {
    /**
     * AC, Time Complexity O(n)
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.size() == 0) {
            return Arrays.asList(newInterval);
        }
        List<Interval> ret = new ArrayList<Interval>();
        boolean overlapping = false;
        Integer prevend = Integer.MIN_VALUE;
        Integer ostart = null;
        Integer oend = null;
        for (Interval ite : intervals) {
            if (!overlapping && prevend < newInterval.start && newInterval.start <= ite.end) {
                overlapping = true;
                ostart = Math.min(ite.start, newInterval.start);
            }
            if (overlapping && newInterval.end < ite.start) {
                overlapping = false;
                oend = Math.max(prevend, newInterval.end);
                ret.add(new Interval(ostart, oend));
                // ret.add(ite);
            }
            prevend = ite.end;
            if (!overlapping) {
                if (prevend < newInterval.start && newInterval.end < ite.start) {
                    ret.add(newInterval);
                }
                ret.add(ite);
                // prevend = ite.end;
            }
        }
        if (overlapping) {
            oend = Math.max(intervals.get(intervals.size() - 1).end, newInterval.end);
            ret.add(new Interval(ostart, oend));
        }
        if (ostart == null && oend == null) {
            ret.add(newInterval);
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.insert(Arrays.asList(new Interval(1, 3), new Interval(6, 9)), new Interval(2, 5)));
        System.out.println(s.insert(Arrays.asList(new Interval(1, 2), new Interval(3, 5), new Interval(6, 7),
                new Interval(8, 10), new Interval(12, 16)), new Interval(4, 9)));
        System.out.println(s.insert(Arrays.asList(new Interval(1, 2), new Interval(3, 5), new Interval(6, 7),
                new Interval(8, 10), new Interval(12, 16)), new Interval(-3, -1)));
        System.out.println(s.insert(Arrays.asList(new Interval(1, 2), new Interval(3, 5), new Interval(6, 7),
                new Interval(8, 10), new Interval(12, 16)), new Interval(18, 20)));
        System.out.println(s.insert(Arrays.asList(new Interval(1, 2), new Interval(3, 5), new Interval(6, 7),
                new Interval(8, 10), new Interval(14, 16)), new Interval(11, 12)));
        System.out.println(s.insert(Arrays.asList(new Interval(1, 2), new Interval(3, 5), new Interval(6, 7),
                new Interval(8, 10), new Interval(14, 16)), new Interval(4, 5)));
        System.out.println(s.insert(Arrays.asList(new Interval(1, 2), new Interval(3, 5), new Interval(6, 7),
                new Interval(8, 10), new Interval(14, 16)), new Interval(4, 6)));
    }
}