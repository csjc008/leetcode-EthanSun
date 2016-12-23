package prbm55;

public class Solution2 {
    /**
     * AC Time Complexity is O(n)<br>
     * think is the fastest way
     *
     */
    public boolean canJump(final int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return true;
        }
        int[] reach = new int[len];
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                reach[i] = reach[i + 1];
                continue;
            }
            if (nums[i] + i >= len - 1) {
                reach[i] = reach[i + 1] + 1;
                continue;
            }
            int maxidx = nums[i] + i + 1 > (len - 1) ? (len - 1) : nums[i] + i + 1;
            if (reach[i + 1] > reach[maxidx]) {
                reach[i] = reach[i + 1] + 1;
            } else {
                reach[i] = reach[i + 1];
            }
        }
        return reach[0] > reach[1];
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.canJump(new int[] { 9, 8, 2, 2, 0, 2, 2, 0, 4, 1, 5, 7, 9, 6, 6, 0, 6, 5, 0, 5 }));
        System.out.println(s.canJump(new int[] { 5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7,
                9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5 }));
        System.out.println(s.canJump(new int[] { 8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6,
                0, 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8,
                2, 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5 }));
        System.out.println(s.canJump(new int[] { 2, 3, 1, 1, 4 }));
        System.out.println(s.canJump(new int[] { 2, 3, 0, 1, 4 }));
        int size = 25002;
        int[] l = new int[size];
        for (int i = 0; i < size - 2; i++) {
            l[i] = (size - i - 2);
        }
        l[size - 2] = 1;
        l[size - 1] = 0;
        System.out.println(s.canJump(l));
    }
}