## 2.1.11

### 题目

将希尔排序中实时计算递增序列改为预先计算并存储在一个数组中。

### 评估

预先计算递增序列并存储在一个数组的方式只能在对小规模数组进行排序时有所优化。

![image-20200202165103926](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200202165103926.png)

### 代码实现

Math.log(2 * (n / 3) + 1) / Math.log(3)) + 1 这个是用数组规律算出来的长度

```
/**
 * 将希尔排序中实时计算递增序列改为预先计算并存储在一个数组中。
 *
 * @author cyy
 */
public class ex11 implements BaseSort {

  public static void main(String[] args) {
    int n;
    for (int i = 2; i <= 5; i++) {
      n = (int) Math.pow(10, i);
      System.out.println();
      System.out.println("ArraySize:" + n);
      System.out.println("\t原版用时：" + SortCompare.timeRandomInput(new Shell(), n));
      System.out.println("\t改版用时：" + SortCompare.timeRandomInput(new ex11(), n));
    }
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

/**
 * 算法比较
 *
 * @param baseSort 需要进行比较的各算法类型
 * @return 排序所用时间
 */
public static double timeRandomInput(BaseSort baseSort, int n) {
  double total = 0;
  Double[] a = new Double[n];
  for (int i = 0; i < T; i++) {
    for (int j = 0; j < n; j++) {
      a[j] = Math.random() * 100;
    }
    total += time(baseSort, a);
  }
  return total;
}

```



## 2.1.12

### 题目

令希尔排序打印出递增序列的每个元素所带来的比较次数和数组大小的比值。
编写一个测试用例对随机 Double 数组进行希尔排序，验证该值是一个小常数，数组大小按照 10 的幂次递增，不小于 100。

### 评估

<img src="C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200201190956677.png" alt="image-20200201190956677" style="zoom:80%;" />

### 代码实现

```
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
```



## 2.1.24

### 题目

插入排序的哨兵。
在插入排序的实现中先找出最小的元素并将其置于数组的最左边，这样就能去掉内循环的判断条件 j>0。
使用 SortCompare 来评估这种做法的效果。
注意：这是一种常见的规避边界测试的方法，能够省略判断条件的元素通常称为哨兵。

### 评估

改版后的插入排序性能有所提升

![image-20200202165908009](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200202165908009.png)

### 代码实现

```
/**
 * 插入排序的哨兵。 在插入排序的实现中先找出最小的元素并将其置于数组的最左边，这样就能去掉内循环的判断条件 j>0。 使用 SortCompare 来评估这种做法的效果。
 * 注意：这是一种常见的规避边界测试的方法，能够省略判断条件的元素通常称为哨兵。
 *
 * @author cyy
 */
public class ex24 implements BaseSort {

  public static void main(String[] args) {
    int n;
    for (int i = 2; i <= 5; i++) {
      n = (int) Math.pow(10, i);
      System.out.println();
      System.out.println("ArraySize = " + n);
      System.out.println("\t原版用时：" + SortCompare.timeRandomInput(new Insertion(), n));
      System.out.println("\t改版用时：" + SortCompare.timeRandomInput(new ex24(), n));
    }
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

/**
 * 算法比较
 *
 * @param baseSort 需要进行比较的各算法类型
 * @return 排序所用时间
 */
public static double timeRandomInput(BaseSort baseSort, int n) {
  double total = 0;
  Double[] a = new Double[n];
  for (int i = 0; i < T; i++) {
    for (int j = 0; j < n; j++) {
      a[j] = Math.random() * 100;
    }
    total += time(baseSort, a);
  }
  return total;
}
```



## 2.1.25

### 题目

不需要交换的插入排序。
在插入排序的实现中使较大元素右移一位只需要访问一次数组（而不用使用 exch()）。
使用 SortCompare 来评估这种做法的效果。

### 评估

不需要交换的插入排序性能有一定提升，但只有在数量级较大的时候才明显

![image-20200202170320776](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200202170320776.png)

### 代码实现

