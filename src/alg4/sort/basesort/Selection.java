package alg4.sort.basesort;

import static alg4.sort.utils.Common.*;

/** 选择排序：从头至尾扫描序列，找出其中最小的一个元素，并和第一个元素交换，接着再从剩下的元素中继续这种选择和交换方式，最终得到一个有序序列。 时间复杂度：O(n²) 空间复杂度：O(1) */
public class Selection implements BaseSort {
  public void main(String[] args) {
    Comparable[] a = {1, 3, 9, 2, 4, 5, 7, 4, 6};
    sort(a);
    assert isSorted(a);
    print(a);
  }

  @Override
  public void sort(Comparable[] a) {
    int n = a.length;
    int min;
    for (int i = 0; i < n; i++) {
      min = i;
      for (int j = i + 1; j < n; j++) {
        if (less(a[j], a[min])) {
          min = j;
        }
      }
      exch(a, i, min);
    }
  }
}
