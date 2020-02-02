package alg4.sort.ex.chap2.section1;

import alg4.sort.basesort.BaseSort;
import alg4.sort.basesort.Insertion;
import alg4.sort.utils.SortCompare;

import static alg4.sort.utils.Common.exch;
import static alg4.sort.utils.Common.less;

/**
 * 不需要交换的插入排序。
 *
 * <p>在插入排序的实现中使较大元素右移一位只需要访问一次数组（而不用使用 exch()）。 使用 SortCompare 来评估这种做法的效果。
 *
 * @author cyy
 */
public class ex25 implements BaseSort {

  public static void main(String[] args) {
    String[] s = {"原版用时", "改版用时"};
    BaseSort[] baseSorts = {new Insertion(), new ex25()};

    SortCompare.timeRandomInput(s, baseSorts);
  }

  @Override
  public void sort(Comparable[] a) {
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
}
