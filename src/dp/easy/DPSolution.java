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
}
