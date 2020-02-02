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
 * 不均匀的概率分布。编写一个测试用例，使用非均匀分布的概率来生成随机排列的数据，包括：
 *
 * <p>高斯分布 泊松分布 几何分布 离散分布（一种特殊情况见练习 2.1.28）。 评估并验证这些输入数据对本节讨论的算法的影响。
 *
 * @author cyy
 */
public class ex35 {
  private static String[] s = {"Selection", "Insertion", "Shell"};
  private static BaseSort[] baseSorts = {new Selection(), new Insertion(), new Shell()};
  private static int n = 10000; // 数组长度
  private static int T = 5; // 执行次数

  public static void main(String[] args) {
    gauss();
    poisson();
    geometry();
    dispersed();
    specialOfDispersed();
  }

  /** 显示结果 */
  private static void showResult(double[] total) {
    for (int i = 0; i < baseSorts.length; i++) {
      System.out.println(s[i] + "：" + total[i]);
    }
  }

  /** 高斯分布 */
  private static void gauss() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.gauss(n);
      a.add(b);
    }
    System.out.println();
    System.out.println("高斯分布：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 泊松分布 */
  private static void poisson() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.poisson(n);
      a.add(b);
    }
    System.out.println();
    System.out.println("泊松分布：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 几何分布 */
  private static void geometry() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.geometry(n);
      a.add(b);
    }
    System.out.println();
    System.out.println("几何分布：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 离散分布 */
  private static void dispersed() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.dispersed(n);
      a.add(b);
    }
    System.out.println();
    System.out.println("离散分布：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }

  /** 离散分布的特殊情况（即主键仅可能取两种值） */
  private static void specialOfDispersed() {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    for (int i = 0; i < T; i++) {
      b = ArrayGenerate.specialOfDispersed(n);
      a.add(b);
    }
    System.out.println();
    System.out.println("离散分布的特殊情况（即主键仅可能取两种值）：");
    showResult(SortCompare.compare(baseSorts, a, n));
  }
}
