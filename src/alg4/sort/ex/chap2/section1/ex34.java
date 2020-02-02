package alg4.sort.ex.chap2.section1;

import alg4.sort.basesort.BaseSort;
import alg4.sort.basesort.Insertion;
import alg4.sort.basesort.Selection;
import alg4.sort.basesort.Shell;
import alg4.sort.utils.ArrayGenerate;
import alg4.sort.utils.SortCompare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 罕见情况。
 *
 * <p>编写一个测试用例，调用 sort() 方法对实际应用中可能出现困难或极端情况的数组进行排序。
 *
 * <p>比如，数组可能已经是有序的，或是逆序的，数组中的所有主键相同，数组的主键只有两种值，大小是 0 或 1的数组。
 *
 * @author cyy
 */
public class ex34 {
  private static String[] s = {"Selection", "Insertion", "Shell"};
  private static BaseSort[] baseSorts = {new Selection(), new Insertion(), new Shell()};
  private static int n = 10000; // 数组长度
  private static int T = 5; // 执行次数

  public static void main(String[] args) {
    sortedArrayTest();
    reversedArrayTest();
    sameKeyTest();
    twoKeyTest();
    oneElementTest();
    zeroElementTest();
  }

  private static void showResult(double[] total) {
    for (int i = 0; i < baseSorts.length; i++) {
      System.out.println(s[i] + "：" + total[i]);
    }
  }

  /** 顺序数组 */
  private static void sortedArrayTest() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.random(n);
      Arrays.sort(b);
      a.add(b);
    }
    System.out.println();
    System.out.println("顺序数组：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 逆序数组 */
  private static void reversedArrayTest() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b, c;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.random(n);
      c = new Comparable[n];
      Arrays.sort(b);
      for (int j = 0; j < n; j++) {
        c[j] = b[n - j - 1];
      }
      a.add(c);
    }
    System.out.println();
    System.out.println("逆序数组：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 所有主键相同 */
  private static void sameKeyTest() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    Comparable t;
    for (int i = 0; i < T; i++) {
      b = new Comparable[n];
      t = Math.random() * 100;
      for (int j = 0; j < n; j++) b[j] = t;
      a.add(b);
    }
    System.out.println();
    System.out.println("所有主键相同：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 主键只有两种值 */
  private static void twoKeyTest() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = new Comparable[n];
      for (int j = 0; j < n; j++) b[j] = new Random().nextInt(2);
      a.add(b);
    }
    System.out.println();
    System.out.println("主键只有两种值：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 大小是 1 的数组 */
  private static void oneElementTest() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = new Comparable[1];
      b[0] = Math.random() * 100;
      a.add(b);
    }
    System.out.println();
    System.out.println("大小是 1 的数组：");
    showResult(SortCompare.compare(baseSorts, a, 1));
  }

  /** 大小是 0 的数组 */
  private static void zeroElementTest() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b = new Comparable[0];
    for (int i = 0; i < T; i++) {
      a.add(b);
    }
    System.out.println();
    System.out.println("大小是 0 的数组");
    showResult(SortCompare.compare(baseSorts, a, 0));
  }
}
