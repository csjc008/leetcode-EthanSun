package prbm10;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public class Symbol {
        public Symbol() {
            rep = false;
        }

        public Symbol(char f, boolean s) {
            c = f;
            rep = s;
        }

        public char    c;
        public boolean rep;
    }

    public boolean isMatch(String s, String p) {
        // parse pattern
        List<Symbol> pl = new ArrayList<Symbol>();
        int i = 0;
        while (i < p.length()) {
            char c;
            boolean rep = false;
            char a = p.charAt(i);
            if (a != '*') {
                c = a;
            } else {
                // regex wrong
                return false;
            }
            i++;
            if (i < p.length() && p.charAt(i) == '*') {
                rep = true;
                i++;
            }
            pl.add(new Symbol(c, rep));
        }
        // do match
        return isMatch(s, 0, pl, 0);
    }

    private boolean isMatch(String s, int sPos, List<Symbol> pl, int pPos) {
        while (sPos < s.length() && pPos < pl.size()) {
            Symbol sym = pl.get(pPos);
            if (sym.rep) {
                if (sym.c == '.' || sym.c == s.charAt(sPos)) {
                    sPos++;
                } else {
                    pPos++;
                }
                while (sPos < s.length() && pPos < pl.size()) {
                    if (isMatch(s, sPos, pl, pPos)) {
                        return true;
                    } else {
                        if (sym.c == '.' || sym.c == s.charAt(sPos)) {
                            sPos++;
                        } else {
                            return false;
                        }
                    }
                }
                if (pPos < pl.size()) {
                    break;
                } else {
                    while (sPos < s.length()) {
                        if (s.charAt(sPos) != sym.c && sym.c != '.') {
                            return false;
                        }
                        sPos++;
                    }
                    return true;
                }
            } else {
                if (sym.c != s.charAt(sPos) && sym.c != '.') {
                    return false;
                }
            }
            sPos++;
            pPos++;
        }
        if (sPos < s.length()) {
            return false;
        }
        if (pPos < pl.size()) {
            while (pPos < pl.size()) {
                if (!pl.get(pPos).rep) {
                    return false;
                }
                pPos++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        // System.out.println(s.isMatch("aa", "a"));
        // System.out.println(s.isMatch("aa", "aa"));
        // System.out.println(s.isMatch("aaa", "aa"));
        // System.out.println(s.isMatch("aa", "a*"));
        // System.out.println(s.isMatch("aa", ".*"));
        // System.out.println(s.isMatch("aab", "c*a*b"));
        // System.out.println(s.isMatch("ab", ".*"));
        // System.out.println(s.isMatch("aaab", ".*ab"));
        // System.out.println(s.isMatch("aaa", "a*a"));
        // System.out.println(s.isMatch("aaa", "aaab*"));
        // System.out.println(s.isMatch("bbab", "b*"));
        // System.out.println(s.isMatch("bbab", "a*"));
        // System.out.println(s.isMatch("bbab", "b*a*"));
        // System.out.println(s.isMatch("bbab", "...."));
        // System.out.println(s.isMatch("abbabaaaaaaacaa",
        // "a*.*b.a.*c*b*a*c*"));
        // System.out.println(s.isMatch("bbabaaaaaaacaa", "b.a.*c*b*a*c*"));
        // System.out.println(s.isMatch("baaaaaaacaa", ".*c*b*a*c*"));
        // System.out.println(s.isMatch("caa", "c*b*a*c*"));
        // System.out.println(s.isMatch("caa", "c*b*a*"));
        // System.out.println(s.isMatch("a", "a*b*"));
        // System.out.println(s.isMatch("a", "a*.*"));
        System.out.println(s.isMatch("ab", "a*b"));
        System.out.println(s.isMatch("b", ".*b"));
        System.out.println(s.isMatch("ab", ".*b"));
    }
}