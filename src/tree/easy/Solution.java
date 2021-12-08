package tree.easy;

import tree.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Author lupeiyao
 * @Description easy难度二叉树相关solution
 * @Date 2021/12/8 19:34
 * @Version 1.0
 */
public class Solution {

    /*
     * @Author lupeiyao
     * @Description 求二叉树val的集合大小
     * @Link https://leetcode-cn.com/problems/sZ59z6/
     * @Solution 层序遍历
     * @Data 2021/12/8 20:02
     */
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

    // 中序遍历
    public int numColor2(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        Set<Integer> colorSet = new HashSet<>();
        TreeNode cur = root;
        while(cur != null || list.size() != 0) {
            while(cur != null) {
                list.add(cur);
                cur = cur.left;
            }
            TreeNode pop = list.pollLast();
            colorSet.add(pop.val);
            cur = pop.right;
        }
        return colorSet.size();
    }

    // 先序遍历
    public int numColor3(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        Set<Integer> colorSet = new HashSet<>();
        TreeNode cur = root;
        while(cur != null || list.size() != 0) {
            while(cur != null) {
                colorSet.add(cur.val);
                list.add(cur);
                cur = cur.left;
            }
            TreeNode pop = list.pollLast();
            cur = pop.right;
        }
        return colorSet.size();
    }

    /*
     * @Author lupeiyao
     * @Description 二叉搜索树，求和所有[low,high]的节点val
     * @Link https://leetcode-cn.com/problems/range-sum-of-bst/
     * @Solution 递归+剪枝
     * @Data 2021/12/8 20:15
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        if(root == null) {
            return 0;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if(root.val <= low) {
            left = null;
        }
        if(root.val >= high) {
            right = null;
        }
        if(root.val >= low && root.val <= high) {
            sum += root.val;
        }
        return sum + rangeSumBST(left, low, high) + rangeSumBST(right, low, high);
    }

    // 层序遍历+剪枝
    public int rangeSumBST2(TreeNode root, int low, int high) {
        int sum = 0;
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while(!list.isEmpty()) {
            TreeNode node = list.pollFirst();
            if(node == null) {
                continue;
            }
            if(node.val < low) {
                list.add(node.right);
            } else if(node.val > high) {
                list.add(node.left);
            } else {
                sum += node.val;
                list.add(node.left);
                list.add(node.right);
            }
        }
        return sum;
    }

    /*
     * @Author lupeiyao
     * @Description 返回镜像二叉树
     * @Link https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
     * @Solution 递归
     * @Data 2021/12/8 20:35
     */
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null) {
            return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = mirrorTree(right);
        root.right = mirrorTree(left);
        return root;
    }

    // 层序遍历
    public TreeNode mirrorTree2(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while(!list.isEmpty()) {
            TreeNode node = list.pollFirst();
            if(node == null) {
                continue;
            }
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;
            list.add(node.left);
            list.add(node.right);
        }
        return root;
    }

    /*
     * @Author lupeiyao
     * @Description 求二叉树深度
     * @Link https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/
     * @Solution 递归
     * @Data 2021/12/8 20:40
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // 层序遍历
    public int maxDepth2(TreeNode root) {
        if(root == null) {
            return  0;
        }
        int maxDepth = 0;
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while(!list.isEmpty()) {
            LinkedList<TreeNode> nextLevelList = new LinkedList<>();
            for(TreeNode node : list) {
                if(node.left != null) {
                    nextLevelList.add(node.left);
                }
                if(node.right != null) {
                    nextLevelList.add(node.right);
                }
            }
            maxDepth += 1;
            list = nextLevelList;
        }
        return maxDepth;
    }

    /*
     * @Author lupeiyao
     * @Description 给定升序数组，生成一颗高度最小的二叉搜索树
     * @Link https://leetcode-cn.com/problems/minimum-height-tree-lcci/
     * @Solution 中序遍历
     * @Data 2021/12/8 21:11
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length < 1) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length -1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int low, int high) {
        if(low == high) {
            TreeNode root = new TreeNode(nums[low]);
            return root;
        } else if(low + 1 == high) {
            TreeNode root = new TreeNode(nums[high]);
            TreeNode left = new TreeNode(nums[low]);
            root.left = left;
            return root;
        } else {
            int middle = (low + high) / 2;
            TreeNode root = new TreeNode(nums[middle]);
            root.left = sortedArrayToBST(nums, low, middle -1);
            root.right = sortedArrayToBST(nums, middle + 1, high);
            return root;
        }
    }
}
