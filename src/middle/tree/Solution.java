package middle.tree;

import java.util.LinkedList;

/**
 * @Author Lunus
 * @Description TODO
 * @Date 2021/11/7 20:51
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        TreeNode temp1 = new TreeNode(3);
        TreeNode temp2 = new TreeNode(15);
        root.left = temp1;
        root.right = temp2;
        TreeNode temp3 = new TreeNode(9);
        TreeNode temp4 = new TreeNode(20);
        temp2.left = temp3;
        temp2.right = temp4;
        BSTIterator iter = new BSTIterator(root);
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
    }

}

/*
剑指 Offer II 055. 二叉搜索树迭代器
https://leetcode-cn.com/problems/kTOapQ/
 */
class BSTIterator {

    LinkedList<TreeNode> travelNodes = new LinkedList<>();
    TreeNode curr;

    public BSTIterator(TreeNode root) {
        curr = root;
    }

    public int next() {
        while (curr != null) {
            travelNodes.add(curr);
            curr = curr.left;
        }
        TreeNode next = travelNodes.pollLast();
        curr = next.right;
        return next.val;
    }


    public boolean hasNext() {
        return curr != null || travelNodes.size() > 0;
    }


}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}
