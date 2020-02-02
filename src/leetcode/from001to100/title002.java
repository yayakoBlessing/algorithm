package leetcode.from001to100;

import leetcode.common.ListNode;

class title002 {
  public static void main(String[] args) {
    ListNode l1 = new ListNode(5);
    //    l1.next = new ListNode(8);
    //    l1.next.next = new ListNode(3);

    ListNode l2 = new ListNode(5);
    //    l2.next = new ListNode(6);
    //    l2.next.next = new ListNode(4);

    ListNode pre = addTwoNumbers(l1, l2);
    while (pre != null) {
      System.out.print(pre.val + "->");
      pre = pre.next;
    }
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) {
      return null;
    }

    ListNode result = new ListNode(0);
    ListNode pre = result;
    int carry = 0, sum;

    while (l1 != null || l2 != null) {
      // 若为null则补0
      int x = l1 == null ? 0 : l1.val;
      int y = l2 == null ? 0 : l2.val;

      sum = x + y + carry;
      carry = sum / 10; // 判断本次进位符

      pre.next = new ListNode(sum % 10);
      pre = pre.next;

      l1 = l1 == null ? null : l1.next;
      l2 = l2 == null ? null : l2.next;
    }

    if (carry > 0) {
      pre.next = new ListNode(carry);
    }
    return result;
  }

  // 优解
  public ListNode addTwoNumbers_v2(ListNode l1, ListNode l2) {
    ListNode root = new ListNode(0);
    ListNode cursor = root;
    int carry = 0;
    while (l1 != null || l2 != null || carry != 0) {
      int l1Val = l1 != null ? l1.val : 0;
      int l2Val = l2 != null ? l2.val : 0;
      int sumVal = l1Val + l2Val + carry;
      carry = sumVal / 10;

      ListNode sumNode = new ListNode(sumVal % 10);
      cursor.next = sumNode;
      cursor = sumNode;

      if (l1 != null) l1 = l1.next;
      if (l2 != null) l2 = l2.next;
    }

    return root.next;
  }
}
