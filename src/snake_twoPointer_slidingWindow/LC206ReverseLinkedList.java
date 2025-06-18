package snake_twoPointer_slidingWindow;

/**
 *
 */
public class LC206ReverseLinkedList {

    public class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //wrong: found cycle in the list
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        ListNode curr = head;
        dummy.next = curr; // wrong here, move this in the while condition, this cause cycle in linked list
        while (curr != null) {
            ListNode next = curr.next;
            ListNode prev = dummy.next;
            //dummy.next = curr; --correct solution should add this here
            curr.next = prev;
            //prev = curr; ---no need here, this is for solution 1
            curr = next;
        }
        return dummy.next; //cur is null now
    }

    public ListNode reverseListSolution1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            //placeholder two variable pre and cur
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseListSolution2(ListNode head) { //use dummy node
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(1);
        ListNode curr = head;
        while (curr != null) {
            //insert current node between dummy and dummy.next
            ListNode prev = dummy.next;
            ListNode next = curr.next;
            curr.next = prev;
            dummy.next = curr;
            curr = next;
        }
        return dummy.next; //cur is null now
    }

}
