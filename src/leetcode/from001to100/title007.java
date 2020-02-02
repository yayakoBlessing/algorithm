package leetcode.from001to100;

public class title007 {
  public static void main(String[] args) {
    System.out.println(reverse(-2147483648));
  }

  public static int reverse(int x) {
    if (x >= 2147483647 || x <= -2147483648) return 0;

    String str = Integer.toString(Math.abs(x));
    int n = 0;
    for (int i = str.length() - 1; i >= 0; i--) {
      n += Integer.parseInt(String.valueOf(str.charAt(i))) * Math.pow(10, i);
    }
    return x < 0 ? -n : n;
  }

  public static int reverse_v2(int x) {
    int ans = 0, pop;
    while (x != 0) {
      pop = x % 10; // 依次取个位数
      x /= 10;
      if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE && pop > 7)) return 0;
      if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE && pop < -8)) return 0;
      ans = ans * 10 + pop;
    }
    return ans;
  }
}
