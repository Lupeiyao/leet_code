package list.easy;

import list.ListNode;

/**
 * @Author Lunus
 * @Description TODO
 * @Date 2022/10/1 13:42
 * @Version 1.0
 */
public class Solutions {

    public static void main(String[] args) {

    }


    /*
     * @Author Lunus
     * @Description 给定两个由链表表示的整数，求和
     * @Link https://leetcode.cn/problems/add-two-numbers/?favorite=2cktkvj
     * @Solution 如题
     * Date 2022-10-01
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        int tmp = 0;
        while(l1 != null && l2 != null) {
            int val = (l1.val + l2.val + tmp) % 10;
            tmp = (l1.val + l2.val + tmp) > 9 ? 1 : 0;
            cur.next = new ListNode(val);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        cur.next = l1 != null ? l1 : l2;
        while(tmp != 0) {
            if(cur.next == null) {
                cur.next = new ListNode(1);
                tmp = 0;
            } else if(cur.next.val == 9){
                cur.next.val = 0;
                cur = cur.next;
                tmp = 1;
            } else {
                cur.next.val += 1;
                tmp = 0;
            }
        }
        return dummy.next;
    }
}
