public class LinkedListRearrangement {

    // Definition for singly-linked list
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

    // Swap nodes in pairs
    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {

            ListNode first = prev.next;
            ListNode second = first.next;
            ListNode nextPair = second.next;

            // Pointer rewiring
            prev.next = second;
            second.next = first;
            first.next = nextPair;

            prev = first;
        }

        return dummy.next;
    }

    // Create a linked list from an array
    public static ListNode createList(int[] arr) {

        if (arr.length == 0)
            return null;

        ListNode head = new ListNode(arr[0]);
        ListNode curr = head;

        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }

        return head;
    }

    // Print linked list
    public static void printList(ListNode head) {

        while (head != null) {
            System.out.print(head.val);

            if (head.next != null)
                System.out.print(" -> ");

            head = head.next;
        }

        System.out.println();
    }

    // Driver
    public static void main(String[] args) {

        LinkedListRearrangement solution = new LinkedListRearrangement();

        int[] arr = {1, 2, 3, 4, 5};

        ListNode head = createList(arr);

        System.out.print("Original List : ");
        printList(head);

        head = solution.swapPairs(head);

        System.out.print("Swapped List  : ");
        printList(head);
    }
}