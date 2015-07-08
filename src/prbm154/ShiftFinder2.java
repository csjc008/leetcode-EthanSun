package prbm154;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class ShiftFinder2 {
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
            cur = moveLeft(array, cur);
            if (cur == -1) {
                cur = 0;
                break;
            }
            l = moveRight(array, l);
            r = moveLeft(array, r);
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
                l = moveRight(array, cur);
                cur = (l + r) / 2;
                continue;
            }
            r = cur;
            cur = (l + r) / 2;
        }
        return array[cur];
    }

    public static int moveLeft(int[] array, int pos) {
        int counter = 0;
        int len = array.length;
        pos = index(pos, len);
        while (true) {
            if (array[pos] != array[index(pos - 1, len)]) {
                return pos;
            }
            if (counter >= len) {
                // special case
                return -1;
            }
            pos = index(pos - 1, len);
            counter++;
        }
    }

    public static int moveRight(int[] array, int pos) {
        int len = array.length;
        pos = index(pos, len);
        while (true) {
            if (array[pos] != array[index(pos + 1, len)]) {
                return pos;
            }
            pos = index(pos + 1, len);
        }
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
        int[] h = { 1, 1, 1, 1, 1, 1 };
        int[] m = { 2, 1, 1, 1, 1, 1, 1 };
        int[] n = { 2, 2, 1, 1, 1, 1, 1, 1, 2 };
        int[] o = { 1, 1, 1, 1, 1, 1, 1, 2 };
        int[] p = { 1, 1, 1, 1, 1, 1, 2, 2 };
        int[] q = { 5, 5, 1, 3, 3, 3, 3, 3, 3, 4 };
        int[] r = { 4, 4, 5, 6, 6, 6, 6, 7, 9, 1, 1, 2 };
        int[] s = { 18, 18, 18, 19, 19, 19, 20, 20, 20, 20, 20, 20, 21, 21, 21, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23,
                24, 24, 25, 25, 25, 25, 26, 27, 27, 27, 27, 28, 28, 29, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 6,
                6, 7, 8, 8, 9, 9, 9, 9, 10, 10, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14,
                14, 15, 15, 16, 17, 17, 17, 17, 17, 17, 18, 18 };
        int[] t = { 7, 7, 8, 8, 8, 9, 9, 9, 9, 9, 10, 10, 10, 11, 11, 12, 12, 12, 12, 12, 13, 14, 14, 15, 15, 15, 15,
                16, 16, 17, 17, 17, 17, 18, 18, 19, 19, 19, 20, 20, 20, 20, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22,
                23, 23, 23, 23, 24, 24, 25, 26, 28, 28, 28, 28, 29, 29, 29, 29, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3,
                3, 3, 3, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7 };
        int[] u = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 };
        int[] v = { 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        System.out.println(ShiftFinder2.findMin(a));
        System.out.println(ShiftFinder2.findMin(b));
        System.out.println(ShiftFinder2.findMin(c));
        System.out.println(ShiftFinder2.findMin(d));
        System.out.println(ShiftFinder2.findMin(e));
        System.out.println(ShiftFinder2.findMin(f));
        System.out.println(ShiftFinder2.findMin(g));
        System.out.println(ShiftFinder2.findMin(h));
        System.out.println(ShiftFinder2.findMin(m));
        System.out.println(ShiftFinder2.findMin(n));
        System.out.println(ShiftFinder2.findMin(o));
        System.out.println(ShiftFinder2.findMin(p));
        System.out.println(ShiftFinder2.findMin(q));
        System.out.println(ShiftFinder2.findMin(r));
        System.out.println(ShiftFinder2.findMin(s));
        System.out.println(ShiftFinder2.findMin(t));
        System.out.println(ShiftFinder2.findMin(u));
        System.out.println(ShiftFinder2.findMin(v));
        // gen non-repeatable random shift array
        System.out.println("----- test non-repeatable shift array -----");
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
            aa[ShiftFinder2.index(i + shift, aa.length)] = iay[i];
        }
        for (int i = 0; i < aa.length; i++) {
            System.out.print(aa[i] + " ");
        }
        System.out.println();
        System.out.println("non-repeatable random minimum find: " + ShiftFinder2.findMin(aa));
        System.out.println();
        // gen repeatable random shift array
        System.out.println("----- test repeatable shift array -----");
        attemptSize = 100;
        randomRange = 30;
        shift = rdm.nextInt(attemptSize);
        System.out.println("size: " + attemptSize + "; shift: " + shift);
        List<Integer> jay = new ArrayList<Integer>();
        for (int i = 0; i < attemptSize; i++) {
            jay.add(rdm.nextInt(randomRange));
        }
        Collections.sort(jay);
        int[] bb = new int[attemptSize];
        for (int i = 0; i < attemptSize; i++) {
            bb[ShiftFinder2.index(i + shift, attemptSize)] = jay.get(i);
        }
        for (int i = 0; i < attemptSize; i++) {
            System.out.print(bb[i] + " ");
        }
        System.out.println();
        System.out.println("repeatable random minimum find: " + ShiftFinder2.findMin(bb));
        System.out.println();
    }
}