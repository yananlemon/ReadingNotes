package site.ilemon;

import java.util.Scanner;

/**
 * DFS算法输出数字的全排列
 */
public class Main {

    static int N        ;
    static int[] a      = new int[10];
    static int[] book   = new int[10];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数(1~9)");
        N = scanner.nextInt();
        dfs(1);
    }

    static void dfs(int step) {
        if (step == N + 1) {
            for (int i = 1; i <= N; i++) {
                System.out.print(a[i] + ",");
            }
            System.out.println();
            return;
        }
        // 尝试每一种可能
        for (int i = 1; i <= N; i++) {
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
