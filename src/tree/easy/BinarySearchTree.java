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
        root.right = new TreeNode(2, 2, null);
        System.out.println(new BinarySearchTree().findMode(root));
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
     * @Description BST中查找是否有两个节点满足a.val + b.val = k
     * @Link https://leetcode-cn.com/problems/opLdQZ/
     * @Solution 双指针，分别从小到大和从大到小移动
     * @Data 2021/12/16 22:19
     */
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) {
            return false;
        }
        BSTIterator iterator = new BSTIterator(root);
        TreeNode left = iterator.getLeftNextNode();
        TreeNode right = iterator.getRightNextNode();
        while(left != null && right != null && right != left) {
            if(left.val + right.val == k) {
                return true;
            } else if(left.val + right.val > k) {
                right = iterator.hasRightNext() ? iterator.getRightNextNode() : null;
            } else {
                left = iterator.hasLeftNext() ? iterator.getLeftNextNode() : null;
            }
        }
        return false;
    }


    /*
     * @Author lupeiyao
     * @Description BST的最近公共父节点
     * @Link https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
     * @Solution 从root开始，找到第一个大于一个、小于一个即可
     * @Data 2021/12/20 22:50
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lessNode = p.val < q.val ? p : q;
        TreeNode bigNode = lessNode == p ? q : p;
        TreeNode node = root;
        while(node != p && node != q && (node.val > bigNode.val || node.val < lessNode.val)) {
            if(node.val > bigNode.val) {
                node = node.left;
            } else if(node.val < lessNode.val) {
                node = node.right;
            }
        }
        return node;
    }

    /*
     * @Author lupeiyao
     * @Description 把BST转换为链表（node.left = null, node.right = next_node）
     * @Link https://leetcode-cn.com/problems/binode-lcci/
     * @Solution 中序、逆中序遍历都可，存储前一个节点，注意中序遍历需要等遍历下个节点时才能cur.right = next，逆中序则相反
     * @Data 2021/12/20 22:46
     */
    public TreeNode convertBiNode(TreeNode root) {
        if(root == null) {
            return root;
        }
        BSTIterator iterator = new BSTIterator(root);
        TreeNode result = null;
        TreeNode pre = null;
        while(iterator.hasRightNext()) {
            TreeNode cur = iterator.getRightNextNode();
            if(pre == null) {
                pre = cur;
            } else {
                cur.right = pre;
                pre.left = null;
                pre = cur;
            }
            result = cur;
        }
        result.left = null;
        return result;
    }


    /*
     * @Author lupeiyao
     * @Description 找到BST中所有节点的众数
     * @Link https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
     * @Solution 中序遍历，保存状态
     * @Data 2021/12/21 22:46
     */
    public int[] findMode(TreeNode root) {
        TreeNode cur = root;
        LinkedList<TreeNode> list = new LinkedList<>();
        Integer pre = null;
        int preCnt = 0;
        int maxCnt = 0;
        LinkedList<Integer> result = new LinkedList<>();
        while(cur != null || !list.isEmpty()) {
            while(cur != null) {
                list.add(cur);
                cur = cur.left;
            }
            cur = list.pollLast();
            if(pre == null) {
                pre = cur.val;
            }
            if(cur.val == pre) {
                preCnt++;
            } else {
                if(preCnt > maxCnt) {
                    maxCnt = preCnt;
                    result.clear();
                    result.add(pre);
                } else if(preCnt == maxCnt) {
                    result.add(pre);
                }
                preCnt = 1;
            }
            pre = cur.val;
            cur = cur.right;
        }
        if(preCnt > maxCnt) {
            result.clear();
            result.add(pre);
        } else if(preCnt == maxCnt) {
            result.add(pre);
        }
        int[] temp = new int[result.size()];
        for(int i = 0; i < temp.length; i++) {
            temp[i] = result.get(i);
        }
        return temp;
    }

}
