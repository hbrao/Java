package patterns.twopntr;
public class RearrangeList {

    public static void reorder(ListNode head) {
        //Split the list
        ListNode slow = head, fast = head;
        while ( fast != null && fast.next != null ) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //Reverse second half. At the end prev points to head of the reversed second half.
        ListNode mid = slow;
        ListNode prev = null;
        while ( mid != null ) {
            ListNode tmp1 = mid.next;
            mid.next = prev;
            prev = mid;
            mid = tmp1;
        }

        //Merge : hFirst -> [] -> [] -> [] <- [] <- [] <- hSecond
        ListNode hFirst = head, hSecond = prev;
        while ( hFirst.next != null && hSecond.next != null ) {
            ListNode tmp1 = hFirst.next;
            ListNode tmp2 = hSecond;

            hSecond = hSecond.next;
            hFirst.next = tmp2;
            tmp2.next = tmp1;

            hFirst = tmp1;
        }

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        RearrangeList.reorder(head);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

}