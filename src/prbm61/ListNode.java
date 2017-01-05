package prbm61;

public class ListNode {
    int      val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        ListNode cur = this;
        StringBuffer sb = new StringBuffer();
        while (cur != null) {
            sb.append(cur.val).append("->");
            cur = cur.next;
        }
        return sb.toString();
    }
}