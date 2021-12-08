package tree.easy;

import tree.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @Author lupeiyao
 * @Description 二叉树遍历 https://leetcode-cn.com/problems/sZ59z6/
 * @Date 2021/12/8 19:34
 * @Version 1.0
 */
public class NumberColor {
    public int numColor(TreeNode root) {
        Set<Integer> colorSet = new HashSet<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.pollFirst();
            colorSet.add(curr.val);
            if(curr.left != null) {
                queue.add(curr.left);
            }
            if(curr.right != null) {
                queue.add(curr.right);
            }
        }
        return colorSet.size();
    }
}
