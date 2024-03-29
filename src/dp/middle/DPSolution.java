package dp.middle;

import tree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2021/12/30 20:26
 * @Version 1.0
 */
public class DPSolution {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        System.out.println(new DPSolution().longestPalindrome1("babad"));
    }

    /*
     * @Author lupeiyao
     * @Description 给n对括号，生产所有合法的组合
     * @Link https://leetcode-cn.com/problems/IDBivT/
     * @Solution DFS，如果左括号多于右括号，左右都可以放，否则只能放左括号
     * @Data 2021/12/30 21:02
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        DFS(n, n, list, sb);
        return list;
    }

    private void DFS(int leftNum, int rightNum, List<String> result, StringBuilder sb) {
        if(leftNum == 0 && rightNum == 0) {
            result.add(sb.toString());
        } else if(leftNum < rightNum) {
            if(leftNum > 0) {
                sb.append('(');
                DFS(leftNum - 1, rightNum, result, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
            if(rightNum > 0) {
                sb.append(')');
                DFS(leftNum, rightNum - 1, result, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            if(leftNum > 0) {
                sb.append('(');
                DFS(leftNum - 1, rightNum, result, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    /*
     * @Author lupeiyao
     * @Description 构造所有包含N个节点的满二叉树
     * @Link https://leetcode-cn.com/problems/all-possible-full-binary-trees/
     * @Solution 递归，获取所有可能的左子树和右子树，然后遍历（剪枝：满二叉树的节点一定位奇数）
     * @Data 2021/12/30 21:21
     */
    public List<TreeNode> allPossibleFBT(int n) {
        return allPossibleFBT(n, new HashMap<>());
    }
    public List<TreeNode> allPossibleFBT(int n, HashMap<Integer, List<TreeNode>> map) {
        List<TreeNode> result = new LinkedList<>();
        if(map.containsKey(n)) {
            return map.get(n);
        }
        if(n == 1) {
            TreeNode root = new TreeNode(0);
            result.add(root);
        } else {
            for(int i = 1; i < n; i+=2) {
                List<TreeNode> leftTress = allPossibleFBT(i, map);
                List<TreeNode> rightTress = allPossibleFBT(n - i - 1, map);
                for(TreeNode leftNode : leftTress) {
                    for(TreeNode rightNode : rightTress) {
                        TreeNode root = new TreeNode(0);
                        root.left = leftNode;
                        root.right = rightNode;
                        result.add(root);
                    }
                }
            }
        }
        map.put(n, result);
        return result;
    }


    /*
     * @Author lupeiyao
     * @Description 给定每个房子粉刷三种颜色的消耗，求最省钱的方式（相邻的颜色不能相同）
     * @Link https://leetcode-cn.com/problems/JEj789/submissions/
     * @Solution dp[i][j]表示当前i房子粉刷j颜色的最低消耗
     * @Data 2021/12/30 21:28
     */
    public int minCost(int[][] costs) {
        int[][] dp = new int[costs.length][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for(int i = 1; i < dp.length; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        return Math.min(Math.min(dp[dp.length - 1][0], dp[dp.length - 1][1]), dp[dp.length - 1][2]);
    }


    /*
     * @Author lupeiyao
     * @Description 给5个不同字母可以组成长度为n的字符串，计算所有符合字典序的可能
     * @Link https://leetcode-cn.com/problems/count-sorted-vowel-strings/
     * @Solution dp[i][j]表示长为i，以第j个字母打头的所有可能数
     * @Data 2021/12/30 21:49
     */
    public int countVowelStrings(int n) {
        int[][] dp = new int[n + 1][5];
        for(int i = 0; i < 5; i++) {
            dp[1][i] = 1;
        }
        for(int i = 2; i <= n; i++) {
            dp[i][4] = dp[i - 1][4];
            dp[i][3] = dp[i][4] + dp[i - 1][3];
            dp[i][2] = dp[i][3] + dp[i - 1][2];
            dp[i][1] = dp[i][2] + dp[i - 1][1];
            dp[i][0] = dp[i][1] + dp[i - 1][0];
        }
        return dp[n][0] + dp[n][1] + dp[n][2] + dp[n][3] + dp[n][4];
    }

    /*
     * @Author lupeiyao
     * @Description 给偶数堆石子以及每堆数量nums（总和为奇数，一定有人胜出），两个人轮流拿，
     * 每人只能拿两侧的其中一堆，石子多的人赢，求先手是否有必胜策略
     * @Link https://leetcode-cn.com/problems/stone-game/
     * @Solution dp[i][j]表示先手从石子堆i->j拿，最多比后手多的石子数，dp[start][end]>0则必胜
     * dp[i][j+x] = max(nums[i] - dp[i+1][j+x], nums[j+x] - dp[i][j+x-1])
     * @Data 2021/12/30 22:21
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = Math.abs(piles[i] - piles[i + 1]);
        }
        for(int i = 2; i <= n - 1; i++) {
            for(int j = 0; j + i < n; j++) {
                dp[j][j + i] = Math.max(piles[j] - dp[j + 1][j + i], piles[j + i] - dp[j][j + i -1]);
            }
        }
        return dp[0][n - 1] > 0;
    }

    /*
     * @Author lupeiyao
     * @Description 给定长m、宽m的网格，从左上角走上右上角有多少种走法（只能向上或向右）
     * @Link
     * @Solution dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     * @Data 2022/1/6 16:28
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i = 0 ; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i - 1 >= 0) dp[i][j] += dp[i - 1][j];
                if(j - 1 >= 0) dp[i][j] += dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
     * @Author lupeiyao
     * @Description 给定数组num，取值均不同，求num[i]>num[j]>num[k]（或小于）的数量(i<j<k)
     * @Link
     * @Solution biggerNum[i]表示num[i]后有几个比num[i]大。
     * if(num[j]>num[i]） 则num[i]可以组成biggerNum[j]个组合
     * @Data 2022/1/6 16:25
     */
    public int numTeams(int[] rating) {
        int result = 0;
        int[] biggerNum = new int[rating.length];
        int[] lessNum = new int[rating.length];
        for(int i = 0; i < rating.length; i++) {
            for(int j = i + 1; j < rating.length; j++) {
                if(rating[j] > rating[i]) {
                    biggerNum[i] += 1;
                } else {
                    lessNum[i] += 1;
                }
            }
        }
        for(int i = 0; i < rating.length; i++) {
            for(int j = i + 1; j < rating.length; j++) {
                if(rating[j] > rating[i]) {
                    result += biggerNum[j];
                }
                if(rating[j] < rating[i]) {
                    result += lessNum[j];
                }
            }
        }
        return result;
    }


    /*
     * @Author lupeiyao
     * @Description 最长回文子串
     * @Link https://leetcode-cn.com/problems/longest-palindromic-substring/
     * @Solution dp[i][j] = true表示str[i:j]为回文串,次计算长度为2,3，4...的所有子串是否回文
     * @Data 2022/1/6 16:22
     */
    public String longestPalindrome1(String s) {
        if(s == null || s.length() < 2) return s;
        int[][] dp = new int[s.length()][s.length()];
        int start = 0;
        int end = 0;
        for(int length = 1; length <= s.length(); length++) {
            for(int i = 0; i + length - 1 < s.length(); i++) {
                if(length == 1) {
                    dp[i][i + length - 1] = 1;
                } else if(s.charAt(i) == s.charAt(i + length - 1) && (i + 1 > i + length - 1 - 1 || dp[i + 1][i + length - 1 - 1] == 1)) {
                    dp[i][i + length - 1] = 1;
                    start = i;
                    end = i + length - 1;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    /*
     * @Author lupeiyao
     * @Description 最长的不包含重复字符的子串
     * @Link https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     * @Solution dp[i] = max(dp[i-1], lastIdx[str[i]])，dp[i]表示以str[i]为结尾的最长子串的
     * 第一个字符的前一个位置，lastIdx[str[i]]表示当前字符的上一个位置
     * @Data 2022/1/14 15:20
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        if(s.length() == 1) return 1;
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0, j = -1; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)));
            }
            map.put(s.charAt(i), i);
            result = Math.max(result, i - j);
        }
        return result;
    }


    /*
     * @Author lupeiyao
     * @Description 给定字符串，判断能否由给定的单词组成，单词可重复多次使用
     * @Link
     * @Solution dp[i]表示str.substring(0, i)能否由给定单词组成
     * @Data 2022/4/19 20:28
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0) {
            return true;
        }
        Set<String> set = new HashSet<>(wordDict);
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < i; j++) {
                if(set.contains(s.substring(j, i)) && dp[j] == 1) {
                    dp[i] = 1;
                    break;
                }
            }
        }
        return dp[s.length()] == 1;
    }


    /*
     * @Author lupeiyao
     * @Description 给定数据1-n，求可组成的左右不同二叉搜索树
     * @Link
     * @Solution 遍历每个节点作为根节点，result += left_num * right_num，存储历史结果
     * @Data 2022/5/5 20:41
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i < dp.length; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
