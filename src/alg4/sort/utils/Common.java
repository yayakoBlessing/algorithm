package alg4.sort.utils;

public class Common {
  public static boolean less(Comparable a, Comparable b) {
    /* compareTo(Comparable a,Comparable b) a<b 返回负数 a=b 返回0 a>b 返回正数 */
    return a.compareTo(b) < 0;
  }

  public static void exch(Comparable[] a, int i, int j) {
    Comparable tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  public static void print(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
    System.out.println();
  }

  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    return true;
  }
}
