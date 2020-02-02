package alg4.sort.ex.chap2.section1;

import alg4.sort.basesort.BaseSort;
import alg4.sort.basesort.Insertion;
import alg4.sort.basesort.Selection;
import alg4.sort.basesort.Shell;
import alg4.sort.utils.ArrayGenerate;
import alg4.sort.utils.SortCompare;

import java.util.ArrayList;
import java.util.List;

/**
 * 部分有序。编写一个测试用例，生成部分有序数组，包括：
 *
 * <p>95% 有序，其余部分为随机值。 所有元素和它们的正确位置的距离都不超过 10。 5% 的元素随机分布在整个数组中，剩下的数据都是有序的。
 * 评估并验证这些输入数据对本节讨论的算法的性能的影响。
 *
 * @author cyy
 */
public class ex37 {
  private static String[] s = {"Selection", "Insertion", "Shell"};
  private static BaseSort[] baseSorts = {new Selection(), new Insertion(), new Shell()};
  private static int n = 10000; // 数组长度
  private static int T = 5; // 执行次数

  public static void main(String[] args) {
    leftInOder95per();
    rightInOder95per();
    deviateFromOrder();
    outOfOrder5per();
  }

  /** 显示结果 */
  private static void showResult(double[] total) {
    for (int i = 0; i < baseSorts.length; i++) {
      System.out.println(s[i] + "：" + total[i]);
    }
  }

  /** 左部 95% 有序，其余部分为随机值 */
  private static void leftInOder95per() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.leftInOder95per(n);
      a.add(b);
    }
    System.out.println();
    System.out.println("左部 95% 有序，其余部分为随机值：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 右部 95% 有序，其余部分为随机值 */
  private static void rightInOder95per() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.rightInOder95per(n);
      a.add(b);
    }
    System.out.println();
    System.out.println("右部 95% 有序，其余部分为随机值：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 所有元素和它们的正确位置的距离都不超过 10 */
  private static void deviateFromOrder() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.deviateFromOrder(n);
      a.add(b);
    }
    System.out.println();
    System.out.println("所有元素和它们的正确位置的距离都不超过 10：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 5% 的元素随机分布在整个数组中，剩下的数据都是有序的 */
  private static void outOfOrder5per() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.outOfOrder5per(n);
      a.add(b);
    }
    System.out.println();
    System.out.println("5% 的元素随机分布在整个数组中，剩下的数据都是有序的：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }
}
