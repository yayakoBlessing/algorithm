package alg4.sort.ex.chap2.section1;

import alg4.sort.basesort.BaseSort;
import alg4.sort.basesort.Shell;
import alg4.sort.utils.Common;
import alg4.sort.utils.SortCompare;

/**
 * 将希尔排序中实时计算递增序列改为预先计算并存储在一个数组中。
 *
 * @author cyy
 */
public class ex11 implements BaseSort {

  public static void main(String[] args) {
    String[] s = {"原版用时", "改版用时"};
    BaseSort[] baseSorts = {new Shell(), new ex11()};

    SortCompare.timeRandomInput(s, baseSorts);
  }

  @Override
  public void sort(Comparable[] a) {
    int n = a.length;
    int i, j, h, k;
    int[] tmp = new int[(int) (Math.log(2 * (n / 3) + 1) / Math.log(3)) + 1];
    for (i = 0, h = 1; i < tmp.length; i++, h = 3 * h + 1) tmp[i] = h;
    for (k = 0; k < tmp.length; k++) {
      for (i = h = tmp[k]; i < n; i++)
        for (j = i; j >= h && Common.less(a[j], a[j - h]); j -= h) Common.exch(a, j - h, j);
    }
  }
}
