package countNumbers;

public class CountNumbers {

    private void copyCount(int[] src, int[] tgt) {
        for (int i = 0; i < src.length; i++) {
            tgt[i] = src[i];
        }
    }

    /**
     * 判定序列是否符合要求
     */
    private boolean compareCount(int[] src, int[] tgt) {
        for (int i = 0; i < src.length; i++) {
            if (tgt[i] != src[i]) {
                return false;
            }
        }
        return true;
    }

    private void printCount(int[] cnt) {
        for (int c : cnt) {
            System.out.print("" + c + " ");
        }
        System.out.println();
    }

    public void countNumbers() {
        // 随机种子
        int[] count = { 2, 2, 2, 3, 3, 5, 5, 6, 6, 7 };
        int[] prevCount = new int[10];

        // 支持多位数
        while (true) {
            this.copyCount(count, prevCount);
            // count for 0~9
            for (int i = 0; i < 10; i++) {
                count[i] = 1;
            }
            for (int cnt : prevCount) {
                String cntStr = String.valueOf(cnt);
                for (char c : cntStr.toCharArray()) {
                    count[c - '0']++;
                }
            }
            this.printCount(count);
            if (compareCount(count, prevCount)) {
                System.out.println("found !");
                break;
            }
        }
    }

    public static void main(String[] args) {
        CountNumbers cn = new CountNumbers();
        cn.countNumbers();
    }
}
