package other.middle;

import java.math.BigInteger;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2022/1/10 21:46
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().intToRoman(1994));
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
}
