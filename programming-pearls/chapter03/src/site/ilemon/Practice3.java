package site.ilemon;

/**
 * <p>banner</p>
 */
public class Practice3 {

    //   ooooooo
    //   o     o
    //   ooooooo
    //   o     o
    //   ooooooo
    public static char[][] gennerate(char c) {
        char[][] result = new char[5][7];
        for (int i = 0; i < result.length; i++) {
            for (int k = 0; k < result[i].length; k++) {
                if (i % 2 == 0) {
                    result[i][k] = c;
                } else {
                    if (k == 0 || k == 6) {
                        result[i][k] = c;
                    } else {
                        result[i][k] = ' ';
                    }
                }

            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[][] result = gennerate('A');
        for (int i = 0; i < result.length; i++) {
            for (int k = 0; k < result[i].length; k++) {
                System.out.print(result[i][k]);
            }
            System.out.println();
        }
    }
}
