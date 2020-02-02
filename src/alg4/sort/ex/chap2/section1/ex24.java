package alg4.sort.ex.chap2.section1;

import alg4.sort.basesort.BaseSort;
import alg4.sort.basesort.Insertion;
import alg4.sort.utils.SortCompare;

import static alg4.sort.utils.Common.exch;
import static alg4.sort.utils.Common.less;

/**
 * 插入排序的哨兵。 在插入排序的实现中先找出最小的元素并将其置于数组的最左边，这样就能去掉内循环的判断条件 j>0。 使用 SortCompare 来评估这种做法的效果。
 * 注意：这是一种常见的规避边界测试的方法，能够省略判断条件的元素通常称为哨兵。
 *
 * @author cyy
 */
public class ex24 implements BaseSort {

  public static void main(String[] args) {
    String[] s = {"原版用时", "改版用时"};
    BaseSort[] baseSorts = {new Insertion(), new ex24()};

    SortCompare.timeRandomInput(s, baseSorts);
  }

  @Override
  public void sort(Comparable[] a) {
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
}
