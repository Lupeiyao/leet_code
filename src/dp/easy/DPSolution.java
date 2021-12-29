package dp.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2021/12/23 20:52
 * @Version 1.0
 */
public class DPSolution {

    public static void main(String[] args) {
        System.out.println(new DPSolution().divisorGame(4));
    }

    /*
     * @Author lupeiyao
     * @Description 计算[0,n]每个数字的二进制的1的个数
     * @Link https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode-solution-0t1i/
     * @Solution x & (x-1)可以消除二进制最低的1，连消N次为0
     * @Data 2021/12/23 21:09
     */
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            int temp = i;
            while(temp != 0) {
                temp &= (temp - 1);
                result[i]++;
            }
        }
        return result;
    }


    /*
     * @Solution x为偶数，f(x) = f(x / 2)，否则f(x) = f(x - 1) + 1
     */
    public int[] countBits1(int n) {
        int[] result = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            if(i % 2 == 0) {
                result[i] = result[i >> 1];
            } else {
                result[i] = result[i - 1] + 1;
            }
        }
        return result;
    }


    /*
     * @Author lupeiyao
     * @Description 给[0,n)个用户，和单项关系array(i->j)，求0->n-1走k步一共多少路径
     * @Link https://leetcode-cn.com/problems/chuan-di-xin-xi/
     * @Solution dp[i][k]表示从0到i走k+1步的路径，dp[i][k] = Sum_j dp[j][k-1]，j为任意可到i的用户
     * @Data 2021/12/23 21:26
     */
    public int numWays(int n, int[][] relation, int k) {
        int dp[][] = new int[n][k];
        for(int i = 0; i < k; i++) {
            for(int[] rel : relation) {
                if(i == 0) {
                    if (rel[0] == 0) {
                        dp[rel[1]][0] = 1;
                    }
                } else {
                    dp[rel[1]][i] += dp[rel[0]][i-1];
                }
            }
        }
        return dp[n-1][k-1];
    }

    /*
     * @Author lupeiyao
     * @Description int[] cost 表示爬当前台阶的消耗，消耗后可以爬一节或者两节台阶，求爬完最小消耗（从0或者1开始都可以）
     * @Link https://leetcode-cn.com/problems/min-cost-climbing-stairs/
     * @Solution 简单dp[i] = min(dp[i + 1], dp[i + 2]) + cost[i]
     * @Data 2021/12/29 21:24
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[cost.length - 1] = cost[cost.length - 1];
        dp[cost.length - 2] = cost[cost.length - 2];
        for(int i = cost.length - 3; i >= 0; i--) {
            dp[i] = Math.min(dp[i + 1], dp[i + 2]) + cost[i];
        }
        return Math.min(dp[0], dp[1]);
    }


    /*
     * @Author lupeiyao
     * @Description
     * @Link https://leetcode-cn.com/problems/divisor-game/
     * @Solution
     * @Data 2021/12/29 21:27
     */
    public boolean divisorGame(int n) {
        if(n == 1) return false;
        if(n == 2) return true;
        if(n == 3) return false;
        boolean[] result = new boolean[n];
        result[0] = false;
        result[1] = true;
        result[2] = false;
        for(int i = 3; i < result.length; i++) {
            for(int j = 0; j < (i + 1) / 2; j++) {
                if((i + 1) % (j + 1) == 0 && !result[i - j - 1]) {
                    result[i] = true;
                    break;
                }
            }
        }
        return result[n - 1];
    }

    /*
     * @Author lupeiyao
     * @Description 最大连续子数组的和
     * @Link https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
     * @Solution dp[i]表示以i结尾的子数组最大和
     * @Data 2021/12/29 21:29
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int pre = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            if(pre > 0) {
                cur += pre;
            }
            max = Math.max(max, cur);
            pre = cur;
        }
        return max;
    }


    /*
     * @Author lupeiyao
     * @Description 给定一组价格nums,找最大的num[j] - num[i]，j > i
     * @Link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     * @Solution dp[i]表示已num[0-i]中最小的值
     * @Data 2021/12/29 21:30
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        int pre = prices[0];
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > pre) {
                result = Math.max(prices[i] - pre, result);
            } else {
                pre = prices[i];
            }
        }
        return result;
    }

    /*
     * @Author lupeiyao
     * @Description 给定插件个数，每分钟可选择下载一个或者宽带加倍，求最短时间
     * @Link https://leetcode-cn.com/problems/Ju9Xwi/
     * @Solution 当前分钟下载，则变为dp[i-1]，否则相当于插件数量减半 变为dp[(i + 1) / 2]
     * @Data 2021/12/29 21:32
     */
    public int leastMinutes(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + 1, dp[(i + 1) / 2] + 1);
        }
        return dp[n];
    }


    /*
     * @Author lupeiyao
     * @Description
     * @Link https://leetcode-cn.com/problems/get-maximum-in-generated-array/
     * @Solution
     * @Data 2021/12/29 21:35
     */
    public int getMaximumGenerated(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for(int i = 2; i < nums.length; i++) {
            if(i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2] + nums[i / 2 + 1];
            }
        }
        int result = 0;
        for(int num : nums) {
            result = Math.max(result, num);
        }
        return result;
    }

    /*
     * @Author lupeiyao
     * @Description 判断s是否是t的子序列
     * @Link https://leetcode-cn.com/problems/is-subsequence/
     * @Solution 双指针
     * @Data 2021/12/29 21:35
     */
    public boolean isSubsequence(String s, String t) {
        char[] mainStr = t.toCharArray();
        char[] subStr = s.toCharArray();
        int i = 0;
        int j = 0;
        while(i < mainStr.length && j < subStr.length) {
            if(mainStr[i] == subStr[j]) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return j == subStr.length;
    }

    /*
     * @Author lupeiyao
     * @Description 给定正整数数组，求和最大的子序列（不能取连续的两个）
     * @Link https://leetcode-cn.com/problems/the-masseuse-lcci/
     * @Solution 如果取当前节点，则最大为dp[i - 2]，否则为 dp[i - 1]
     * @Data 2021/12/29 21:36
     */
    public int massage(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        int n2 = nums[nums.length - 1];
        int n1 = Math.max(nums[nums.length - 1], nums[nums.length - 2]);
        int max = 0;
        for(int i = nums.length - 3; i >= 0; i--) {
            max = Math.max(nums[i] + n2, n1);
            n2 = n1;
            n1 = max;
        }
        return max;
    }

}
