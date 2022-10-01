package other.hard;

/**
 * @Author lupeiyao
 * @Description
 * @Date 2022/1/10 21:43
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findMedianSortedArrays1(new int[]{1}, new int[]{2,3,4,5,6}));
    }

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
        int middleIndex2 = (nums1.length + nums2.length) % 2 == 0 ? middleIndex1 - 1 : middleIndex1;
        for (int i = 0, j = 0, k = 0; i < nums1.length || j < nums2.length; ) {
            int curVal = 0;
            if (i >= nums1.length) {
                curVal = nums2[j];
                j++;
            } else if (j >= nums2.length) {
                curVal = nums1[i];
                i++;
            } else if (nums1[i] < nums2[j]) {
                curVal = nums1[i];
                i++;
            } else {
                curVal = nums2[j];
                j++;
            }
            if (k == middleIndex2) {
                result += curVal;
            }
            if (k == middleIndex1) {
                result += curVal;
                break;
            }
            k++;
        }
        return result / 2;
    }

    // 解法2：假设中位数为第k个，相当于寻找第k小的数，每次在两个数组找k/2个数去排除，
    // 然后就变成寻找第k - k/2小的数
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int middle = (n + m) / 2;
        if((nums1.length + nums2.length) % 2 == 1) {
            return minK(0, n - 1, nums1, 0, m - 1, nums2, middle + 1);
        } else {
            return (minK(0, n - 1, nums1, 0, m - 1, nums2, middle) +
                    minK(0, n - 1, nums1, 0, m - 1, nums2, middle + 1)) / 2;
        }
    }

    private double minK(int s1, int e1, int nums1[], int s2, int e2, int[] nums2, int k) {
        if(k == 1) {
            if(s1 > e1) return nums2[s2];
            else if(s2 > e2) return nums1[s1];
            else return Math.min(nums1[s1], nums2[s2]);
        }
        if(s1 > e1) return nums2[s2 + k - 1];
        if(s2 > e2) return nums1[s1 + k  -1];
        int middle = k / 2;
        if(e1 - s1  + 1 < middle || e2 - s2 + 1 < middle) {
            middle = Math.min(e1 - s1 + 1, e2 - s2 + 1);
        }
        if(nums1[s1 + middle - 1] < nums2[s2 + middle - 1]) {
            s1 += middle;
        } else {
            s2 += middle;
        }
        return minK(s1, e1, nums1, s2, e2, nums2, k - middle);
    }


}
