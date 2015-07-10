package prbm2;

public class Solution {
    public class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int addUp = 0;
        ListNode ret = null;
        ListNode cur = null;
        while (l1 != null || l2 != null) {
            if (cur == null) {
                cur = ret = new ListNode(0);
            } else {
                cur.next = new ListNode(addUp);
                cur = cur.next;
            }
            cur.val += (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            addUp = cur.val / 10;
            cur.val = cur.val % 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (addUp > 0) {
            cur.next = new ListNode(addUp);
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode a = s.new ListNode(2);
        a.next = s.new ListNode(4);
        a.next.next = s.new ListNode(3);
        ListNode b = s.new ListNode(5);
        b.next = s.new ListNode(6);
        // b.next.next = s.new ListNode(4);
        ListNode c = s.addTwoNumbers(a, b);
        while (true) {
            System.out.println(c.val);
            if (c.next != null) {
                c = c.next;
            } else {
                break;
            }
        }
    }
}