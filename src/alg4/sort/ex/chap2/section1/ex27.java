package alg4.sort.ex.chap2.section1;

import alg4.sort.basesort.BaseSort;
import alg4.sort.basesort.Insertion;
import alg4.sort.basesort.Selection;
import alg4.sort.basesort.Shell;
import alg4.sort.utils.SortCompare;

/**
 * 希尔排序的用时是次平方级的。
 *
 * <p>在你的计算机上用 SortCompare 比较希尔排序和插入排序以及选择排序。 测试数组的大小按照 2 的幂次递增，从 128 开始
 *
 * @author cyy
 */
public class ex27 {

  public static void main(String[] args) {
    String[] s = {"Selection", "Insertion", "Shell"};
    BaseSort[] baseSorts = {new Selection(), new Insertion(), new Shell()};

    SortCompare.timeRandomInput(s, baseSorts);
  }
}
