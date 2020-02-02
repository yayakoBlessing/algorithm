import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * <p>比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * <p>L C I R E T O E S I I G E D H N 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * <p>请你实现这个将字符串进行指定行数变换的函数：
 *
 * <p>string convert(string s, int numRows); 示例 1:
 *
 * <p>输入: s = "LEETCODEISHIRING", numRows = 3 输出: "LCIRETOESIIGEDHN" 示例 2:
 *
 * <p>输入: s = "LEETCODEISHIRING", numRows = 4 输出: "LDREOEIIECIHNTSG" 解释:
 *
 * <p>L D R E O E I I E C I H N T S G
 *
 * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class title006 {
  public static void main(String[] args) {
    System.out.println(convert_v2("PAYPALISHIRING", 4));
  }

  public static String convert_v2(String s, int numRows) {
    if (numRows == 1 || s.length() <= 2) {
      return s;
    }
    int n = s.length();
    List<StringBuilder> stringBuilders = new ArrayList<>();
    int flag, i = 0;
    for (i = 0; i < numRows; i++) {
      stringBuilders.add(new StringBuilder());
    }
    i = 0;
    flag = -1;
    for (Character c : s.toCharArray()) {
      stringBuilders.get(i).append(c);
      if (i == 0 || i == numRows - 1) flag = -flag;
      i += flag;
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (StringBuilder row : stringBuilders) {
      stringBuilder.append(row);
    }
    return stringBuilder.toString();
  }

  public static String convert(String s, int numRows) {
    if (s.length() <= 2 || numRows == 0) {
      return s;
    }

    int n = s.length();
    int x = 2 * numRows - 2;
    int i, j, interval, m;
    StringBuilder res = new StringBuilder();

    for (i = x, j = 0; i >= 0; j++, i -= 2) {
      res.append(s.charAt(j));
      interval = i;

      if (interval == x || interval == 0) {
        interval = x;
        m = j + interval;
        while (m < n) {
          res.append(s.charAt(m));
          m += interval;
        }
      } else {
        m = j + interval;
        while (m < n) {
          res.append(s.charAt(m));
          interval = x - interval;
          m = m + interval;
        }
      }
    }

    return res.toString();
  }
}
