public class LC82_RemoveDuplicatesII {

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

    static class Solution {

        public ListNode deleteDuplicates(ListNode head) {

            if (head == null || head.next == null)
                return head;

            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            ListNode p = dummy;
            ListNode c = head;

            while (c != null) {

                if (c.next != null && c.val == c.next.val) {

                    while (c.next != null && c.val == c.next.val) {
                        c = c.next;
                    }

                    p.next = c.next;
                }
                else {
                    p = p.next;
                }

                c = c.next;
            }

            return dummy.next;
        }
    }

    // Create linked list from array
    static ListNode createList(int[] arr) {

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
    static void printList(ListNode head) {

        while (head != null) {

            System.out.print(head.val);

            if (head.next != null)
                System.out.print(" -> ");

            head = head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 3, 4, 4, 5};

        ListNode head = createList(arr);

        System.out.println("Original List:");
        printList(head);

        Solution obj = new Solution();

        head = obj.deleteDuplicates(head);

        System.out.println("\nAfter Removing Duplicates:");
        printList(head);
    }
}