package prbm71;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 71. <b>Simplify Path</b><br>
 * Given an absolute path for a file (Unix-style), simplify it.<br>
 *
 * For example,<br>
 * path = "/home/", => "/home"<br>
 * path = "/a/./b/../../c/", => "/c"<br>
 * click to show corner cases.<br>
 *
 * <b>Corner Cases</b>:<br>
 * Did you consider the case where path = "/../"?<br>
 * In this case, you should return "/".<br>
 * Another corner case is the path might contain multiple slashes '/' together,
 * such as "/home//foo/".<br>
 * In this case, you should ignore redundant slashes and return "/home/foo".<br>
 *
 */
public class Solution {
    /**
     * AC, 96% beaten
     */
    public String simplifyPath(String path) {
        Deque<String> pq = new LinkedList<String>();
        StringBuffer sb = new StringBuffer();
        for (char c : path.toCharArray()) {
            if (c == '/') {
                if (sb.length() == 0) {
                    continue;
                }
                processCurrentBuffer(pq, sb);
                sb = new StringBuffer();
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            processCurrentBuffer(pq, sb);
        }
        if (pq.size() == 0) {
            return "/";
        }
        sb = new StringBuffer();
        while (pq.size() > 0) {
            sb.append("/").append(pq.removeFirst());
        }
        return sb.toString();
    }

    private void processCurrentBuffer(Deque<String> pq, StringBuffer sb) {
        String tp = sb.toString();
        if (tp.equals(".")) {
            // do nothing
        } else if (tp.equals("..")) {
            if (!pq.isEmpty()) {
                pq.removeLast();
            }
        } else {
            pq.addLast(tp);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.simplifyPath("/"));
        System.out.println(s.simplifyPath("/home/"));
        System.out.println(s.simplifyPath("/a/./b/../../c/"));
        System.out.println(s.simplifyPath("//../"));
    }
}