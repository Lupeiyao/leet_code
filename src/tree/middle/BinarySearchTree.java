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

    /*
     * @Author lupeiyao
     * @Description 给定二叉检索数，判读是否合法
     * @Link
     * @Solution 前序遍历，查看是否满足pre < cur
     * @Data 2022/5/5 20:43
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null) return false;
        LinkedList<TreeNode> list = new LinkedList<>();
        Integer preVal = null;
        while(root != null || !list.isEmpty()) {
            while(root != null) {
                list.add(root);
                root = root.left;
            }
            root = list.removeLast();
            if(preVal != null && root.val <= preVal) {
                return false;
            }
            preVal = root.val;
            root = root.right;
        }
        return true;
    }
}





