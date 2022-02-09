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
}
