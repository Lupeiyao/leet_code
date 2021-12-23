package tree.middle;

import tree.TreeNode;
import java.util.LinkedList;

/**
 * @Author Lunus
 * @Description 剑指 Offer II 055. 二叉搜索树迭代器 https://leetcode-cn.com/problems/kTOapQ/
 * @Date 2021/11/7 20:51
 * @Version 1.0
 */

public class BinarySearchTree {

    public static void main(String[] args) {
    }


    private class BSTIterator {

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
}





