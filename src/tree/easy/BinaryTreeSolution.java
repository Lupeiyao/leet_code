package tree.easy;

import tree.TreeNode;

import java.util.*;

/**
 * @Author lupeiyao
 * @Description easy难度二叉树相关solution
 * @Date 2021/12/8 19:34
 * @Version 1.0
 */
public class BinaryTreeSolution {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(4, 2, 5);
        root1.left.left = new TreeNode(1, 0, null);
        root1.left.right = new TreeNode(3);
        root1.right.right = new TreeNode(6);
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
     * @Description 找两个节点的最近公共祖先
     * @Link https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
     * @Solution 遍历，在map中存储每个节点的父节点，然后回溯p，set存储所有父节点，然后回溯q
     * @Data 2021/12/16 22:13
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<TreeNode, TreeNode> fatherMap = new HashMap<>();
        if(root == null || p == null || q == null) {
            return root;
        }
        if(p == q) {
            return p;
        }
        TreeNode cur = root;
        LinkedList<TreeNode> list = new LinkedList<>();
        while(cur != null || !list.isEmpty()) {
            while(cur != null) {
                list.add(cur);
                if(cur.left != null) {
                    fatherMap.put(cur.left, cur);
                }
                if(cur.right != null) {
                    fatherMap.put(cur.right, cur);
                }
                cur = cur.left;
            }
            cur = list.pollLast();
            cur = cur.right;
        }

