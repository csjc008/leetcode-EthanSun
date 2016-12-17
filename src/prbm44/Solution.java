package prbm44;

/**
 * 44. Wildcard Matching<br>
 *
 * Implement wildcard pattern matching with support for '?' and '*'.<br>
 *
 * '?' Matches any single character.<br>
 * '*' Matches any sequence of characters (including the empty sequence).<br>
 *
 * The matching should cover the entire input string (not partial).<br>
 *
 * The function prototype should be:<br>
 * bool isMatch(const char *s, const char *p)<br>
 *
 * Some examples:<br>
 * isMatch("aa","a") → false<br>
 * isMatch("aa","aa") → true<br>
 * isMatch("aaa","aa") → false<br>
 * isMatch("aa", "*") → true<br>
 * isMatch("aa", "a*") → true<br>
 * isMatch("ab", "?*") → true<br>
 * isMatch("aab", "c*a*b") → false<br>
 */
public class Solution {
    public boolean isMatch(String s, String p) {
        // replace all redundent ** to *
        if (p.length() > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append(p.charAt(0));
            for (int i = 1; i < p.length(); i++) {
                if (p.charAt(i) != '*' || p.charAt(i - 1) != '*') {
                    sb.append(p.charAt(i));
                }
            }
            p = sb.toString();
        }
        if (p.length() == 1 && p.charAt(0) == '*') {
            return true;
        }

        int slen = s.length();
        int plen = p.length();
        int ps = 0;
        int pp = 0;
        // trim left non-star element of s and p
        // "aabb","?*b" -> "abb","*b"
        while (pp < plen && ps < slen && p.charAt(pp) != '*') {
            if (p.charAt(pp) != '?' && p.charAt(pp) != s.charAt(ps)) {
                return false;
            }
            pp++;
            ps++;
        }
        int trimleft = pp;
        s = s.substring(trimleft);
        p = p.substring(trimleft);

        // if s and p is not empty
        // trim right non-star element of s and p
        // "abb","*b" -> "ab","*"
        if (s.length() > 0 && p.length() > 0) {
            slen = s.length();
            plen = p.length();
            ps = slen - 1;
            pp = plen - 1;
            while (pp >= 0 && ps >= 0 && p.charAt(pp) != '*') {
                if (p.charAt(pp) != '?' && p.charAt(pp) != s.charAt(ps)) {
                    return false;
                }
                pp--;
                ps--;
            }
            int trimright = plen - 1 - pp;
            s = s.substring(0, slen - trimright);
            p = p.substring(0, plen - trimright);
        }
        slen = s.length();
        plen = p.length();
        // length of s or length of p is zero judgement
        if (plen == 0) {
            if (slen > 0) {
                return false;
            } else {
                return true;
            }
        }
        if (slen == 0 && (plen > 1 || plen == 1 && p.charAt(0) != '*')) {
            return false;
        }
        ps = 0;
        pp = 0;
        int ptnl = 1;
        int ptnr = 1;
        int psl = 0;
        int psr = 0;
        // first and last character of p is star
        // p: *aa*bb* -> (aa,bb)
        // locate each of the non-star sub-patterns in s sequentially
        // if all satisfies, return true
        // otherwise false
        while (ptnl < plen && ptnr < plen) {
            // ptnl and ptnr designates left and right index of current
            // sub-pattern
            // find a sub-pattern
            while (p.charAt(ptnr) != '*') {
                ptnr++;
            }
            // find match in s
            for (int i = psl; i <= slen - (ptnr - ptnl); i++) {
                int j = ptnl;
                for (; j < ptnr; j++) {
                    if (s.charAt(i + (j - ptnl)) != p.charAt(j) && p.charAt(j) != '?') {
                        break;
                    }
                }
                if (j == ptnr) {
                    // matches current sub-pattern
                    psl = i;
                    psr = psl + (ptnr - ptnl);
                    break;
                }
            }
            if (psl == psr) {
                // no match for current sub-pattern
                return false;
            }
            // go to next position for next sub-pattern
            psl = psr;
            ptnr++;
            ptnl = ptnr;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isMatch("bb", "?*?"));
        System.out.println(s.isMatch("b", "?*?"));
        System.out.println(s.isMatch("aa", "a"));
        System.out.println(s.isMatch("aa", "aa"));
        System.out.println(s.isMatch("aaa", "aa"));
        System.out.println(s.isMatch("aa", "*"));
        System.out.println(s.isMatch("aa", "a*"));
        System.out.println(s.isMatch("ab", "?*"));
        System.out.println(s.isMatch("aab", "c*a*b"));
        System.out.println(s.isMatch("aabbaab", "a*b"));
        System.out.println(s.isMatch("aabbbaaab", "a*b*b"));
        System.out.println(s.isMatch("aaabababaaabaababbbaaaabbbbbbabbbbabbbabbaabbababab", "*ab***ba**b*b*aaab*b"));
        System.out.println(s.isMatch("aaabaaaabbbbbbaaabbabbbbababbbaaabbabbabb", "*b*bbb*baa*bba*b*bb*b*a*aab*a*"));
        System.out.println(s.isMatch(
                "abbaabbbbababaababababbabbbaaaabbbbaaabbbabaabbbbbabbbbabbabbaaabaaaabbbbbbaaabbabbbbababbbaaabbabbabb",
                "***b**a*a*b***b*a*b*bbb**baa*bba**b**bb***b*a*aab*a**"));

        System.out.println(s.isMatch("bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab", "b*b*ab**ba*b**b***bba"));
        System.out.println(s.isMatch(
                "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
    }
}