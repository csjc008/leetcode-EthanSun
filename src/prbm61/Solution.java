package prbm61;

/**
 * 61. <b>Rotate List</b><br>
 * Given a list, rotate the list to the right by k places, where k is
 * non-negative.<br>
 *
 * For example:<br>
 * Given 1->2->3->4->5->NULL and k = 2,<br>
 * return 4->5->1->2->3->NULL.<br>
 *
 */
public class Solution {

    /**
     * AC, O(n)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode tail = null;
        ListNode cur = head;
        int len = 1;
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }
        tail = cur;
        k = k % len;
        if (k == 0) {
            return head;
        }
        cur = head;
        for (int i = 0; i < (len - k - 1); i++) {
            cur = cur.next;
        }
        tail.next = head;
        ListNode newhead = cur.next;
        cur.next = null;
        return newhead;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        // System.out.println(s.rotateRight(head, 0));
        // System.out.println(s.rotateRight(head, 1));
        System.out.println(s.rotateRight(head, 6));
    }
}