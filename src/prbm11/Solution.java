package prbm11;

public class Solution {
    public int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (height[i] > height[j] ? height[j] : height[i]) * (j - i);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = { 10, 14, 10, 4, 10, 2, 6, 1, 6, 12 };
        Solution s = new Solution();
        int a = s.maxArea(height);
        System.out.println(a);
    }
}