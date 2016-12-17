package prbm23;

/**
 * 23. Merge k Sorted Lists<br>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.<br>
 *
 * The time complexity is O(logk*n), which n is the total element number of all
 * k sorted lists.<br>
 *
 */
public class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        if (lists.length == 2) {
            return merge2Lists(lists[0], lists[1]);
        }
        ListNode[] newlists = new ListNode[(lists.length + 1) / 2];
        for (int i = 0; i < lists.length;) {
            if ((i + 1) < lists.length) {
                newlists[i / 2] = merge2Lists(lists[i], lists[i + 1]);
            } else {
                newlists[i / 2] = lists[i];
            }
            i += 2;
        }
        return mergeKLists(newlists);
    }

    public ListNode merge2Lists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head;
        ListNode cur;
        if (l1.val < l2.val) {
            head = l1;
            cur = l1;
            l1 = l1.next;
        } else {
            head = l2;
            cur = l2;
            l2 = l2.next;
        }
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return head;
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
        s.printNodeList(s.mergeKLists(new ListNode[] {}));
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(10);
        s.printNodeList(s.mergeKLists(new ListNode[] { l1 }));
        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        s.printNodeList(s.mergeKLists(new ListNode[] { l1, l2 }));
    }
}
