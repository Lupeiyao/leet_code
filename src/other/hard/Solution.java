package other.hard;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2022/1/10 21:43
 * @Version 1.0
 */
public class Solution {

    /*
     * @Author lupeiyao
     * @Description 找到两个排序数组的中位数
     * @Link https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
     * @Solution 双指针遍历，根据两个数组大小判断中位数是是第k个
     * @Data 2022/1/14 15:25
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;
        int middleIndex1 = (nums1.length + nums2.length) / 2;
        int middleIndex2 = (nums1.length + nums2.length) %  2 == 0 ? middleIndex1 - 1 : middleIndex1;
        for(int i = 0, j = 0, k = 0; i < nums1.length || j < nums2.length;) {
            int curVal = 0;
            if(i >= nums1.length) {
                curVal = nums2[j];
                j++;
            } else if(j >= nums2.length) {
                curVal = nums1[i];
                i++;
            } else if(nums1[i] < nums2[j]) {
                curVal = nums1[i];
                i++;
            } else {
                curVal = nums2[j];
                j++;
            }
            if(k == middleIndex2) {
                result += curVal;
            }
            if(k == middleIndex1) {
                result += curVal;
                break;
            }
            k++;
        }
        return result / 2;
    }
}
