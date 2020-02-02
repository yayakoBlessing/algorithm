# 排序算法

## 选择排序

- #### 基本思想

   	从头至尾扫描序列，找出其中最小的一个元素，并和第一个元素交换，接着再从剩下的元素中继续这种选择和交换方式，最终得到一个有序序列。

- #### 图解

  （图源https://www.cnblogs.com/zwtgyh/p/10631760.html）

  ![img](https://img2018.cnblogs.com/blog/1647944/201903/1647944-20190331203729814-1036465869.gif)

  

- #### 性能分析

  比较次数：(n-1)+(n-2)+...+2+1=n(n-1)/2~n²/2

  |                  | 时间复杂度 | 空间复杂度 | 比较次数 | 交换次数 |
  | ---------------- | ---------- | ---------- | -------- | -------- |
  | 最好情况（正序） | O(n²)      | 1          | n²/2     | n        |
  | 最坏情况（逆序） | O(n²)      | 1          | n²/2     | n        |
  | 平均             | O(n²)      | 1          | n²/2     | n        |

- #### 代码实现

  ```
    private static void alg4.sort(Comparable[] a) {
      int n = a.length;
      int min;
      for (int i = 0; i < n; i++) {
        min = i;
        for (int j = i + 1; j < n; j++) {
          if (less(a[j], a[min])) {
            min = j;
          }
        }
        exch(a, i, min);
      }
    }
  ```

- #### 特点

  - 运行时间与输入无关（输入如何，都将进行n次交换，未利用输入的初始状态）

  - 数据移动是最少的（交换次数与数组大小成线性关系）

    

## 插入算法

- #### 基本思想

   	将数组分成两部分，左边为有序序列，右边为无序序列。每次循环都按顺序从无序序列中取出一个值并将其插入左侧的有序序列中（寻找方法是依次通过与有序序列中的值进行比较，若比他小就交换，再与前一个值进行比较，由此往复）最终得到一个有序序列。

- #### 图解

  （图源https://www.cnblogs.com/zwtgyh/p/10631760.html）

  ![img](https://img2018.cnblogs.com/blog/1647944/201903/1647944-20190331192727473-1620564641.gif)

- #### 性能

  最坏情况的比较次数：1+2+..+(n-2)+(n-1)=n(n-1)/2~n²/2

  |                  | 时间复杂度 | 空间复杂度 | 比较次数 | 交换次数 |
  | ---------------- | ---------- | ---------- | -------- | -------- |
  | 最好情况（正序） | O(n)       | 1          | n-1      | 0        |
  | 最坏情况（逆序） | O(n²)      | 1          | n²/2     | n²/2     |
  | 平均             | O(n²)      | 1          | n²/4     | n²/4     |

- #### 代码实现

  ```
    private static void sort_v2(Comparable[] a) {
      int n = a.length;
      for (int i = 1; i < n; i++) {
        for (int j = i; j > 0 && less(a[j - 1], a[j]); j--) {
            exch(a, j, j - 1);
        }
      }
    }
  ```
  
- #### 特点

  适用于部分有序的数组以及小规模数组
  
- #### **优化**

    - **version1 原版**

    - **version2 加入哨兵**

        - **思路**

            > 插入排序的哨兵。
            > 在插入排序的实现中先找出最小的元素并将其置于数组的最左边，这样就能去掉内循环的判断条件 j>0。
            > 注意：这是一种常见的规避边界测试的方法，能够省略判断条件的元素通常称为哨兵。

        - **代码实现**

            ```
              public static void sort_v2(Comparable[] a) {
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
            ```

    - **version3 提前 break**

        - **思路**

            在找到合适的插入位置后就不再继续往前比较，因为左部已为有序数组，当遇到大于前者的时候就已经找到了插入位置，再往前也必定比他小。

        - **代码实现**

            ```
              public static void sort_v3(Comparable[] a) {
                int n = a.length;
                for (int i = 1; i < n; i++) {
                  for (int j = i; j > 0; j--) {
                    if (less(a[j], a[j - 1])) exch(a, j, j - 1);
                    else break;
                  }
                }
              }
            ```

    - **version4 加入哨兵+提前break**

        就是前两种的结合

        ```
          public static void sort_v4(Comparable[] a) {
            int n = a.length;
            int i, min = 0;
            for (i = 0; i < n; i++) {
              if (less(a[i], a[min])) min = i;
            }
            exch(a, 0, min);
            for (i = 2; i < n; i++) {
              for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) exch(a, j, j - 1);
                else break;
              }
            }
          }
        ```

    - ##### **结果评估**

        - 数组长度 10

            <img src="C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220244965.png" alt="image-20200130220244965" style="zoom:67%;" />![image-20200130220300661](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220300661.png)<img src="C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220244965.png" alt="image-20200130220244965" style="zoom:67%;" />![image-20200130220300661](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220300661.png)![image-20200130220324613](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220324613.png)<img src="C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220244965.png" alt="image-20200130220244965" style="zoom:67%;" />![image-20200130220300661](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220300661.png)![image-20200130220324613](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220324613.png)

        - 数组长度 100

            ![image-20200130220528255](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220528255.png)<img src="C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220515324.png" alt="image-20200130220515324"/>

        - 数组长度 1000
        
            ![image-20200130220715910](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220715910.png)![image-20200130220737941](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220737941.png)![image-20200130220751087](C:\Users\90598\AppData\Roaming\Typora\typora-user-images\image-20200130220751087.png)

## 希尔排序

- #### 基本思想

  使数组中任意间隔为h的元素都是有序的。

- #### 具体实现

  通过将数组中任意间隔为h的元素分割成h个子数组，再对其进行插入排序，

- #### 图解

  基于代码

- #### 性能分析

- #### 代码实现

  ```
    public static void alg4.sort(Comparable[] a) {
      int n = a.length;
      int h = 1;
      while (h < n / 3) {
        h = h * 3 + 1;
      }
      while (h >= 1) {
        for (int i = h; i < n; i++) {
          // 对子数组进行插入排序
          for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
            exch(a, j, j - h);
          }
        }
        h = h / 3;
      }
    }
  ```

  

- #### 特点

  - ，其中h