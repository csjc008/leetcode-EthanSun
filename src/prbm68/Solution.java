package prbm68;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. <b>Text Justification</b><br>
 * Given an array of words and a length L, format the text such that each line
 * has exactly L characters and is fully (left and right) justified.<br>
 *
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly L characters.<br>
 *
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line do not divide evenly between words, the empty
 * slots on the left will be assigned more spaces than the slots on the right.
 * <br>
 *
 * <b>For the last line of text, it should be left justified and no extra space
 * is inserted between words.</b><br>
 *
 * For example,<br>
 * <b>words:</b> ["This", "is", "an", "example", "of", "text", "justification."]
 * <br>
 * <b>L:</b> 16.<br>
 *
 * Return the formatted lines as:<br>
 * [<br>
 * "This    is    an",<br>
 * "example  of text",<br>
 * "justification.  "<br>
 * ]<br>
 * <b>Note:</b> Each word is guaranteed not to exceed L in length.<br>
 *
 */
public class Solution {
    /**
     * AC
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ret = new ArrayList<String>();
        int len = words.length;
        if (len == 0) {
            return ret;
        }
        int sidx = 0;
        int eidx = 0;
        while (sidx < len) {
            int wordlen = 0;
            for (eidx = sidx; eidx < len; eidx++) {
                if (wordlen + (eidx - sidx) + words[eidx].length() > maxWidth) {
                    break;
                }
                wordlen += words[eidx].length();
            }
            if (eidx > sidx) {
                eidx--;
            }
            int blanks = maxWidth - wordlen;
            int pb = blanks;
            if ((sidx + 1) < eidx) {
                pb = blanks / (eidx - sidx);
            }
            int morenum = blanks - (eidx - sidx) * pb;
            StringBuffer sb = new StringBuffer();
            if (sidx == eidx) {
                sb.append(words[sidx]);
                appendSpaces(sb, pb);
            } else if (eidx == len - 1) {
                for (int i = sidx; i <= eidx; i++) {
                    sb.append(words[i]);
                    if (i != eidx) {
                        appendSpaces(sb, 1);
                    } else {
                        appendSpaces(sb, blanks - (eidx - sidx));
                    }
                }
            } else {
                for (int i = sidx; i <= eidx; i++) {
                    sb.append(words[i]);
                    if (i != eidx) {
                        if (i - sidx < morenum) {
                            appendSpaces(sb, pb + 1);
                        } else {
                            appendSpaces(sb, pb);
                        }
                    }
                }
            }
            ret.add(sb.toString());
            sidx = eidx + 1;
        }
        return ret;
    }

    private void appendSpaces(StringBuffer sb, int num) {
        for (int i = 0; i < num; i++) {
            sb.append(' ');
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.fullJustify(
                new String[] { "This", "is", "an", "example", "of", "text", "justification", "justification." }, 16));
        System.out.println(s.fullJustify(new String[] { "What", "must", "be", "shall", "be." }, 12));
    }
}