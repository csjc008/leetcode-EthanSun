package prbm10;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution2 {
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

    public class Pair<T> {
        public T first;
        public T second;

        public Pair() {
            first = null;
            second = null;
        }

        public Pair(T _f, T _s) {
            first = _f;
            second = _s;
        }
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
        Stack<Pair<Integer>> q = new Stack<Pair<Integer>>();
        q.push(new Pair<Integer>(0, 0));
        while (!q.isEmpty()) {
            Pair<Integer> pr = q.pop();
            if (isMatch(s, pr.first, pl, pr.second, q)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMatch(String s, int sPos, List<Symbol> pl, int pPos, Stack<Pair<Integer>> q) {
        while (sPos < s.length() && pPos < pl.size()) {
            Symbol sym = pl.get(pPos);
            if (sym.rep) {
                if (sym.c == '.' || sym.c == s.charAt(sPos)) {
                    q.push(new Pair<Integer>(sPos, pPos + 1));
                    q.push(new Pair<Integer>(sPos + 1, pPos));
                } else {
                    q.push(new Pair<Integer>(sPos, pPos + 1));
                }
                return false;
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
        Solution2 s = new Solution2();
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