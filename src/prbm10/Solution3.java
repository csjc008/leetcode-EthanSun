package prbm10;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    /**
     * AC, fast enough
     */
    public boolean isMatch(String s, String p) {
        List<String> plist = new ArrayList<String>();
        int pi = 0;
        while (pi < p.length()) {
            if (p.charAt(pi) == '*') {
                // regex wrong
                return false;
            }
            if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
                plist.add(p.substring(pi, pi + 2));
                pi += 2;
            } else {
                plist.add(p.substring(pi, pi + 1));
                pi += 1;
            }
        }
        return isMatch(s, 0, s.length(), plist, 0, plist.size());
    }

    /**
     *
     * @param s string to be matched
     * @param ss start position of s (inclusive)
     * @param se end position of s (exclusive)
     * @param plist pattern list
     * @param ps start position of plist (inclusive)
     * @param pe end position of plist (exclusive)
     * @return
     */
    public boolean isMatch(String s, int ss, int se, List<String> plist, int ps, int pe) {
        if (ps == pe) {
            if (ss != se) {
                return false;
            } else {
                return true;
            }
        }
        while (ps < pe && plist.get(ps).length() == 1 && ss < se) {
            char c = plist.get(ps).charAt(0);
            if (c != '.' && c != s.charAt(ss)) {
                return false;
            }
            ss++;
            ps++;
        }
        while (ps < pe && plist.get(pe - 1).length() == 1 && ss < se) {
            char c = plist.get(pe - 1).charAt(0);
            if (c != '.' && c != s.charAt(se - 1)) {
                return false;
            }
            se--;
            pe--;
        }
        if (ps == pe && ss == se) {
            return true;
        }
        if (ps == pe) {
            return false;
        }
        if (ss == se) {
            for (int i = ps; i < pe; i++) {
                if (plist.get(i).length() == 1) {
                    return false;
                }
            }
            return true;
        }
        // select one single sub-pattern
        int pi = 0;
        for (pi = ps; pi < pe; pi++) {
            if (plist.get(pi).length() == 1) {
                break;
            }
        }
        if (pi < pe) {
            // found single sub-pattern
            char c = plist.get(pi).charAt(0);
            for (int si = ss; si < se; si++) {
                if (c == '.' || s.charAt(si) == c) {
                    boolean b1 = isMatch(s, ss, si, plist, ps, pi);
                    if (!b1) {
                        // early termination
                        continue;
                    }
                    boolean b2 = isMatch(s, si + 1, se, plist, pi + 1, pe);
                    if (b2) {
                        return true;
                    }
                }
            }
            return false;
        }
        // single sub-pattern not found, all * patterns
        // do greedy
        for (pi = ps; pi < pe; pi++) {
            char c = plist.get(pi).charAt(0);
            if (c == '.') {
                // will consume all
                return true;
            }
            while (ss < se && (s.charAt(ss) == c)) {
                ss++;
            }
            if (ss == se) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution3 s = new Solution3();
        System.out.println(s.isMatch("aa", "a"));
        System.out.println(s.isMatch("aa", "aa"));
        System.out.println(s.isMatch("aaa", "aa"));
        System.out.println(s.isMatch("aa", "a*"));
        System.out.println(s.isMatch("aa", ".*"));
        System.out.println(s.isMatch("aab", "c*a*b"));
        System.out.println(s.isMatch("ab", ".*"));
        System.out.println(s.isMatch("aaab", ".*ab"));
        System.out.println(s.isMatch("aaa", "a*a"));
        System.out.println(s.isMatch("aaa", "aaab*"));
        System.out.println(s.isMatch("bbab", "b*"));
        System.out.println(s.isMatch("bbab", "a*"));
        System.out.println(s.isMatch("bbab", "b*a*"));
        System.out.println(s.isMatch("bbab", "...."));
        System.out.println(s.isMatch("abbabaaaaaaacaa", "a*.*b.a.*c*b*a*c*"));
        System.out.println(s.isMatch("bbabaaaaaaacaa", "b.a.*c*b*a*c*"));
        System.out.println(s.isMatch("baaaaaaacaa", ".*c*b*a*c*"));
        System.out.println(s.isMatch("caa", "c*b*a*c*"));
        System.out.println(s.isMatch("caa", "c*b*a*"));
        System.out.println(s.isMatch("a", "a*b*"));
        System.out.println(s.isMatch("a", "a*.*"));
        System.out.println(s.isMatch("ab", "a*b"));
        System.out.println(s.isMatch("b", ".*b"));
        System.out.println(s.isMatch("ab", ".*b"));
        System.out.println(s.isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*a*a*b"));
    }
}
