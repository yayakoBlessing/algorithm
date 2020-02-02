package leetcode.from001to100;

public class title009 {
  public static void main(String[] args) {
    System.out.println(1221);
  }

  public static boolean isPalindrome(int x) {
    if (x < 0 || (x != 0 && x % 10 == 0)) return false;
    int n = Integer.toString(x).length();
    int[] ints = new int[n];
    int i;
    for (i = 0; i < n; i++) {
      ints[i] = x % 10;
      x /= 10;
    }
    for (i = 0; i < n / 2; i++) {
      if (ints[i] != ints[n - i - 1]) return false;
    }
    return true;
  }

  //
  public static boolean isPalindrome_v2(int x) {
    if (x < 0 || (x != 0 && x % 10 == 0)) return false;
    int rev = 0;
    while (rev < x) {
      rev = rev * 10 + x % 10;
      x /= 10;
    }
    return (rev == x) || (rev / 10 == x); // 12321 rev = 123, x = 12
  }
}
