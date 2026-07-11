public class LC328_OddEvenLinkedList {

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

    // -------------------------------------------------------
    // Utility Methods
    // -------------------------------------------------------

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

    static void printList(ListNode head) {

        while (head != null) {

            System.out.print(head.val);

            if (head.next != null)
                System.out.print(" -> ");

            head = head.next;
        }

        System.out.println();
    }

    // -------------------------------------------------------
    // Solution 1 : My Flag-Based Approach
    // -------------------------------------------------------

    static class MySolution {

        /*
         * Idea:
         * -----
         * Traverse the list once.
         *
         * Maintain two separate chains:
         * 1. Odd indexed nodes
         * 2. Even indexed nodes
         *
         * A flag (t) is used to decide whether the current node
         * belongs to the odd or even list.
         *
         * Since original nodes are reused, disconnect every node
         * after attaching it to avoid unwanted links.
         *
         * Finally, connect the odd list with the even list.
         *
         * Time Complexity : O(n)
         * Space Complexity: O(1)
         */

        public ListNode oddEvenList(ListNode head) {

            if (head == null || head.next == null)
                return head;

            ListNode dummyOdd = new ListNode(0);
            ListNode odd = dummyOdd;

            ListNode dummyEven = new ListNode(0);
            ListNode even = dummyEven;

            int t = 1;

            while (head != null) {

                if (t == 1) {

                    odd.next = head;
                    odd = odd.next;

                    head = head.next;

                    // Break old connection
                    odd.next = null;

                    t = 0;

                } else {

                    even.next = head;
                    even = even.next;

                    head = head.next;

                    // Break old connection
                    even.next = null;

                    t = 1;
                }
            }

            odd.next = dummyEven.next;

            return dummyOdd.next;
        }
    }

    // -------------------------------------------------------
    // Solution 2 : Optimal In-place Pointer Rewiring
    // -------------------------------------------------------

    static class OptimalSolution {

        /*
         * Idea:
         * -----
         * Keep odd and even nodes inside the same list.
         *
         * odd.next should skip the current even node.
         * even.next should skip the current odd node.
         *
         * Save the head of the even list because the even pointer
         * moves during traversal.
         *
         * Finally connect the last odd node to evenHead.
         *
         * Time Complexity : O(n)
         * Space Complexity: O(1)
         */

        public ListNode oddEvenList(ListNode head) {

            if (head == null || head.next == null)
                return head;

            ListNode odd = head;
            ListNode even = head.next;
            ListNode evenHead = even;

            while (even != null && even.next != null) {

                odd.next = even.next;
                odd = odd.next;

                even.next = odd.next;
                even = even.next;
            }

            odd.next = evenHead;

            return head;
        }
    }

    // -------------------------------------------------------
    // Driver
    // -------------------------------------------------------

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};

        System.out.println("Original List:");
        printList(createList(arr));

        // Your Solution
        ListNode list1 = createList(arr);
        MySolution my = new MySolution();

        System.out.println("\nMy Flag-Based Solution:");
        printList(my.oddEvenList(list1));

        // Optimal Solution
        ListNode list2 = createList(arr);
        OptimalSolution optimal = new OptimalSolution();

        System.out.println("\nOptimal Pointer-Rewiring Solution:");
        printList(optimal.oddEvenList(list2));
    }
}
