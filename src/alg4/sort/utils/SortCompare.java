package alg4.sort.utils;

import alg4.sort.basesort.BaseSort;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法比较工具类
 *
 * @author cyy
 */
public class SortCompare {
  private static final int N = 1000;
  private static final int T = 5;

  /**
   * 计时
   *
   * @param baseSort 需要进行比较的各算法类型
   * @param a 排序对象数组
   * @return 排序所用时间
   */
  public static double time(BaseSort baseSort, Comparable[] a) {
    Stopwatch timer = new Stopwatch();
    baseSort.sort(a);
    return timer.elapsedTime();
  }

  /**
   * 算法比较
   *
   * @param baseSort 需要进行比较的各算法类型
   * @return 排序所用时间
   */
  public static double timeRandomInput(BaseSort baseSort, int n) {
    double total = 0;
    Comparable[] a = ArrayGenerate.random(n);
    for (int i = 0; i < T; i++) {
      for (int j = 0; j < n; j++) {
        a[j] = Math.random() * 100;
      }
      total += time(baseSort, a);
    }
    return total;
  }

  /**
   * 算法比较（基于不同算法对同一数组的排序的用时比较）
   *
   * @param s 名称
   * @param baseSorts 算法类
   */
  public static void timeRandomInput(String[] s, BaseSort[] baseSorts) {
    List<Comparable[]> a = new ArrayList<>(T);
    Comparable[] b;
    double[] total, pre = new double[s.length];
    int n;

    //    // 专为 2.1.27 使用，生成从2^7~2^16长度的测试数组
    //    for (int i = 7; i <= 16; i++) {
    //      n = (int) Math.pow(2, i);

    //    // 专为 2.1.31 使用，生成长度成倍増长的测试数组
    //    for (int i = 0, n = 1000; i <= 6; i++, n *= 2) {

    //     生成从100~100000数量级长度的测试数组
    for (int i = 2; i <= 5; i++) {
      n = (int) Math.pow(10, i);

      System.out.println();
      System.out.println("ArraySize = " + n);
      // 每个数量级的排序都要分别进行T次，所以需要初始化T个相同长度的不同数组
      for (int j = 0; j < T; j++) {
        b = ArrayGenerate.random(n);
        a.add(b);
      }
      total = SortCompare.compare(baseSorts, a, n);
      for (int k = 0; k < baseSorts.length; k++) {
        System.out.println(s[k] + "：" + total[k] + "\tnow/pre = " + total[k] / pre[k]);
        pre[k] = total[k];
      }
      a.clear();
    }
  }

  /**
   * 综合比较
   *
   * @param baseSorts 需要进行比较的各算法类型
   * @param a 排序对象数组
   * @param n 数组长度
   * @return 用时数组
   */
  public static double[] compare(BaseSort[] baseSorts, List<Comparable[]> a, int n) {
    Comparable[] b;
    int j;
    double[] total = new double[baseSorts.length];
    for (int i = 0; i < T; i++) {
      b = new Comparable[n];
      j = 0;
      for (BaseSort baseSort : baseSorts) {
        System.arraycopy(a.get(i), 0, b, 0, n);
        total[j++] = SortCompare.time(baseSort, b);
      }
    }
    return total;
  }
}
