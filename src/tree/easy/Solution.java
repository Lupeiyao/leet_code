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

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        root1.left = node1;
        root1.right = node2;
        node1.left = node3;

        TreeNode root2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        root2.left = node4;
        root2.right = node5;
        node4.right = node6;
        node5.right = node7;
        new Solution().mergeTrees(root1, root2);
    }

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

    /*
     * @Author lupeiyao
     * @Description 把二叉搜索树的每个节点替换成所有大于等于该节点值得和
     * @Link https://leetcode-cn.com/problems/w6cpku/
     * @Solution 逆中序遍历即可
     * @Data 2021/12/9 21:19
     */
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        LinkedList<TreeNode> list = new LinkedList<>();
        TreeNode node = root;
        while(node != null || !list.isEmpty()) {
            while(node != null) {
                list.add(node);
                node = node.right;
            }
            node = list.pollLast();
            sum += node.val;
            node.val = sum;
            node = node.left;
        }
        return root;
    }


    /*
     * @Author lupeiyao
     * @Description 合并两颗二叉树
     * @Link https://leetcode-cn.com/problems/merge-two-binary-trees/
     * @Solution 层序遍历，如果两个左节点都非空，则左节点加入队列，否则结果等于非空的那个节点，右一样
     * @Data 2021/12/9 23:05
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null) {
            return root2;
        }
        if(root2 == null) {
            return root1;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        LinkedList<TreeNode> list1 = new LinkedList<>();
        LinkedList<TreeNode> list2 = new LinkedList<>();
        TreeNode result = new TreeNode(root1.val + root2.val);
        list.add(result);
        list1.add(root1);
        list2.add(root2);
        while(!list1.isEmpty() && !list2.isEmpty()) {
            TreeNode node = list.pollFirst();
            TreeNode node1 = list1.pollFirst();
            TreeNode node2 = list2.pollFirst();
            if(node1.left != null && node2.left != null) {
                node.left = new TreeNode(node1.left.val + node2.left.val);
                list.add(node.left);
                list1.add(node1.left);
                list2.add(node2.left);
            } else if(node1.left == null) {
                node.left = node2.left;
            } else {
                node.left = node1.left;
            }

            if(node1.right != null && node2.right != null) {
                node.right = new TreeNode(node1.right.val + node2.right.val);
                list.add(node.right);
                list1.add(node1.right);
                list2.add(node2.right);
            } else if(node1.right == null) {
                node.right = node2.right;
            } else {
                node.right = node1.right;
            }
        }
        return result;
    }


    /*
     * @Author lupeiyao
     * @Description 找到BST的某个值节点
     * @Link https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
     * @Solution 正常检索即可
     * @Data 2021/12/9 23:07
     */
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode cur = root;
        while(cur != null) {
            if(val == cur.val) {
                return cur;
            } else if(val > cur.val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return cur;
    }

    /*
     * @Author lupeiyao
     * @Description 找BST第K大的节点
     * @Link https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
     * @Solution 逆中序遍历即可
     * @Data 2021/12/9 23:08
     */
    public int kthLargest(TreeNode root, int k) {
        LinkedList<TreeNode> list = new LinkedList<>();
        TreeNode cur = root;
        int cnt = 0;
        while(cur != null || !list.isEmpty()) {
            while(cur != null) {
                list.add(cur);
                cur = cur.right;
            }
            cur = list.pollLast();
            cnt += 1;
            if(cnt == k) {
                return cur.val;
            }
            cur = cur.left;
        }
        return -1;
    }

    /*
     * @Author lupeiyao
     * @Description 中序遍历BT
     * @Link https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     * @Solution 中序遍历
     * @Data 2021/12/9 23:09
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        TreeNode cur = root;
        int cnt = 0;
        while(cur != null || !list.isEmpty()) {
            while(cur != null) {
                list.add(cur);
                cur = cur.left;
            }
            cur = list.pollLast();
            result.add(cur.val);
            cur = cur.right;
        }
        return  result;
    }
}
