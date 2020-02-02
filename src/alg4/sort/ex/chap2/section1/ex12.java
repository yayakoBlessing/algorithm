package alg4.sort.ex.chap2.section1;

import alg4.sort.utils.ArrayGenerate;

import static alg4.sort.utils.Common.exch;
import static alg4.sort.utils.Common.less;

/**
 * 令希尔排序打印出递增序列的每个元素所带来的比较次数和数组大小的比值。
 *
 * <p>编写一个测试用例对随机 Double 数组进行希尔排序，验证该值是一个小常数，数组大小按照 10 的幂次递增，不小于 100。
 *
 * @author cyy
 */
public class ex12 {

  private static void sort(Comparable[] a) {
    int N = a.length;
    System.out.println();
    System.out.println("ArraySize = " + N);
    int h = 1;
    while (h < N / 3) h = 3 * h + 1;
    int i, j, ctime;
    while (h >= 1) {
      ctime = 0;
      for (i = h; i < N; i++) {
        for (j = i; j >= h && less(a[j], a[j - h]); j -= h, ctime++) {
          exch(a, j - h, j);
        }
      }
      System.out.println(
          "CompareTimeOf h ="
              + h
              + "："
              + ctime
              + "\t\tCompareTime/ArraySize = "
              + ((float) ctime / N));
      h /= 3;
    }
  }

  public static void main(String[] args) {
    for (int i = 2; i < 6; i++) {
      sort(ArrayGenerate.randomDouble((int) Math.pow(10, i)));
    }
  }
}
