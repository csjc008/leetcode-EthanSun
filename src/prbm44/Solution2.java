package prbm44;

public class Solution2 {
    /**
     * AC, beats 99.49%
     */
    public boolean isMatch(String s, String p) {
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
        slen = s.length();
        plen = p.length();
        if (slen > 0 && plen > 0) {
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

        ps = 0;
        pp = 0;
        int ptnl = 0;
        int ptnr = 0;
        int psl = 0;
        int psr = 0;
        // skip preceding *
        while (ptnr < plen && p.charAt(ptnr) == '*') {
            ptnr++;
        }
        ptnl = ptnr;
        // first and last character of p is star
        // p: *aa*bb* -> (aa,bb)
        // locate each of the non-star sub-patterns in s sequentially
        // if all satisfies, return true
        // otherwise false
        while (ptnl < plen && ptnr < plen) {
            // ptnl and ptnr designates left and right index of current
            // sub-pattern
            // find a sub-pattern
            while (ptnr < plen && p.charAt(ptnr) != '*') {
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
            while (ptnr < plen && p.charAt(ptnr) == '*') {
                ptnr++;
            }
            ptnl = ptnr;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.isMatch("aaab", "b**"));
        System.out.println(s.isMatch("a", "aa"));
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