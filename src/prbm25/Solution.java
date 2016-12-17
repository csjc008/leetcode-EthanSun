package prbm25;

public class Solution {
    /**
     * accepted<br>
     * didn't use recursion, so it meets the constant memory requirement.<br>
     * but it's a little bit slow.
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode rhead = null;
        ListNode ktail = head;
        ListNode nhead = head.next;
        int i = 1;
        while (ktail.next != null && i < k) {
            ktail = ktail.next;
            nhead = nhead.next;
            i++;
        }
        if (i < k) {
            return head;
        }
        rhead = ktail;
        ListNode prevtail = null;
        while (head != null) {
            ktail = head;
            nhead = head.next;
            i = 1;
            while (ktail.next != null && i < k) {
                ktail = ktail.next;
                nhead = nhead.next;
                i++;
            }
            if (i < k) {
                return rhead;
            }
            ListNode reversehead = reverseList(head, k);
            if (prevtail != null) {
                prevtail.next = reversehead;
            }
            prevtail = head;
            head.next = nhead;
            head = nhead;
        }
        return rhead;
    }

    public ListNode reverseList(ListNode head, int k) {
        ListNode prev = null;
        ListNode newhead = null;
        for (int i = 0; i < k - 1; i++) {
            ListNode tail = head;
            for (int j = 0; j < k - i - 1; j++) {
                tail = tail.next;
            }
            tail.next = head;
            if (prev != null) {
                prev.next = tail;
            } else {
                newhead = tail;
            }
            prev = tail;
        }
        // printNodeList(head);
        return newhead == null ? head : newhead;
    }

    public void printNodeList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        s.printNodeList(s.reverseKGroup(head, 1));
    }
}
