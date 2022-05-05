package tree.middle;

import tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

    /*
     * @Author lupeiyao
     * @Description 给定二叉树的前序和中序遍历，重构二叉树
     * @Link
     * @Solution 先根据前序的第一个节点构造根，然后构造左子树和右子树（递归）
     * @Data 2022/5/5 20:45
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, map);
    }

    public TreeNode helper(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd, Map<Integer, Integer> map) {
        if(preStart > preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int idx = map.get(preorder[preStart]);
        int leftNum = idx - inStart;
        TreeNode left = helper(preorder, inorder, preStart + 1, preStart + leftNum, inStart, idx - 1, map);
        TreeNode right = helper(preorder, inorder, preStart + leftNum + 1, preEnd, idx + 1, inEnd, map);
        root.left = left;
        root.right = right;
        return root;
    }

    /*
     * @Author lupeiyao
     * @Description 把二叉树按照前序遍历转换为链表（还是树，只是左节点都为null，右结点表示下一个节点）
     * @Link
     * @Solution 前序遍历（每次修改pre.right = cur, pre.left = null)，但要保存树的结构
     * 通过cur = stack.pop()，stack.push(cur.right),stack.push(cur.left)即可
     * @Data 2022/5/5 20:47
     */
    public void flatten(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        TreeNode cur = null;
        TreeNode pre = null;
        while(!list.isEmpty()) {
            cur = list.removeLast();
            if(pre != null) {
                pre.left = null;
                pre.right = cur;
            }
            if(cur.right != null) {
                list.add(cur.right);
            }
            if(cur.left != null) {
                list.add(cur.left);
            }
            pre = cur;
        }
    }
}
