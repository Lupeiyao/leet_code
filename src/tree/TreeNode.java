package tree;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2021/12/8 19:35
 * @Version 1.0
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int a, int b, int c) {
        this.val = a;
        TreeNode node1 = new TreeNode(b);
        TreeNode node2 = new TreeNode(c);
        left = node1;
        right = node2;
    }
}

