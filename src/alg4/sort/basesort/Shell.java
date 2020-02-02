package alg4.sort.basesort;

import static alg4.sort.utils.Common.*;

/**
 * 希尔排序
 *
 * @author cyy
 */
public class Shell implements BaseSort {

  public void main(String[] args) {
    Integer[] a = {0, 55, 23, 1, 8, 6, 22, 15, 12, 20, 5, 9, 8, 17, 4};
    sort(a);
    assert isSorted(a);
    print(a);
  }

  @Override
  public void sort(Comparable[] a) {
    int n = a.length;
    int h = 1;
    // 递增序列（1,4,13,40...）
    while (h < n / 3) {
      h = h * 3 + 1;
    }
    while (h >= 1) {
      for (int i = h; i < n; i++) {
        // 对子数组进行插入排序
        for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
          exch(a, j, j - h);
        }
      }
      h = h / 3;
    }
  }
}