```
/**
 * 不需要交换的插入排序。
 *
 * <p>在插入排序的实现中使较大元素右移一位只需要访问一次数组（而不用使用 exch()）。 使用 SortCompare 来评估这种做法的效果。
 *
 * @author cyy
 */
public class ex25 implements BaseSort {
  public static void main(String[] args) {
    int n;
    for (int i = 2; i <= 5; i++) {
      n = (int) Math.pow(10, i);
      System.out.println();
      System.out.println("ArraySize = " + n);
      System.out.println("\t原版用时：" + SortCompare.timeRandomInput(new Insertion(), n));
      System.out.println("\t改版用时：" + SortCompare.timeRandomInput(new ex25(), n));
    }
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

/**
 * 算法比较
 *
 * @param baseSort 需要进行比较的各算法类型
 * @return 排序所用时间
 */
public static double timeRandomInput(BaseSort baseSort, int n) {
  double total = 0;
  Double[] a = new Double[n];
  for (int i = 0; i < T; i++) {
    for (int j = 0; j < n; j++) {
      a[j] = Math.random() * 100;
    }
    total += time(baseSort, a);
  }
  return total;
}
```



## 2.1.27

### 题目

希尔排序的用时是次平方级的。
在你的计算机上用 SortCompare 比较希尔排序和插入排序以及选择排序。
测试数组的大小按照 2 的幂次递增，从 128 开始。

### 评估

shell性能更加优越，insertion其次，selection最差

数据越大越明显

![image-20200202173124341](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200202173124341.png)

### 代码实现

```
/**
 * 希尔排序的用时是次平方级的。
 *
 * <p>在你的计算机上用 SortCompare 比较希尔排序和插入排序以及选择排序。 测试数组的大小按照 2 的幂次递增，从 128 开始
 *
 * @author cyy
 */
public class ex27 {
  private static String[] s = {"Selection", "Insertion", "Shell"};
  private static BaseSort[] baseSorts = {new Selection(), new Insertion(), new Shell()};

  public static void main(String[] args) {
    // 执行次数
    int t = 5;
    List<Comparable[]> a = new ArrayList<>(t);
    Comparable[] b;
    double[] total;
    int n;
    for (int i = 7; i <= 15; i++) {
      n = (int) Math.pow(2, i);
      System.out.println();
      System.out.println("ArraySize = " + n);
      for (int j = 0; j < t; j++) {
        b = ArrayGenerate.random(n);
        a.add(b);
      }
      total = SortCompare.compare(baseSorts, a, n);
      for (int k = 0; k < baseSorts.length; k++) {
        System.out.println(s[k] + "：" + total[k]);
      }
      a.clear();
    }
  }
}

/** 构造随机 Double数组 */
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
```



### 2.1.28

### 题目

相等的主键。
对于主键仅可能取两种值的数组，评估和验证插入排序和选择排序的性能，假设两种主键值出现的概率相同。

### 评估

![image-20200201212020179](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200201212020179.png)

### 代码实现

```
public class ex28 {

  public static void compare(HashMap<String, BaseSort> hashMaps, int n) {
    Comparable[] a, b;
    int j;
    double[] total = new double[hashMaps.size()];

    for (int i = 0; i < 5; i++) {
      a = ArrayGenerate.towKey(n);
      b = new Comparable[n];
      j = 0;
      for (String s : hashMaps.keySet()) {
        System.arraycopy(a, 0, b, 0, n);
        total[j++] += SortCompare.time(hashMaps.get(s), b);
      }
    }
    j = 0;
    for (String s : hashMaps.keySet()) {
      System.out.println(s + total[j++] / 5 + "ms");
    }
  }

  public static void main(String[] args) {
    HashMap<String, BaseSort> hashMap = new LinkedHashMap<>(3);
    hashMap.put("Selection：", new Selection());
    hashMap.put("Insertion：", new Insertion());
    int n;
    for (int i = 2; i <= 5; i++) {
      n = (int) Math.pow(10, i);
      System.out.println();
      System.out.println("ArraySize = " + n);
      compare(hashMap, n);
    }
  }
}

/**
 * 主键仅可能取两种值的数组
 *
 * @param n 数组长度
 * @return 数组
 */
public static Comparable[] towKey(int n) {
  Comparable[] a = new Comparable[n];
  for (int i = 0; i < n; i++) {
    a[i] = new Random().nextInt(2);
  }
  return a;
}
```



## 2.1.31

### 题目

双倍测试。
编写一个能够对排序算法进行双倍测试的用例。
数组规模 N 的起始值为 1000，排序后打印 N、估计排序用时、实际排序用时以及在 N 倍增之后两次用时的比例。
用这段程序验证在随机输入模型下插入排序和选择排序的运行时间都是平方级别的。
对希尔排序的性能做出猜想并验证你的猜想。

