package site.ilemon;

/**
 * <p>第7题，转置矩阵</p>
 *
 * @author Andy.Yan
 */
public class Practice7 {

    static final int row = 4000;
    static final int col = 4000;
    static int[][] matrix = new int[row][col];

    //strictfp
    static {
        int w = 1;
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < col; k++) {
                matrix[i][k] = w++;
            }
        }
    }

    static int[][] transpose() {
        int[][] result = new int[col][row];
        for (int i = 0; i < col; i++) {
            for (int k = 0; k < row; k++) {
                result[i][k] = matrix[k][i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        int[][] rs = transpose();
        System.out.println("花费：" + (System.currentTimeMillis() - beginTime) + "ms");
        /*for (int i = 0; i < col; i++) {
            for (int k = 0; k < row; k++) {
                System.out.print(rs[i][k] + ",");
            }
            System.out.println();
        }*/
    }
}
