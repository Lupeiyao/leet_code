package tree.easy;

import tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Author Lunus
 * @Description TODO
 * @Date 2021/12/21 16:10
 * @Version 1.0
 */
public class BinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, 3, 4);
        root.right = new TreeNode(2, 4, 3);
    }


    private class BSTIterator {
        LinkedList<TreeNode> lList = new LinkedList<>();
        LinkedList<TreeNode> rList = new LinkedList<>();
        TreeNode leftCur;
        TreeNode rightCur;

        BSTIterator(TreeNode root) {
            leftCur = root;
            rightCur = root;
        }

        boolean hasLeftNext() {
            return leftCur != null || !lList.isEmpty();
        }

        boolean hasRightNext() {
            return rightCur != null || !rList.isEmpty();
        }

        TreeNode getLeftNextNode() {
            while(leftCur != null) {
                lList.add(leftCur);
                leftCur = leftCur.left;
            }
            TreeNode result = lList.pollLast();
            leftCur = result.right;
            return result;
        }

        TreeNode getRightNextNode() {
            while(rightCur != null) {
                rList.add(rightCur);
                rightCur = rightCur.right;
            }
            TreeNode result = rList.pollLast();
            rightCur = result.left;
            return result;
        }

    }


    /*
     * @Author lupeiyao
     * @Description 计算BST中任意两节点之差的绝对值中得最小指
     * @Link https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
     * @Solution 中序遍历，找连续两个节点之间的最小值
     * @Data 2021/12/21 22:46
     */
    public int getMinimumDifference(TreeNode root) {
        int result = Integer.MAX_VALUE;
        if(root == null) {
            return result;
        }
        BSTIterator iter = new BSTIterator(root);
        TreeNode pre = null;
        while(iter.hasLeftNext()) {
            TreeNode cur = iter.getLeftNextNode();
            if(pre != null) {
                result = Math.min(result, Math.abs(pre.val - cur.val));
            }
            pre = cur;
        }
        return result;
    }



}
