package leetcode;

import java.util.Arrays;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * <p>请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * <p>你可以假设 nums1 和 nums2 不会同时为空。
 *
 * <p>示例 1:
 *
 * <p>nums1 = [1, 3] nums2 = [2]
 *
 * <p>则中位数是 2.0 示例 2:
 *
 * <p>nums1 = [1, 2] nums2 = [3, 4]
 *
 * <p>则中位数是 (2 + 3)/2 = 2.5
 *
 * <p>@Author：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class title004 {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n1 = nums1.length;
    int n2 = nums2.length;

    int[] nums = Arrays.copyOf(nums1, n1 + n2);

    for (int i = 0; i < n2; i++) {
      nums[i + n1] = nums2[i];
    }
    Arrays.sort(nums);
    int left = 0, right = n1 + n2 - 1, mid = (n1 + n2) / 2;
    return 0;
  }
}
