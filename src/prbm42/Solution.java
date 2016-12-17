package prbm42;

public class Solution {
    public int trap(int[] height) {
        int len = height.length;
        if (len <= 2) {
            return 0;
        }
        int[] area = new int[len];
        int max = 0;
        for (int i = 0; i < len; i++) {
            max = height[i] > max ? height[i] : max;
        }
        for (int i = 0; i < len; i++) {
            area[i] = max;
        }
        int curmax = 0;
        for (int i = 0; i < len; i++) {
            curmax = height[i] > curmax ? height[i] : curmax;
            area[i] = curmax < area[i] ? curmax : area[i];
        }
        curmax = 0;
        for (int i = len - 1; i >= 0; i--) {
            curmax = height[i] > curmax ? height[i] : curmax;
            area[i] = curmax < area[i] ? curmax : area[i];
        }
        int accu = 0;
        for (int i = 0; i < len; i++) {
            accu += area[i] - height[i];
        }
        return accu;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
    }
}