package prbm14;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String a = strs[0];
        for (int idx = 0; idx < a.length(); idx++) {
            char c = a.charAt(idx);
            boolean same = true;
            for (String str : strs) {
                if (str.length() < (idx + 1) || c != str.charAt(idx)) {
                    same = false;
                    break;
                }
            }
            if (same) {
                sb.append(c);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
