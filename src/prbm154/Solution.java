package prbm154;

public class Solution {
    public int findMin(int[] array) {
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
            if (array[cur] < array[(cur + len - 1) % len]) {
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
        while (true) {
            if (array[pos] != array[(pos + len - 1) % len]) {
                return pos;
            }
            if (counter >= len) {
                // special case
                return -1;
            }
            pos = (pos + len - 1) % len;
            counter++;
        }
    }

    public static int moveRight(int[] array, int pos) {
        int len = array.length;
        while (true) {
            if (array[pos] != array[(pos + 1) % len]) {
                return pos;
            }
            pos = (pos + 1) % len;
        }
    }
}
