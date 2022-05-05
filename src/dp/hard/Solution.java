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
}
