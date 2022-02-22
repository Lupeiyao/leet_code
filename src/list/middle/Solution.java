package list.middle;

import list.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2022/1/19 20:09
 * @Version 1.0
 */
public class Solution {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next = b;
        ListNode c = new ListNode(3);
        b.next = c;
        ListNode d = new ListNode(4);
        c.next = d;
        ListNode e = new ListNode(5);
        d.next = e;
        System.out.println(new Solution().rotateRight(a, 2));
    }


    /*
     * @Author lupeiyao
     * @Description 删除链表的倒数第N个节点
     * @Link https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     * @Solution 双指针，first在second的前面n+1个节点，同时移动，first走到头，secend是倒数
     * 第n+1个，同时在head前面构造一个dummy节点，防止在长度n的链表删除倒数第n个要特殊判断
     * @Data 2022/1/19 20:35
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for(int i = 0; i < n; i++) {
            first = first.next;
        }
        while(first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(list1 != null && list2 != null) {
            if(list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if(list1 == null) {
            cur.next = list2;
        } else {
            cur.next = list1;
        }
        return dummy.next;
    }

    /*
     * @Author lynnliu
     * @Description 两两交换链表的节点
     * @Link https://leetcode-cn.com/problems/swap-nodes-in-pairs/
     * @Solution dummy节点
     * @Data 2022/2/17 7:21 PM
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while(cur != null) {
            ListNode next = cur.next;
            if(next == null) {
                return dummy.next;
            }
            ListNode nextNext = next.next;
            if(nextNext == null) {
                return dummy.next;
            }
            cur.next = nextNext;
            next.next = nextNext.next;
            nextNext.next = next;
            cur = cur.next.next;
        }
        return dummy.next;
    }


    /*
     * @Author lynnliu
     * @Description 旋转链表，给定链表，按照循环链表的方式把每个节点向后挪动k个位置
     * @Link https://leetcode-cn.com/problems/rotate-list/
     * @Solution 快慢指针，快指针在慢指针的前面k个
     * @Data 2022/2/22 8:09 PM
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode temp = head;
        int length = 0;
        while(temp != null) {
            length += 1;
            temp = temp.next;
        }
        k = k % length;
        if(k == 0) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        for(int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode result = slow.next;
        fast.next = head;
        slow.next = null;
        return result;
    }

}