        HashSet<TreeNode> pFathers = new HashSet<>();
        while(p != null) {
            pFathers.add(p);
            p = fatherMap.get(p);
        }
        while(q != null) {
            if(pFathers.contains(q)) {
                return q;
            }
            q = fatherMap.get(q);
        }
        return null;

    }

    /*
     * @Author lupeiyao
     * @Description 判断一棵二叉树是否是另一颗二叉树的子树
     * @Link https://leetcode-cn.com/tag/tree/problemset/
     * @Solution 前序遍历，如果cur.val = subRoot.val，判断same(cur, subRoot)(递归)
     * @Data 2021/12/16 22:10
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == subRoot) {
            return true;
        }
        if(root == null || subRoot == null) {
            return false;
        }
        TreeNode cur = root;
        LinkedList<TreeNode> list = new LinkedList<>();
        while(cur != null || !list.isEmpty()) {
            while(cur != null) {
                if(cur.val == subRoot.val && isSameTree(cur, subRoot)) {
                    return true;
                }
                list.add(cur);
                cur = cur.left;
            }
            cur = list.pollLast();
            cur = cur.right;
        }
        return false;
    }

    private boolean isSameTree(TreeNode root1, TreeNode root2) {
        if(root1 == root2) {
            return true;
        } else if(root1 == null && root2 != null) {
            return false;
        } else if(root1 != null && root2 == null) {
            return false;
        } else if(root1.val != root2.val) {
            return false;
        } else {
            return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
        }
    }

    /*
     * @Author lupeiyao
     * @Description 二叉树每个节点 node.val = min(node.left.val, node.right.val)
     * @Link https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/
     * @Solution 层序遍历，找到大于root.val的最小val，剪枝，如果本层全都是大于root.val的，则不需要继续了
     * @Data 2021/12/16 22:08
     */
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null || root.left == null) {
            return -1;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        // 每层加个null作为标志位
        list.add(root);
        list.add(null);
        int min = root.val;
        int secondMin = -1;
        long levelMin = min + 1;
        while(!list.isEmpty()) {
            TreeNode cur = list.pollFirst();
            if(cur == null) {
                if(!list.isEmpty()) {
                    list.add(null);
                }
                if(levelMin == min) {
                    levelMin = min + 1;
                    continue;
                } else {
                    break;
                }
            }
            if(cur.val > min) {
                if(secondMin == -1) {
                    secondMin = cur.val;
                } else {
                    secondMin = Math.min(secondMin, cur.val);
                }
            }
            if(levelMin == min + 1) {
                levelMin = cur.val;
            } else {
                levelMin = Math.min(levelMin, cur.val);
            }
            if(cur.left != null) {
                list.add(cur.left);
            }
            if(cur.right != null) {
                list.add(cur.right);
            }
        }
        return secondMin;
    }


    /*
     * @Author lupeiyao
     * @Description 二叉树最小深度
     * @Link https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
     * @Solution 层序遍历，找到第一个叶子节点
     * @Data 2021/12/16 22:07
     */
    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        list.add(null);
        int result = 1;
        while(!list.isEmpty()) {
            TreeNode node = list.pollFirst();
            if(node == null) {
                result++;
                list.add(null);
                continue;
            }
            if(node.left == null && node.right == null) {
                return result;
            }
            if(node.left != null) {
                list.add(node.left);
            }
            if(node.right != null) {
                list.add(node.right);
            }
        }
        return result;
    }

    /*
     * @Author lupeiyao
     * @Description 二叉树后序遍历
     * @Link https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
     * @Solution 后续遍历
     * @Data 2021/12/16 22:06
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pollLast();
            if(cur.right == null || cur.right == pre) {
                result.add(cur.val);
                pre = cur;
                cur = null;
            } else {
                stack.add(cur);
                cur = cur.right;
            }
        }
        return result;
    }

    /*
     * @Author lupeiyao
     * @Description 二叉树，节点值为0或1，求所有叶子节点代表的二进制串数字之和
     * @Link https://leetcode-cn.com/problems/sum-of-root-to-leaf-binary-numbers/
     * @Solution 层序遍历，辅助队列用来存储当前节点代表的二进制串数字之和
     * @Data 2021/12/16 22:03
     */
    public int sumRootToLeaf(TreeNode root) {
        int result = 0;
        if(root == null) {
            return result;
        }
        LinkedList<TreeNode> levelList = new LinkedList<>();
        LinkedList<Integer> valueList = new LinkedList<>();
        levelList.add(root);
        valueList.add(root.val);
        while(!levelList.isEmpty()) {
            LinkedList<TreeNode> nextLevel = new LinkedList<>();
            LinkedList<Integer> nextValueList = new LinkedList<>();
            for(int i = 0; i < levelList.size(); i++) {
                TreeNode node = levelList.get(i);
                int value = valueList.get(i);
                if(node.left != null) {
                    nextLevel.add(node.left);
                    nextValueList.add(value * 2 + node.left.val);
                }
                if(node.right != null) {
                    nextLevel.add(node.right);
                    nextValueList.add(value * 2 + node.right.val);
                }
                if(node.left == null && node.right == null) {
                    result += value;
                }
            }
            levelList = nextLevel;
            valueList = nextValueList;
        }
        return result;
    }


    /*
     * @Author lupeiyao
     * @Description 计算二叉树的坡度，结点坡度=ABS(sum(left) - sum(right))，树的坡度为所有节点坡度和
     * @Link https://leetcode-cn.com/problems/binary-tree-tilt/
     * @Solution 递归
     * @Data 2021/12/20 22:52
     */
    public int findTilt(TreeNode root) {
        if(root == null) {
            return 0;
        }
        HashMap<TreeNode, Integer> resultMap = new HashMap<>();
        return findTilt(root.left) + findTilt(root.right) + Math.abs(valSum(root.left, resultMap) - valSum(root.right, resultMap));
    }

    private int valSum(TreeNode root, HashMap<TreeNode, Integer> resultMap) {
        if(root == null) {
            return 0;
        }
        if(resultMap.containsKey(root)) {
            return resultMap.get(root);
        }
        int result = root.val + valSum(root.left, resultMap) + valSum(root.right, resultMap);
        resultMap.put(root, result);
        return result;
    }


    /*
     * @Author lupeiyao
     * @Description 判断两颗树从左到右的叶子叶子值序列是否相同
     * @Link https://leetcode-cn.com/problems/leaf-similar-trees/
     * @Solution 双指针，分别遍历两个树的叶子节点，依次判断
     * @Data 2021/12/20 21:58
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if(root1 == root2) {
            return true;
        }
        if(root1 == null || root2 == null) {
            return false;
        }
        BTLeaftIterator it1 = new BTLeaftIterator(root1);
        BTLeaftIterator it2 = new BTLeaftIterator(root2);
        TreeNode leaf1 = it1.nextLeaf();
        TreeNode leaf2 = it2.nextLeaf();
        while(leaf1 != null && leaf2 != null) {
            if(leaf1.val != leaf2.val) {
                return false;
            }
            leaf1 = it1.nextLeaf();
            leaf2 = it2.nextLeaf();
        }
        if(leaf1 == null && leaf2 == null) {
            return true;
        } else {
            return false;
        }
    }

    private class BTLeaftIterator {
        TreeNode cur;
        LinkedList<TreeNode> stack = new LinkedList<>();
        public BTLeaftIterator(TreeNode root) {
            this.cur = root;
        }

        TreeNode nextLeaf() {
            TreeNode leaf = null;
            while(cur != null || !stack.isEmpty()) {
                while(cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.pop();
                if(cur.left == null || cur.right == null) {
                    leaf = cur;
                }
                cur = cur.right;
                if(leaf != null) {
                    break;
                }
            }
            return leaf;
        }
    }


    /*
     * @Author lupeiyao
     * @Description 计算一个流输入的第k大元素
     * @Link https://leetcode-cn.com/problems/jBjn9C/
     * @Solution 小顶堆
     * @Data 2021/12/20 22:46
     */
    private class KthLargest {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int k;

         KthLargest(int k, int[] nums) {
            this.k = k;
            for(int i = 0; i < nums.length; i++) {
                if(i < k) {
                    pq.add(nums[i]);
                } else if(nums[i] > pq.peek()) {
                    pq.poll();
                    pq.add(nums[i]);
                }
            }
        }

        public int add(int val) {
            if(pq.size() < k) {
                pq.add(val);
            } else if(val > pq.peek()) {
                pq.poll();
                pq.add(val);
            }
            return pq.peek();
        }
    }


    /*
     * @Author lupeiyao
     * @Description 判断二叉树是否平衡树（任意节点的左右子树深度差不超过1）
     * @Link https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
     * @Solution 递归，计算每个节点的深度
     * @Data 2021/12/21 22:46
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }
        TreeNode cur = root;
        LinkedList<TreeNode> list = new LinkedList<>();
        HashMap<TreeNode, Integer> map = new HashMap<>();
        while(cur != null || !list.isEmpty()) {
            while(cur != null) {
                list.add(cur);
                if(Math.abs(getDepth(cur.left, map) - getDepth(cur.right, map)) > 1) {
                    return false;
                }
                cur = cur.left;
            }
            cur = list.pollLast();
            cur = cur.right;
        }
        return true;
    }

    private int getDepth(TreeNode root, HashMap<TreeNode, Integer> map) {
        if(root == null) {
            return 0;
        }
        if(map.containsKey(root)) {
            return map.get(root);
        } else {
            int depth = Math.max(getDepth(root.left, map), getDepth(root.right, map)) + 1;
            map.put(root, depth);
            return depth;
        }
    }


    /*
     * @Author lupeiyao
     * @Description 计算二叉树所有左叶子节点和
     * @Link https://leetcode-cn.com/problems/sum-of-left-leaves/
     * @Solution 先序遍历，左叶子节点一定是上一个节点的左子节点
     * @Data 2021/12/21 22:46
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int result = 0;
        if(root == null) {
            return result;
        }
        TreeNode cur = root;
        TreeNode pre = null;
        LinkedList<TreeNode> list = new LinkedList<>();
        while (cur != null || !list.isEmpty()) {
            while (cur != null) {
                if(pre != null && pre.left == cur && cur.left == null && cur.right == null) {
                    result += cur.val;
                }
                list.add(cur);
                pre = cur;
                cur = cur.left;
            }
            cur = list.pollLast();
            cur = cur.right;
        }
        return result;
    }


    /*
     * @Author lupeiyao
     * @Description 判断二叉树是否对称
     * @Link https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
     * @Solution 层序遍历，从左向右和从右向左完全相同
     * @Data 2021/12/21 22:46
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        LinkedList<TreeNode> leftQueue = new LinkedList<>();
        LinkedList<TreeNode> rightQueue = new LinkedList<>();
        if(root.left != null) {
            leftQueue.add(root.left);
        }
        if(root.right != null) {
            rightQueue.add(root.right);
        }
        while(!leftQueue.isEmpty() && leftQueue.size() == rightQueue.size()) {
            LinkedList<TreeNode> newLeft = new LinkedList<>();
            LinkedList<TreeNode> newRight = new LinkedList<>();
            while(!leftQueue.isEmpty()) {
                TreeNode left = leftQueue.pollFirst();
                TreeNode right = rightQueue.pollFirst();
                if(left.val != right.val) {
                    return false;
                }
                if(left.left != null && right.right != null) {
                    newLeft.add(left.left);
                    newRight.add(right.right);
                } else if(left.left == null && right.right == null) {
                    //pass
                } else {
                    return false;
                }
                if(left.right != null && right.left != null) {
                    newLeft.add(left.right);
                    newRight.add(right.left);
                } else if(left.right == null && right.left == null) {
                    //pass
                } else {
                    return false;
                }
            }
            leftQueue = newLeft;
            rightQueue = newRight;
        }
        if(leftQueue.size() == rightQueue.size()) {
            return true;
        } else {
            return false;
        }
    }

}
