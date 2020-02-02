package alg4.sort.ex.chap2.section1;

import alg4.sort.basesort.BaseSort;
import alg4.sort.basesort.Insertion;
import alg4.sort.basesort.Selection;
import alg4.sort.basesort.Shell;
import alg4.sort.utils.SortCompare;

/**
 * 双倍测试。
 *
 * <p>编写一个能够对排序算法进行双倍测试的用例。 数组规模 N 的起始值为 1000，排序后打印 N、估计排序用时、实际排序用时以及在 N 倍增之后两次用时的比例。
 * 用这段程序验证在随机输入模型下插入排序和选择排序的运行时间都是平方级别的。 对希尔排序的性能做出猜想并验证你的猜想。
 *
 * @author cyy
 */
public class ex31 {
  public static void main(String[] args) {
    String[] s = {"Selection", "Insertion", "Shell"};
    BaseSort[] baseSorts = {new Selection(), new Insertion(), new Shell()};

    SortCompare.timeRandomInput(s, baseSorts);
  }
}
