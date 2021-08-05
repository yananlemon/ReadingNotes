package site.ilemon;

public class Test2 {
    private static final int N = 20000;
    private static int[] bitmap = new int[1 + N/32];
    private static int position = 9999;

    public static void set(int i) {
        int j;
        if (i < 0) {
            i = -i;
            j = position - i;
        }else {
            j = position + i;
        }
        bitmap[j >> 5] |= (1 << (j & 31));
    }


    static int test(int i) {

        return bitmap[i >> 5] & (1 << (i & 31));
    }

    public static void main(String[] args) {
        int[] array = {9000,2,10,-2000,-9999,-1699};
        for ( int i = 0; i < array.length; i++ ) {
            set(array[i]);
        }
        for ( int i = 0; i < 20000; i++ ) {
            if (test(i) != 0 )
                System.out.println(i-position);
        }
    }
}
