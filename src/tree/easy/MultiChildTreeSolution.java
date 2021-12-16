package tree.easy;

import tree.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2021/12/16 21:01
 * @Version 1.0
 */
public class MultiChildTreeSolution {

    public List<Integer> preorder(Node root) {
        List<Integer> result = new LinkedList<>();
        if(root == null) {
            return result;
        }
        LinkedList<Node> stack = new LinkedList<>();

        stack.add(root);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.val);
            for(int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }
        return result;
    }

    public List<Integer> postorder(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        if(root == null) {
            return result;
        }
        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            result.addFirst(node.val);
            for(int i = 0; i < node.children.size(); i++) {
                stack.push(node.children.get(i));
            }
        }
        return result;
    }

    public int maxDepth(Node root) {
        int result = 0;
        if(root == null) {
            return result;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            LinkedList<Node> nextLevel = new LinkedList<>();
            for(Node cur : queue) {
                for(Node node : cur.children) {
                    nextLevel.add(node);
                }
            }
            queue = nextLevel;
            result += 1;
        }
        return result;
    }
}
