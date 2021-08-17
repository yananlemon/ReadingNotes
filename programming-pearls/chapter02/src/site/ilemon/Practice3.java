package site.ilemon;

/**
 * <p>向量旋转-递归</p>
 *
 * @author Andy.Yan
 */
public class Practice3 {

    // 旋转向量x其实就是交换向量ab的两段，得到向量ba。这里a代表x中前i个元素。
    // 假设a比b短，将b分为bl和br，使得br具有和a相同的长度，交换a和br，也就
    // 将ablbr转换为brbla，序列a此时以及处于其最终的位置，因此现在的问题就
    // 集中到交换b的两部分。由于新问题与原来的问题具有相同的形式，我们可以递归
    // 解决。
    // abcdefgh -> defghabc (n=8,i =3)
    static String recursion(String x, int pos) {
        String a = x.substring(0, pos); // abc
        int len = x.length() - a.length() * 2;
        if (len >= 0) {
            String bl = x.substring(pos, pos + len); // de
            String br = x.substring(pos + len); // fgh
            String b = recursion(br + bl, pos);
            x = b + a;
        } else {
            String b = x.substring(pos);
            return b + a;
        }
        return x;
    }

    public static void main(String[] args) {
        String source = "abcdefgh";
        //String source = "abcd";
        String rs = recursion(source, 3);
        System.out.println(rs);
    }
}
