package leetcode;

import java.util.HashMap;

public class title003 {
  public static void main(String[] args) {
    double start = System.nanoTime();
    int r1 = lengthOfLongestSubstring("bbb");
    double end = System.nanoTime();
    System.out.println(r1 + "\t" + (end - start) + "ns");

    start = System.nanoTime();
    int r2 = lengthOfLongestSubstring_v2("bbb");
    end = System.nanoTime();
    System.out.println(r1 + "\t" + (end - start) + "ns");
  }

  public static int lengthOfLongestSubstring(String s) {
    int n = s.length();
    if (n == 0) return 0;
    int left = 0, max = 0;
    HashMap<Character, Integer> hashMap = new HashMap<>(n);
    for (int i = 0; i < n; i++) {
      if (hashMap.containsKey(s.charAt(i))) {
        left = Math.max(left, hashMap.get(s.charAt(i)) + 1);
      }
      hashMap.put(s.charAt(i), i);
      max = Math.max(max, i - left + 1);
    }
    return max;
  }

  public static int lengthOfLongestSubstring_v2(String s) {
    // i相当于left
    int size, i = 0, j, k, max = 0;
    char[] arr = s.toCharArray();
    size = arr.length;
    for (j = 0; j < size; j++) {
      for (k = i; k < j; k++) {
        if (arr[k] == arr[j]) {
          i = k + 1;
          break;
        }
      }
      if (j - i + 1 > max) {
        max = j - i + 1;
      }
    }
    return max;
  }
}
