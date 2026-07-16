/**
 * Reverse a sublist of a singly linked list from position left to right.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ReverseLL2 {

    /**
     * Definition for a singly linked list node.
     */
    static class ListNode {
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

    public ListNode reverseBetween(ListNode head, int left, int right) {

        // No reversal required
        if (head == null || head.next == null || left == right) {
            return head;
        }

        // Dummy node simplifies handling when left == 1
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Move 'prev' to the node just before the reversal begins
        ListNode prev = dummy;
        int position = 1;

        while (position != left) {
            prev = prev.next;
            position++;
        }

        // Stores the node before the reversal starts
        ListNode beforeReverse = prev;

        // Initialize pointers for reversal
        prev = prev.next;
        ListNode curr = prev;
        ListNode next = prev.next;

        // Reverse nodes from left to right
        while (position != right) {
            position++;

            curr = next;
            next = next.next;

            curr.next = prev;
            prev = curr;
        }

        /*
         * Reconnect the reversed sublist.
         *
         * Before:
         * ... -> beforeReverse -> left -> ... -> right -> next
         *
         * After:
         * ... -> beforeReverse -> right -> ... -> left -> next
         */

        // Original left node becomes the tail
        beforeReverse.next.next = next;

        // Connect the previous part with the new head
        beforeReverse.next = curr;

        return dummy.next;
    }

    // ---------------- Helper Methods ----------------

    public static ListNode createList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);

            if (head.next != null) {
                System.out.print(" -> ");
            }

            head = head.next;
        }
        System.out.println();
    }

    // ---------------- Driver Code ----------------

    public static void main(String[] args) {

        ReverseLL2 solution = new ReverseLL2();

        ListNode head = createList(1, 2, 3, 4, 5);

        System.out.println("Original List:");
        printList(head);

        head = solution.reverseBetween(head, 2, 4);

        System.out.println("After Reversing (2,4):");
        printList(head);
    }
}

