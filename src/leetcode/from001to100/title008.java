package leetcode.from001to100;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class title008 {
  public static void main(String[] args) {

    System.out.println(atoi_v3(" 1 2 "));
  }

  public static int atoi(String s) {
    s = s.trim().split("\\s+")[0];
    int n = s.length();
    if (n < 2) return 0;
    StringBuilder stringBuilder = new StringBuilder();
    int i;
    char sig = s.charAt(0);
    if (s.charAt(0) == 45 || s.charAt(0) == 43) {
      stringBuilder.append(s.charAt(0));
      i = 1;
    } else if (s.charAt(0) <= 57 && s.charAt(0) >= 48) {
      i = 0;
    } else {
      return 0;
    }
    while (i < n && s.charAt(i) <= 57 && s.charAt(i) >= 48) {
      stringBuilder.append(s.charAt(i));
      i++;
    }

    int res;
    try {
      res = Integer.parseInt(stringBuilder.toString());
    } catch (NumberFormatException e) {
      return stringBuilder.toString().length() < 2
          ? 0
          : s.charAt(0) == 45 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }
    return res;
  }

  public static int atoi_v2(String s) {
    s = s.trim().split("\\s+")[0];
    if (s.length() < 1) return 0;
    String str = "^[\\+\\-]?\\d+";
    Pattern p = Pattern.compile(str);
    Matcher m = p.matcher(s);
    int res = 0;
    if (m.find()) {
      try {
        res = Integer.parseInt(s.substring(m.start(), m.end()));
      } catch (NumberFormatException e) {
        res = s.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      }
    }
    return res;
  }

  public static int atoi_v3(String s) {
    //    s = s.trim().split("\\s+")[0];
    int n = s.length();
    if (n < 1) return 0;
    int i = 0;
    int sig = 1;
    int res = 0, pop;
    char[] c = s.toCharArray();
    for (; i < n; i++) {
      if (c[i] == ' ') {
        continue;
      } else if (c[i] == '+') {
        i++;
        break;
      } else if (c[i] == '-') {
        i++;
        sig = -1;
        break;
      } else if (c[i] <= '9' && c[i] >= '0') {
        break;
      } else {
        return 0;
      }
    }

    while (i < n && c[i] <= '9' && c[i] >= '0') {
      pop = (c[i++] - 48) * sig;
      if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7))
        return Integer.MAX_VALUE;
      if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8))
        return Integer.MIN_VALUE;
      res = res * 10 + pop;
    }
    return res;
  }
}
