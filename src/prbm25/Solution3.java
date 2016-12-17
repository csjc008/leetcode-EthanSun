package prbm25;

public class Solution3 {
    /**
     * slitely used recursion.
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode ktail = null;
        ListNode nhead = null;

        ktail = head;
        nhead = head.next;
        int i = 1;
        while (ktail.next != null && i < k) {
            ktail = ktail.next;
            nhead = nhead.next;
            i++;
        }
        if (i < k) {
            return head;
        }
        reverseList(head, k);
        head.next = reverseKGroup(nhead, k);
        return ktail;
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
        Solution3 s = new Solution3();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        s.printNodeList(s.reverseKGroup(head, 3));
    }
}