### 评估

选择排序及插入排序的用时增长速率都在平方级左右，希尔排序在线性左右，偏大。

![image-20200201215953625](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200201215953625.png)

### 代码实现

```
public class ex31 {
  public static void main(String[] args) {
    BaseSort[] baseSorts = {new Selection(), new Insertion(), new Shell()};
    String[] s = {"Selection", "Insertion", "Shell"};
    int i, j, n;
    double[] pre = {5, 5, 5};
    double[] total;
    for (i = 0, n = 1000; i <= 5; i++, n *= 2) {
      System.out.println();
      System.out.println("ArraySize = " + n);
      total = SortCompare.compare(baseSorts, n);
      for (j = 0; j < baseSorts.length; j++) {
        System.out.println("\t" + s[j] + "：" + total[j] / 5 + "ms\tradio：" + total[j] / pre[j]);
      }
      System.arraycopy(total, 0, pre, 0, 3);
    }
  }
}

public static double[] compare(BaseSort[] baseSorts, int n) {
  Comparable[] a, b;
  int j;
  double[] total = new double[baseSorts.length];
  for (int i = 0; i < 5; i++) {
    a = ArrayGenerate.randomDouble(n);
    b = new Comparable[n];
    j = 0;
    for (BaseSort baseSort : baseSorts) {
      System.arraycopy(a, 0, b, 0, n);
      total[j++] += SortCompare.time(baseSort, b);
    }
  }
  return total;
}
```



## 2.1.34

### 题目

罕见情况。
编写一个测试用例，调用 sort() 方法对实际应用中可能出现困难或极端情况的数组进行排序。
比如，数组可能已经是有序的，或是逆序的，数组中的所有主键相同，数组的主键只有两种值，大小是 0 或 1的数组。

### 评估

数组长度10000，各排序分别执行5次取其总用时

综合来看，希尔排序性能更佳

- **选择排序：**各种情况下更适用于逆序数组，在该情况下优于插入排序
- **插入排序：**整体性能略优于选择排序，在处理逆序排序时用时较多，各种情况下更适用于有序数组
- **希尔排序：**整体性能较好

![image-20200202140654240](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200202140654240.png)

### 代码实现

```
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
```



## 2.1.35

### 题目

不均匀的概率分布。编写一个测试用例，使用非均匀分布的概率来生成随机排列的数据，包括：

1. 高斯分布
2. 泊松分布
3. 几何分布
4. 离散分布（一种特殊情况见练习 2.1.28）。

评估并验证这些输入数据对本节讨论的算法的影响。

### 评估

数组长度100000，各排序分别执行5次取其总用时

官方给的库中有构造各分布值的方法，直接调用即可

（我这里就直接调用官方库了，jar包在最开始有分享附件可以下载

（因为我早就忘了这些分布是什么了..........对不起我的高数/离散/随机/高代/...老师【跪

![image-20200202163705308](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200202163705308.png)

### 代码实现

```
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
```



## 2.1.36

### 题目

不均匀的数据。编写一个测试用例，生成不均匀的测试数据，包括：

1. 一半数据是 0，一半数据是 1
2. 一半数据是 0，1/4 是 1，1/4 是 2，以此类推
3. 一半数据是 0，一半是随机 int 值。

评估并验证这些输入数据对本节讨论的算法的性能的影响。

### 评估

数组长度100000，各排序分别执行5次取其总用时

综合来看，选择排序性能相较最差，插入排序在数组未被打乱（即有序）情况下性能比较优越，而希尔排序总体性能都相较优越

- **数组未打乱**

  ![image-20200202150933807](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200202150933807.png)

- **数组被打乱**

  ![image-20200202151406745](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200202151406745.png)



### 代码实现

```
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
```



## 2.1.37

### 题目

部分有序。编写一个测试用例，生成部分有序数组，包括：

1. 95% 有序，其余部分为随机值。
2. 所有元素和它们的正确位置的距离都不超过 10。
3. 5% 的元素随机分布在整个数组中，剩下的数据都是有序的。

评估并验证这些输入数据对本节讨论的算法的性能的影响。

### 评估

数组长度10000，各排序分别执行5次取其总用时

综合来看，性能比较结果都是大同小异的。

选择排序性能相较最差，插入排序在数组有序情况下性能比较优越，而希尔排序总体性能都相较优越

![image-20200202155553974](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200202155553974.png)

### 代码实现

```
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
```

