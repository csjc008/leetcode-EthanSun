package prbm25;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.<br>
 * 
 * If the number of nodes is not a multiple of k then left-out nodes in the end
 * should remain as it is.<br>
 * 
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * <br>
 * 
 * Only constant memory is allowed.<br>
 * 
 * For example, Given this linked list: 1->2->3->4->5<br>
 * 
 * For k = 2, you should return: 2->1->4->3->5<br>
 * 
 * For k = 3, you should return: 3->2->1->4->5
 *
 */
public class Solution2 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode rhead = null;
        ListNode ktail = null;
        ListNode nhead = null;
        ListNode prevtail = null;
        while (head != null) {
            ktail = head;
            nhead = head.next;
            int i = 1;
            while (ktail.next != null && i < k) {
                ktail = ktail.next;
                nhead = nhead.next;
                i++;
            }
            if (i < k) {
                if (rhead == null) {
                    return head;
                } else {
                    return rhead;
                }
            }
            if (rhead == null) {
                rhead = ktail;
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
        Solution2 s = new Solution2();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        s.printNodeList(s.reverseKGroup(head, 5));
    }
}
