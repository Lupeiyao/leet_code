package dp.hard;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2022/4/25 16:44
 * @Version 1.0
 */
public class Solution {


    /*
     * @Author lupeiyao
     * @Description 给定括号字符串，判断最长有效字符串长度
     * @Link
     * @Solution dp[i]表示已s[i]为结尾的子串的最长有效长度，
     * dp[i] = 0 if s[i] == '('
     * dp[i] = dp[i - 2] + 2 if dp[i] == ')' && dp[i - 1] == '('
     * dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2] if dp[i] == ')' && s[i - dp[i - 1] - 1] == '('
     * @Data 2022/4/25 16:44
     */
    public int longestValidParentheses(String s) {
        int max = 0;
        if(s == null || s.length() < 2) {
            return max;
        }
        int[] dp = new int[s.length()];
        for(int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == ')' && s.charAt(i - 1) == '(') {
                dp[i] = i - 2 >= 0 ? 2 + dp[i - 2] : 2;
                max = Math.max(max, dp[i]);
            } else if(ch == ')' && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = i - dp[i - 1] -2 >= 0 ? dp[i - 1] + 2 + dp[i - dp[i - 1] - 2] : dp[i - 1] + 2;
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

	/*
	 * @Author lynnliu
	 * @Description 计算两个字符串的最短编辑距离
	 * @Link https://leetcode-cn.com/problems/edit-distance/
	 * @Solution dp[i][j]表示word1[0：i)和word[0：j)的最短编辑距离，
	 * 当word1(i-1)!=word2(j-1)dp[i][j] = dp[i-1][j-1]
	 * 否则dp:[i][j] = min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1]或
	 * @Data 2022/2/22 9:50 PM
	 */
	public int minDistance(String word1, String word2) {
		int[][] dp = new int[word1.length() + 1][word2.length() + 1];
		for(int i = 0; i < dp.length; i ++) {
			dp[i][0] = i;
		}
		for(int i = 0; i < dp[0].length; i++) {
			dp[0][i] = i;
		}
		for(int i = 1; i < dp.length; i++) {
			for(int j = 1; j < dp[0].length; j++) {
				if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
				}
			}
		}
		return dp[word1.length()][word2.length()];
	}
}
