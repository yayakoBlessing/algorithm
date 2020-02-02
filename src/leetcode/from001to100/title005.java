package leetcode;

public class title005 {
  public static void main(String[] args) {

    //    System.out.println(CenterSpread("abahhsgs"));
    //    System.out.println(DynamicProgramming("abc"));
    System.out.println(Manacher("abbgbbabbgbbaccabe"));
  }

  // 动态规划
  public static String DynamicProgramming(String s) {
    int n = s.length();
    if (n == 0) return "";

    int start = 0, end = 0;

    int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++) {
      dp[i][i] = 1; //  初始化，每个字母自身就是一个回文串
      if (i < n - 1 && s.charAt(i) == s.charAt(i + 1)) { // 相邻字符为相等也是一个回文串
        dp[i][i + 1] = 1;
        start = i;
        end = i + 1;
      }
    }

    int interval; // 字符间距，也可以理解为每次判断的长度+1
    for (interval = 2; interval < n; interval++) {
      for (int i = 0; i + interval < n; i++) { // i为起始字符下标
        int j = i + interval; // j为终止字符下标
        // 动态转移，P(i,j) = (P(i+1, j-1) and Si == Sj)
        if (dp[i + 1][j - 1] == 1 && s.charAt(i) == s.charAt(j)) {
          dp[i][j] = 1;
          start = i;
          end = j;
        }
      }
    }
    return s.substring(start, end + 1);
  }

  // 中心扩散法
  public static String CenterSpread(String s) {
    int n = s.length();
    if (n == 0) return "";

    int start = 0, end = 0;
    for (int i = 0; i < n; i++) {
      int l1 = expandAroundCenter(s, i, i); // 回文串长度为奇数，中心为字符
      int l2 = expandAroundCenter(s, i, i + 1); // 回文串长度为复数，中心为相邻字符之间
      int length = Math.max(l1, l2);
      if (length > end - start) {
        start = i - (length - 1) / 2;
        end = i + length / 2;
      }
    }
    return s.substring(start, end + 1);
  }

  // 向两边拓展
  public static int expandAroundCenter(String s, int left, int right) {
    int l = left, r = right;
    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
      l--;
      r++;
    }
    return r - l - 1;
  }

  // manacher
  public static String Manacher(String s) {
    if (s.length() == 0 || s == null) return "";
    if (s.length() == 1) return String.valueOf(s.charAt(0)); // 当只有一个字符时，直接返回该字符
    String newStr = StrInit(s);
    int n = newStr.length();
    int start = 0, max = 0;
    int mx = 0, id = 0; // 当前最远回文的右边界 及其对应的中心
    int[] p = new int[n]; // 回文半径数组
    for (int i = 1; i < n; i++) {
      // Si在最远回文内部，根据回文的对称性，给p[i]赋未匹配的字符的起始位置
      if (i < mx) p[i] = Math.min(p[2 * id - i], mx - i);
      else p[i] = 1;
      // 中心扩展 计算p[i]
      while (i - p[i] >= 0 && i + p[i] < n && newStr.charAt(i - p[i]) == newStr.charAt(i + p[i]))
        p[i]++;
      // 更新mx，id
      if (i + p[i] > mx) {
        mx = i + p[i];
        id = i;
      }
      if (p[i] > max) {
        max = p[i] - 1; // 因为p[i]包括自身在内
        start = (i - max) / 2;
      }
    }
    return s.substring(start, start + max);
  }

  public static String Manacher_v2(String s) {
    if (s.length() == 0 || s == null) return "";
    if (s.length() == 1) return String.valueOf(s.charAt(0)); // 当只有一个字符时，直接返回该字符
    String newStr = StrInit(s);
    int n = newStr.length();
    int start = 0, max = 0;
    int mx = 0, id = 0; // 当前最远回文的右边界 及其对应的中心
    int[] p = new int[n]; // 回文半径数组
    p[0] = 1; // 这里的p[0]必须赋值，否则当 2 * id - i = 0 时，p[j] = 0，会产生错误的答案
    for (int i = 1; i < n; i++) {
      // 存在对称回文 并且 该回文完全包含在最远回文内部
      // 这里 p[2 * id - i] < mx - i 不能取等，因为取等意味着达到了边界，那么对于以Si为中心的回文串来说，超出边界外还有未知领域
      if (i < mx && p[2 * id - i] < mx - i) {
        p[i] = p[2 * id - i];
        continue;
      } else {
        p[i] = i < mx ? mx - i : 1;
        while (i - p[i] >= 0 && i + p[i] < n && newStr.charAt(i - p[i]) == newStr.charAt(i + p[i]))
          p[i]++;
        // 更新右边最大边界及其对应中心
        if (p[i] + i > mx) {
          mx = p[i] + i;
          id = i;
        }
      }

      if (p[i] > max) {
        max = p[i] - 1;
        start = (i - max) / 2;
      }
    }
    return s.substring(start, start + max);
  }

  private static String StrInit(String s) {
    String str = "#";
    for (int i = 0; i < s.length(); i++) {
      str += s.charAt(i);
      str += "#";
    }
    return str;
  }
}
