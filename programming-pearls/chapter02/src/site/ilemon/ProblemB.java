package site.ilemon;

public class ProblemB {


    /**
     * 将一维向量向左旋转{@code count}次
     * <p>首先将{@code vector}的前{@code count}个元素拷贝到一个临时数组中；</p>
     * <p>然后将余下的vector.length - count个元素拷贝到result中；</p>
     * <p>最后将临时数组拷贝到result中，从vector.length - count处开始。</p>
     * @param vector 一维向量
     * @param count 旋转的次数
     * @return 旋转后的向量
     */
    public static char[] leftRotate(char[] vector, int count) {
        char[] result = new char[vector.length];
        char[] temp = new char[count];
        System.arraycopy(vector, 0, temp, 0, count);
        int len = result.length - count;
        int position = count;
        for (int i = 0; i < len; i++) {
            result[i] = vector[count++];
        }
        System.arraycopy(temp, 0, result, result.length - position, temp.length);
        return result;
    }


    // 把数组从count处分成两段xy，x求逆，y求逆，然后xy整体求逆。
    // 例如abcd要向左旋转两次
    // x = ab
    // y = cd
    // x求逆 = ba
    // y求逆 = dc
    // xy = badc
    // xy整体求逆  = cdab
    public static void reverse(char[] source, int i, int j) {
        while (i < j) {
            char temp = source[i];
            source[i] = source[j];
            source[j] = temp;
            i++;
            j--;
        }
    }




    public static void main(String[] args) {
        char[] vector = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        //char[] vector = {'a', 'b', 'c', 'd'};
        int count = 3;
        reverse(vector, 0, count - 1);
        reverse(vector, count, vector.length - 1);
        reverse(vector, 0, vector.length - 1);
        for (int i = 0; i < vector.length; i++)
            System.out.print(vector[i]);
    }
}
