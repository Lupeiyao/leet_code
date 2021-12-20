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

    public TreeNode(Integer a, Integer b, Integer c) {
        this.val = a;
        if(b != null) {
            TreeNode node1 = new TreeNode(b);
            left = node1;
        }
        if(c != null) {
            TreeNode node2 = new TreeNode(c);
            right = node2;
        }
    }
}

