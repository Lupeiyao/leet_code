package middle;

/**
 * @Author Lunus
 * @Description TODO
 * @Date 2021/11/7 20:29
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxCount(3, 3, new int[][]{{2, 2}, {3, 3}}));
    }

    /*
    598. 范围求和 II
    https://leetcode-cn.com/problems/range-addition-ii/
     */
    public int maxCount(int m, int n, int[][] ops) {
        // 参数检查
        if (m <= 0 || n <= 0 || ops == null) {
            return 0;
        }
        if(ops.length <= 0) {
            return m * n;
        }
        // 最终结果由所有操作中a和b的最小值决定
        int minRow = m;
        int minColumn = n;
        for(int i = 0; i < ops.length; i++) {
            if(ops[i].length < 2) {
                return 0;
            }
            if(ops[i][0] == 0 || ops[i][1] == 0) {
                continue;
            }
            if(ops[i][0] < minRow) {
                minRow = ops[i][0];
            }
            if(ops[i][1] < minColumn) {
                minColumn = ops[i][1];
            }
        }
        return minRow * minColumn;
    }
}
