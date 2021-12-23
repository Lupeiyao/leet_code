package tree.middle;

import tree.TreeNode;

import java.util.LinkedList;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2021/12/23 20:46
 * @Version 1.0
 */
public class BinaryTreeSolution {

    /*
     * @Author lupeiyao
     * @Description 层数最深的叶子节点和
     * @Link https://leetcode-cn.com/problems/deepest-leaves-sum/
     * @Solution 层序遍历
     * @Data 2021/12/23 22:06
     */
    public int deepestLeavesSum(TreeNode root) {
        int result = 0;
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while(!list.isEmpty()) {
            int leafSum = 0;
            LinkedList<TreeNode> newList = new LinkedList<>();
            for(TreeNode node : list) {
                if(node.left != null) {
                    newList.add(node.left);
                }
                if(node.right != null) {
                    newList.add(node.right);
                }
                if(node.left == null || node.right == null) {
                    leafSum += node.val;
                }
            }
            result = leafSum;
            list = newList;
        }
        return result;
    }
}
