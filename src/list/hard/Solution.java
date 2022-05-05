package list.hard;

import list.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2022/1/19 21:01
 * @Version 1.0
 */
public class Solution {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        System.out.println(new Solution().reverseKGroup(head, 2));
    }

    /*
     * @Author lupeiyao
     * @Description 合并K个排序数据
     * @Link https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
     * @Solution 方法一：与合并两个相同，只是变成多个。 方法二：分治，两两合并，再两两合并
     * @Data 2022/1/19 21:19
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }
        List<ListNode> validHeads = new ArrayList<>();
        for(ListNode head : lists) {
            if(head != null) validHeads.add(head);
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(validHeads.size() > 0) {
            if(validHeads.size() == 1) {
                cur.next = validHeads.get(0);
                break;
            }
            int minVal = validHeads.get(0).val;
            int minIdx = 0;
            for(int i = 1; i < validHeads.size(); i++) {
                if(validHeads.get(i).val < minVal) {
                    minVal = validHeads.get(i).val;
                    minIdx = i;
                }
            }
            cur.next = validHeads.get(minIdx);
            cur = cur.next;
            if(cur.next != null) {
                validHeads.add(cur.next);
            }
            validHeads.remove(minIdx);
        }
        return dummy.next;

    }

    /*
     * @Author lynnliu k个一组翻转链表
     * @Description 每k个node翻转一次，最后小于k个则不翻转
     * @Link https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     * @Solution dummy节点，list或者函数翻转k个
     * @Data 2022/2/17 7:17 PM
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k < 2) return head;
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        ArrayList<ListNode> list = new ArrayList<>(k);
        while(cur != null) {
            ListNode temp = cur.next;
            for(int i = 0; temp != null && i < k; i++, temp = temp.next) {
                list.add(temp);
            }
            if(list.size() != k) {
                return dummy.next;
            }
            list.get(0).next = list.get(k - 1).next;
            cur.next = list.get(k - 1);
            for(int i = k - 1; i > 0; i--) {
                list.get(i).next = list.get(i - 1);
            }
            cur = list.get(0);
            list.clear();
        }
        return dummy.next;
    }
}
