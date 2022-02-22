package other.middle;

import java.math.BigInteger;
import java.util.*;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2022/1/10 21:46
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().simplifyPath("/a//b////c/d//././/.."));
    }

    /*
     * @Author lupeiyao
     * @Description 给定全是[0-9]的字符串，随便分割，判断是否可以组成1,2,3,5,8这样a[i]=a[i-2]+a[i-1]的数组
     * @Link https://leetcode-cn.com/problems/additive-number/
     * @Solution 数组由a[0]和a[1]决定，遍历前两个就行了
     * @Data 2022/1/14 15:27
     */
    public boolean isAdditiveNumber(String num) {
        for(int i = 1; i <= num.length() / 2; i++) {
            if(num.charAt(0) == '0' && i > 1) {
                break;
            }
            String a = num.substring(0, i);
            for(int j = i + 1; j < num.length(); j++) {
                if(num.charAt(i) == '0' && j - i > 1) {
                    break;
                }
                String b = num.substring(i, j);
                if(helper(a, b, num.substring(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean helper(String a, String b, String num) {
        if(num.equals("")) {
            return true;
        }
        String val = add(a, b);
        for(int i = 1; i <= num.length(); i++) {
            if(num.charAt(0) == '0' && i > 1) {
                break;
            }
            if(val.equals(num.substring(0, i))) {
                return helper(b, val, num.substring(i, num.length()));
            }
        }
        return false;
    }

    private String add(String a, String b){
        StringBuilder sb = new StringBuilder();
        int one = 0;
        for(int i=a.length()-1,j=b.length()-1;i>=0||j>=0;){
            int curA = 0, curB = 0;
            if(i >= 0)
                curA = a.charAt(i--) - '0';
            if(j >= 0)
                curB = b.charAt(j--) - '0';
            int cur = curA + curB + one;
            one = cur >= 10 ? 1 : 0;
            sb.append((char)('0' + cur%10));
        }
        if(one == 1)
            sb.append('1');
        return sb.reverse().toString();
    }

    /*
     * @Author lupeiyao
     * @Description 给定数组nums，求Max(min(nums[i], nums[j]) * (j - i))，i < j
     * @Link https://leetcode-cn.com/problems/container-with-most-water/
     * @Solution 双指针，每次移动小的即可。假设从[i,j]中找最大的，那么一定是(i,j)或者其他，
     * 如果nums[i]<nums[j]，那么(i,j-1),x(i-2)...一定小于(i,j)，所以移动i就行
     * @Data 2022/1/17 21:10
     */
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        for(int i = 0, j = height.length - 1; i < j;) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if(height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

    /*
     * @Author lupeiyao
     * @Description 数字转罗马数字表示
     * @Link https://leetcode-cn.com/problems/integer-to-roman/comments/
     * @Solution 每一位的数字硬编码即可
     * @Data 2022/1/17 21:09
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        String[][] x = new String[][] {{"I", "V", "X"}, {"X", "L", "C"}, {"C", "D", "M"}, {"M"}};
        for(int i = 0; num != 0; i++, num /= 10) {
            int temp = num % 10;
            if(temp <= 3) {
                for(int j = 0; j < temp; j++) {
                    sb.append(x[i][0]);
                }
            } else if(temp == 4) {
                sb.append(x[i][1] + x[i][0]);
            } else if(temp == 5) {
                sb.append(x[i][1]);
            } else if(temp == 9) {
                sb.append(x[i][2] + x[i][0]);
            } else {
                for(int j = 0; j < temp - 5; j++) {
                    sb.append(x[i][0]);
                }
                sb.append(x[i][1]);
            }
        }
        return sb.reverse().toString();
    }

    /*
     * @Author lupeiyao
     * @Description 给定数组，求和为0的所有不重复3元组
     * @Link https://leetcode-cn.com/problems/3sum/
     * @Solution 排序，对于大于等于0的第i个元素，在[0,i-1]范围找和为-num[i]的两个数字
     * 不重复过滤，每次判断当前元素不等于上个元素即可，注意要从范围大的开始找，范围大的找完，
     * 下一个如果和当前元素相等才可以跳过，如果从小的开始会有问题
     * @Data 2022/1/18 21:46
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 3) return result;
        Arrays.sort(nums);
        for(int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] < 0 || (i < nums.length - 1 && nums[i] == nums[i + 1])) {
                continue;
            }
            int left = 0;
            int right = i - 1;
            while(left < right) {
                if(nums[left] + nums[right] == -nums[i]) {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    list.add(nums[i]);
                    result.add(list);
                    while(left + 1 < nums.length && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    while(right - 1 > 0 && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if(nums[left] + nums[right] > -nums[i]) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    /*
     * @Author lupeiyao
     * @Description 给定数组，找到其中三个加起来最接近target的和
     * @Link https://leetcode-cn.com/problems/3sum-closest/
     * @Solution 排序，遍历nums数组，假设在一定包含nums[i]的情况下，找另外两个。
     * 双指针遍历nums[i+1,end]，如果三数之和大于target，则end--，否则start++
     * @Data 2022/1/19 19:45
     */
    public int threeSumClosest(int[] nums, int target) {
        Integer res = null;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right) {
                int curSum = nums[left] + nums[right] + nums[i];
                if(res == null || Math.abs(res - target) > Math.abs(curSum - target)) {
                    res = curSum;
                }
                if(curSum == target) {
                    return target;
                } else if(curSum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    /*
     * @Author lynnliu
     * @Description 按照顺时针打印二位数组
     * @Link https://leetcode-cn.com/problems/spiral-matrix/
     * @Solution
     * @Data 2022/2/17 8:41 PM
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int curRow = 0;
        int curCol = 0;
        int leftLimit = -1;
        int rightLimit = matrix[0].length;
        int lowLimit = -1;
        int highLimit = matrix.length;
        List<Integer> result = new LinkedList<>();
        while (curCol > leftLimit && curCol < rightLimit && curRow > lowLimit && curRow < highLimit) {
            for(int i = leftLimit + 1; i < rightLimit && lowLimit + 1 < highLimit; i++) {
                result.add(matrix[lowLimit + 1][i]);
            }
            lowLimit += 1;
            for(int i = lowLimit + 1; i < highLimit && rightLimit - 1 > leftLimit; i++) {
                result.add(matrix[i][rightLimit - 1]);
            }
            rightLimit -= 1;
            for(int i = rightLimit - 1; i > leftLimit && highLimit - 1 > lowLimit; i--) {
                result.add(matrix[highLimit - 1][i]);
            }
            highLimit -= 1;
            for(int i = highLimit - 1; i > lowLimit && leftLimit + 1 < rightLimit; i--) {
                result.add(matrix[i][leftLimit + 1]);
            }
            leftLimit += 1;
            curRow = lowLimit + 1;
            curCol = leftLimit + 1;
        }
        return result;
    }

    /*
     * @Author lynnliu
     * @Description 从nums[i]开始，最大可以跳到i+nums[i]下标处，判断从0开始是否能到最后
     * @Link https://leetcode-cn.com/problems/jump-game/
     * @Solution 从0开始遍历，如果i+nums[i]>=nums.length-1就可以
     * @Data 2022/2/17 8:43 PM
     */
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length < 2) {
            return true;
        }
        int max = nums[0];
        for(int i = 1; i <= max; i++) {
            if(max >= nums.length - 1) {
                return true;
            } else {
                max = Math.max(max, i + nums[i]);
            }
        }
        return false;
    }

    /*
     * @Author lynnliu
     * @Description 给定n个范围，合并有重复区域的范围
     * @Link https://leetcode-cn.com/problems/merge-intervals/
     * @Solution 按照每个范围的start排序，然后顺序向前合并
     * @Data 2022/2/17 8:59 PM
     */
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length < 2) {
            return intervals;
        }
        Comparator comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        };
        Arrays.sort(intervals, comparator);
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for(int i = 1; i < intervals.length; i++) {
            int[] pre = result.get(result.size() - 1);
            if(intervals[i][0] > pre[1]) {
                result.add(intervals[i]);
            } if(intervals[i][0] <= pre[1] && intervals[i][1] > pre[1]) {
                pre[1] = intervals[i][1];
            }
        }
        return result.toArray(new int[result.size()][2]);
    }

    /*
     * @Author lynnliu
     * @Description 给定linux的路径，请简化。（'//'，'.'和'..'需要进行简化)
     * @Link https://leetcode-cn.com/problems/simplify-path/submissions/
     * @Solution 用'/'进行split，""，"."相当于无效，".."把上一个有效的内容无效
     * @Data 2022/2/22 9:16 PM
     */
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        String[] strs = path.split("/");
        int[] valid = new int[strs.length];
        for(int i = 0; i < strs.length; i++) {
            if(strs[i].equals(".")) {
                valid[i] = 0;
            } else if(strs[i].equals("")) {
                valid[i] = 0;
            } else if(strs[i].equals("..")) {
                valid[i] = 0;
                for(int j = i - 1; j >= 0; j--) {
                    if(valid[j] == 1) {
                        valid[j] = 0;
                        break;
                    }
                }
            } else {
                valid[i] = 1;
            }
        }
        for(int i = 0; i < strs.length; i++) {
            if(valid[i] == 1) {
                sb.append("/");
                sb.append(strs[i]);
            }
        }
        if(sb.length() == 0) {
            sb.append("/");
        }
        return sb.toString();
    }
}
