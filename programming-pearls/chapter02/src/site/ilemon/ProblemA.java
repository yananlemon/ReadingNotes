package site.ilemon;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanan
 */
public class ProblemA {

    private static int mark = 0X08;
    private static int index = 4;

    // 用来存放某个bit为0的集合
    private static List<Integer> firstList = new ArrayList<>();

    // 用来存放某个bit为1的集合
    private static List<Integer> secondList = new ArrayList<>();

    public static int findMissingNumber(Integer[] source) {
        int missingNumber = 0;
        while (true) {
            if (firstList.size() > secondList.size() ) {
                missingNumber |= calMask(1, index);//0000 1000
                source = new Integer[secondList.size()];
                source = secondList.toArray(source);
                firstList.clear();
                secondList.clear();
                index --;
            } else if (firstList.size() < secondList.size() ) {
                missingNumber &= calMask(0, index);// 0000 1000 & 1111 1111
                source = new Integer[firstList.size()];
                source = firstList.toArray(source);
                firstList.clear();
                secondList.clear();
                index --;
            }

            if (source.length == 1) {
                int n = source[0];
                int lastPos = n & 1;
                missingNumber &= 0X0F;
                if (lastPos == 0) {
                    missingNumber |= 1;
                } else if (lastPos == 1) {
                    missingNumber &= 0X0E;
                }
                break;
            }
            for (int i = 0; i < source.length; i++) {
                int temp = source[i] & calMask(1, index);
                if (temp == 0) {
                    firstList.add(source[i]);
                } else {
                    secondList.add(source[i]);
                }
            }

        }
        return missingNumber;
    }

    static byte calMask(int val, int position) {
        if (val == 0) {
            return (byte) ~(1 << (position - 1));
        } else if (val == 1) {
            return (byte) (1 << (position - 1));
        }
        return 0;
    }

    public static void main(String[] args) {
        //Integer[] source = {0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15};
        Integer[] source = {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15};
        System.out.println("丢失的数字是：" + findMissingNumber(source));;
    }
}
