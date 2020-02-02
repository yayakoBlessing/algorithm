package alg4.sort.basesort;

import static alg4.sort.utils.Common.*;

/**
 * 插入排序：将一个记录插入到已排好序的序列中，从而得到一个新的有序序列（将序列的第一个数据看成是一个有序的子序列，然后从第二个记录逐个向该有序的子序列进行有序的插入，直至整个序列有序）。
 * 时间复杂度：O(n²) 空间复杂度：O(1)
 * 基本思想：将数组分成两部分，左边为有序序列，右边为无序序列。每次循环都按顺序从无序序列中取出一个值并将其插入左侧的有序序列中（寻找方法是依次通过与有序序列中的值进行比较，若比他小就交换，再与前一个值进行比较，由此往复。
 * 在v2中优化，当找到合适的位置后就退出该次寻找循环。
 *
 * @author cyy
 */
public class Insertion implements BaseSort {

  /**
   * 插入排序的哨兵。
   *
   * <p>在插入排序的实现中先找出最小的元素并将其置于数组的最左边，这样就能去掉内循环的判断条件j>0。
   * 使用SortCompare来评估这种方法的效果。注意：这是一种常见的规避边界测试的方法，能够省略判断条件的元素通常被称为哨兵。
   */
  public static void sort_v2(Comparable[] a) {
    int n = a.length;
    int i, min = 0;
    for (i = 0; i < n; i++) {
      if (less(a[i], a[min])) min = i;
    }
    exch(a, 0, min);
    for (i = 2; i < n; i++) {
      for (int j = i; less(a[j], a[j - 1]); j--) {
        exch(a, j, j - 1);
      }
    }
  }

  // 不需要交换的插入排序。
  // 在插入排序的实现中使较大元素右移一位只需访问一次数组(而不用使用exch())。
  private static void sort_v3(Comparable[] a) {
    int i, j, n = a.length;
    Comparable t;
    for (i = 0; i < n; i++) {
      t = a[i]; // 用t记录比较值
      // 实现较大元素依次向后移动一位
      for (j = i; j > 0 && less(t, a[j - 1]); j--) exch(a, j, j - 1);
      // 空出来的位置填补比较值
      a[j] = t;
    }
  }

  public void main(String[] args) {
    Comparable[] a = {1, 3, 9, 2, 4, 5, 7, 4, 6};
    sort(a);
    assert isSorted(a);
    print(a);
  }

  @Override
  public void sort(Comparable[] a) {
    int n = a.length;
    for (int i = 1; i < n; i++) {
      for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
        exch(a, j, j - 1);
      }
    }
  }
}
