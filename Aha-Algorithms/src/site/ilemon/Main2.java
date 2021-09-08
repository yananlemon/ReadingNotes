package site.ilemon;

import java.util.Scanner;

/**
 * DFS算法输出XXX + XXX = XXX
 */
public class Main2 {

    static int total    ;
    static int[] a      = new int[10];
    static int[] book   = new int[10];

    public static void main(String[] args) {
        dfs(1);
        System.out.println("共有" + total/2);
    }

    static void dfs(int step) {
        if (step == 10) {
            if (a[1]*100 + a[2] * 10 + a[3] + a[4]*100 + a[5] * 10 + a[6] ==
                    a[7]*100 + a[8] * 10 + a[9]) {
                System.out.printf("找到一个解");
                total++;
                System.out.printf("%d%d%d + %d%d%d = %d%d%d\n", a[1], a[2], a[3],
                        a[4], a[5], a[6], a[7], a[8], a[9]);
                return;
            }
        }

        // 尝试每一种可能
        for (int i = 1; i <= 9; i++) {
            if (book[i] == 0) {
                a[step] = i;
                book[i] = 1;
                dfs(step + 1);
                book[i] = 0;
            }
        }
        return;
    }
}
