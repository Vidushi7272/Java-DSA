class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        return reverseGroup(dummy, dummy, dummy, k);
    }

    private ListNode reverseGroup(ListNode prev, ListNode right, ListNode dummy, int k) {

        // Check if at least k nodes exist
        for (int i = 0; i < k; i++) {
            if (right == null)
                return dummy.next;
            right = right.next;
        }

        if (right == null)
            return dummy.next;

        // Node after the current group
        right = right.next;

        ListNode start = prev.next;
        ListNode then = start.next;

        // Reverse current group using head insertion
        while (then != right) {
            start.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = start.next;
        }

        // Process remaining groups
        return reverseGroup(start, start, dummy, k);
    }
}

public class lc25 {

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null)
                System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(
                1,
                new ListNode(
                        2,
                        new ListNode(
                                3,
                                new ListNode(
                                        4,
                                        new ListNode(5)))));

        Solution sol = new Solution();

        ListNode result = sol.reverseKGroup(head, 2);

        printList(result);
    }
}