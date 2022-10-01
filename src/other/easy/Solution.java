package other.easy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author Lunus
 * @Description TODO
 * @Date 2021/11/7 20:29
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
//        System.out.println(new Solution().isValid("()[]{}"));
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


    /*
     * @Author Lunus
     * @Description 给定target，求数组中arr[i] + arr[j] = target的唯一i、j
     * @Link https://leetcode.cn/problems/two-sum/?favorite=2cktkvj
     * @Solution 使用map即可，注意只需遍历一次（可去重），碰到第二个是，第一个数一定已经存在了
     * Date 2022-10-01
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }
}
