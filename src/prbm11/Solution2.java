package prbm11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution2 {
    public int maxArea(int[] height) {

        class HeightObj {
            public HeightObj(int _i, int _ai) {
                this.i = _i;
                this.ai = _ai;
            }

            public int i;
            public int ai;

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof HeightObj)) {
                    return false;
                }
                HeightObj a = (HeightObj) obj;
                if (a.i == this.i && a.ai == this.ai) {
                    return true;
                }
                return false;
            }

            @Override
            public String toString() {
                return "[" + i + "," + ai + "]";
            }
        }
        boolean[] blist = new boolean[height.length];
        for (int i = 0; i < height.length; i++) {
            blist[i] = false;
        }

        if (height.length < 2) {
            return 0;
        }
        int maxArea = 0;
        int min_i = 0;
        int max_i = height.length - 1;
        List<HeightObj> vk = new ArrayList<HeightObj>();
        for (int i = 0; i < height.length; i++) {
            HeightObj ho = new HeightObj(i, height[i]);
            vk.add(ho);
        }
        Collections.sort(vk, new Comparator<HeightObj>() {
            @Override
            public int compare(HeightObj o1, HeightObj o2) {
                if (o1.ai == o2.ai) {
                    return 0;
                }
                return o1.ai > o2.ai ? 1 : -1;
            }
        });
        for (HeightObj ho : vk) {
            while (blist[min_i]) {
                min_i++;
            }
            while (blist[max_i]) {
                max_i--;
            }
            int ai = ho.ai;
            int i = ho.i;
            int len = (i - min_i) > (max_i - i) ? (i - min_i) : (max_i - i);
            int area = len * ai;
            if (area > maxArea) {
                maxArea = area;
            }
            blist[i] = true;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = { 10, 14, 10, 4, 10, 2, 6, 1, 6, 12 };
        Solution2 s = new Solution2();
        int a = s.maxArea(height);
        System.out.println(a);
    }
}