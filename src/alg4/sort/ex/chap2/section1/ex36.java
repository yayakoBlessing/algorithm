package alg4.sort.ex.chap2.section1;

import alg4.sort.basesort.BaseSort;
import alg4.sort.basesort.Insertion;
import alg4.sort.basesort.Selection;
import alg4.sort.basesort.Shell;
import alg4.sort.utils.ArrayGenerate;
import alg4.sort.utils.Common;
import alg4.sort.utils.SortCompare;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 不均匀的数据。编写一个测试用例，生成不均匀的测试数据，包括：
 *
 * <p>一半数据是 0，一半数据是 1
 *
 * <p>一半数据是 0，1/4 是 1，1/4 是 2，以此类推
 *
 * <p>一半数据是 0，一半是随机 int 值。
 *
 * <p>评估并验证这些输入数据对本节讨论的算法的性能的影响。
 *
 * @author cyy
 */
public class ex36 {
  private static String[] s = {"Selection", "Insertion", "Shell"};
  private static BaseSort[] baseSorts = {new Selection(), new Insertion(), new Shell()};
  private static int n = 100000; // 数组长度
  private static int T = 5; // 执行次数

  public static void main(String[] args) {
    half0half1();
    half0qtr1qtr2();
    half0halfRandom();
  }

  /** 输出结果 */
  private static void showResult(double[] total) {
    for (int i = 0; i < baseSorts.length; i++) {
      System.out.println(s[i] + "：" + total[i]);
    }
  }

  /** 一半数据是 0，一半数据是 1 */
  private static void half0half1() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    b = ArrayGenerate.half0half1(n);
    for (int i = 0; i < T; i++) {
      b = disturb(b);
      a.add(b);
    }
    System.out.println();
    System.out.println("一半数据是 0，一半数据是 1：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 一半数据是 0，1/4 是 1，1/4 是 2，以此类推 */
  private static void half0qtr1qtr2() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b = ArrayGenerate.half0qtr1qtr2(n);
    for (int i = 0; i < T; i++) {
      b = disturb(b);
      a.add(b);
    }
    System.out.println();
    System.out.println("一半数据是 0，1/4 是 1，1/4 是 2，以此类推：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 一半数据是 0，一半是随机 int 值 */
  private static void half0halfRandom() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.half0halfRandom(n);
      b = disturb(b);
      a.add(b);
    }
    System.out.println();
    System.out.println("一半数据是 0，一半是随机 int 值：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 数组打乱 */
  private static Comparable[] disturb(Comparable[] a) {
    int n = a.length;
    for (int i = 0; i < n; i++) Common.exch(a, i, new Random().nextInt(n));
    return a;
  }
}
