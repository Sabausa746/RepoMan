import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1 = l1.next;
        l1.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2 = l2.next;
        l2.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);


        System.out.println(sol.findSubstring("wordgoodgoodgoodbestword",new String[]{"foo","bar"}));
    }
}