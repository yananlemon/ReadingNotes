package site.ilemon;

import java.util.Arrays;
import java.util.List;

/**
 * <p>第8题：给定一个n元实数集合、一个实数t，和一个整数k，
 * 如何快速确定是否存在一个k元子集，其元素之和不超过t？</p>
 *
 * @author Andy.Yan
 */
public class Practice8 {

    // 思路1：dfs
    static final int N = 5;
    static final int K = 3;
    static final int T = 1;
    static boolean found = false;
    static final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    static final int[] book = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[] a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static void dfs(int step) {
        if (step == K + 1) {
            int sum = 0;
            for (int i = 1; i <= K; i++)
                sum += a[i];
            if (sum <= T) {
                found = true;
                for (int i = 1; i <= K; i++)
                    System.out.print(a[i]);
                System.out.println();
            }
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (book[i] == 0) {
                a[step] = i;
                book[i] = 1;
                dfs(step + 1);
                book[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        dfs(1);
        System.out.println(found ? "发现满足条件的集合" : "没有发现");
    }
}
