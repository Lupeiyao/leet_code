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
        System.out.println(new Solution().removeNthFromEnd(null, 1));
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
}
