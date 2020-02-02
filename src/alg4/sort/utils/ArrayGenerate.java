package alg4.sort.utils;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Random;

/**
 * 数组构造类
 *
 * @author cyy
 */
public class ArrayGenerate {

  /** 构造随机数组 */
  public static Comparable[] random(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) a[i] = Math.random() * 100;
    return a;
  }

  public static Double[] randomDouble(int n) {
    Double[] a = new Double[n];
    for (int i = 0; i < n; i++) {
      a[i] = Math.random() * 100;
    }
    return a;
  }

  /** 主键仅可能取两种值的数组 */
  public static Comparable[] towKey(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) {
      a[i] = new Random().nextInt(2);
    }
    return a;
  }

  /** 一半 0，一半 1 */
  public static Comparable[] half0half1(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n / 2; i++) {
      a[i] = 0;
      a[n - i - 1] = 1;
    }
    return a;
  }

  /** 一半是 0，1/4 是 1，1/4 是 2 */
  public static Comparable[] half0qtr1qtr2(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n / 4; i++) {
      a[i] = a[n / 4 + i] = 0;
      a[n / 2 + i] = 1;
      a[n - i - 1] = 2;
    }
    return a;
  }

  /** 一半是 0，一半是随机 int 值 */
  public static Comparable[] half0halfRandom(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n / 2; i++) {
      a[i] = 0;
      a[n - i - 1] = new Random().nextInt(100);
    }
    return a;
  }

  /** 左部 95% 有序，其余部分为随机值 */
  public static Comparable[] leftInOder95per(int n) {
    Comparable[] a = random(n);
    Arrays.sort(a);
    for (int j = (int) (n * 0.95); j < n; j++) a[j] = Math.random() * 100;
    return a;
  }

  /** 右部 95% 有序，其余部分为随机值 */
  public static Comparable[] rightInOder95per(int n) {
    Comparable[] a = random(n);
    Arrays.sort(a);
    for (int j = 0; j < (int) (n * 0.05); j++) a[j] = Math.random() * 100;
    return a;
  }

  /** 所有元素和它们的正确位置的距离都不超过 10 */
  public static Comparable[] deviateFromOrder(int n) {
    Comparable[] a = random(n);
    Comparable[] b = new Comparable[n];
    Arrays.sort(a);
    int internal = new Random().nextInt(10), i;
    for (i = 0; i < n - internal; i++) b[i + internal] = a[i];
    for (i = 0; i < internal; i++) b[i] = a[n - internal + i];
    return b;
  }

  /** 所5% 的元素随机分布在整个数组中，剩下的数据都是有序的 */
  public static Comparable[] outOfOrder5per(int n) {
    Comparable[] a = random(n);
    Arrays.sort(a);
    for (int i = 0; i < n * 0.05; i++) a[new Random().nextInt(n)] = Math.random() * 100;
    return a;
  }

  /** 高斯分布 */
  public static Comparable[] gauss(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) a[i] = StdRandom.gaussian();
    return a;
  }

  /** 泊松分布 */
  public static Comparable[] poisson(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) a[i] = StdRandom.poisson(0.5);
    return a;
  }

  /** 几何分布 */
  public static Comparable[] geometry(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) a[i] = StdRandom.geometric(Math.random());
    return a;
  }

  /** 离散分布 */
  public static Comparable[] dispersed(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) a[i] = StdRandom.uniform();
    return a;
  }

  /** 离散分布的特殊情况（即主键仅可能取两种值） */
  public static Comparable[] specialOfDispersed(int n) {
    Comparable[] a = new Comparable[n];
    for (int i = 0; i < n; i++) a[i] = StdRandom.uniform(0, 2);
    return a;
  }
}
