package prbm153;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class ShiftFinder {
    public static int findMin(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        int len = array.length;
        int l = 0;
        int r = len - 1;
        int cur = (l + r) / 2;
        while (true) {
            if (array[cur] < array[index(cur - 1, len)]) {
                break;
            }
            if (l == r) {
                cur = l;
                break;
            }
            if (r == (l + 1)) {
                if (array[l] < array[r]) {
                    cur = l;
                } else {
                    cur = r;
                }
                break;
            }
            if (array[cur] < array[l]) {
                r = cur;
                cur = (l + r) / 2;
                continue;
            }
            if (array[cur] > array[r]) {
                l = cur;
                cur = (l + r) / 2;
                continue;
            }
            r = cur;
            cur = (l + r) / 2;
        }
        return array[cur];
    }

    public static int index(int cur, int length) {
        return (cur % length + length) % length;
    }

    public static void main(String[] args) {
        int[] a = { 7, 8, 11, 12, 13, 14, 19, 22, 1, 2, 4, 5 };
        int[] b = { 1, 2, 3, 4, 5, 6, 7 };
        int[] c = { 11, 1, 2, 4, 5, 7, 8 };
        int[] d = { 1 };
        int[] e = { 1, 2 };
        int[] f = { 2, 1 };
        int[] g = { 3, 1, 2 };
        // System.out.println(ShiftFinder.index(0, 10));
        // System.out.println(ShiftFinder.index(2, 10));
        // System.out.println(ShiftFinder.index(-2, 10));
        // System.out.println(ShiftFinder.index(13, 10));
        // System.out.println(ShiftFinder.index(-13, 10));
        // System.out.println(ShiftFinder.index(1, 1));
        System.out.println(ShiftFinder.findMin(a));
        System.out.println(ShiftFinder.findMin(b));
        System.out.println(ShiftFinder.findMin(c));
        System.out.println(ShiftFinder.findMin(d));
        System.out.println(ShiftFinder.findMin(e));
        System.out.println(ShiftFinder.findMin(f));
        System.out.println(ShiftFinder.findMin(g));
        // gen random shift array
        int attemptSize = 100;
        int randomRange = 999;
        Random rdm = new Random();
        Set<Integer> ts = new TreeSet<Integer>();
        for (int i = 0; i < attemptSize; i++) {
            ts.add(rdm.nextInt(randomRange));
        }
        int shift = rdm.nextInt(ts.size());
        System.out.println("size: " + ts.size() + "; shift: " + shift);
        Integer[] iay = new Integer[ts.size()];
        ts.toArray(iay);
        int[] aa = new int[ts.size()];
        for (int i = 0; i < ts.size(); i++) {
            aa[ShiftFinder.index(i + shift, aa.length)] = iay[i];
        }
        for (int i = 0; i < aa.length; i++) {
            System.out.print(aa[i] + " ");
        }
        System.out.println();
        System.out.println("random minimum find: " + ShiftFinder.findMin(aa));
    }
}