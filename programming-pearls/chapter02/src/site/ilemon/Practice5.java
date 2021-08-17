package site.ilemon;

/**
 * <p>第5题：向量旋转函数将向量ab变为ba。如何将向量abc变为cba</p>
 *
 * @author Andy.Yan
 */
public class Practice5 {


    static void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char t = chars[j];
            chars[j] = chars[i];
            chars[i] = t;
            j--;
            i++;
        }
    }

    public static void main(String[] args) {
        // 如果n=12, A = abc, B = de, C = fghijkl
        // 那么旋转后:fghijkl de abc
        char[] sources = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
        reverse(sources, 0, 2);
        reverse(sources, 3, 4);
        reverse(sources, 5, 11);
        reverse(sources, 0, 11);
        System.out.println(sources);

    }

}
