package site.ilemon;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>给定一个英语字典，找出其中所有变位词集合。例如，"stop"、"pots"和"tops"互为变位词。</p>
 * <p>因为每一个单词都可以通过改变其他单词中字母的顺序来得到。</p>
 */
public class ProblemC {
    static String[] dictionary = {"pans", "pots", "opt", "snap", "stop", "tops"};

    // 初步思路：
    // 1. 变位词长度要大于1
    // 2. 首先遍历字典，将所有单词按照长度进行分类，这样可以得到一个Map
    //    {"2":"","3":"", 4:"",...,"n":""}
    // 3. 然后遍历这个Map，对于每个长度的key中的value，这是一个集合，
    //    遍历此集合，分别计算每个单词的ascii码总和，总和相等的即为变位词。
    //    很遗憾，取总和是错误的（假设ascii为1～10,那么存在总和相等但不是变位词的两个单词）
    //    所以，遍历此集合，对每个单词进行排序，找出相等的单词即可。
    static List<Map<String, List<String>>> find() {
        List<Map<String, List<String>>> result = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        for (String item : dictionary) {
            if (map.get(item.length()) == null) {
                List<String> list = new ArrayList<>();
                list.add(item);
                map.put(item.length(), list);
            } else {
                List<String> list = map.get(item.length());
                list.add(item);
            }
        }
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            Integer key = it.next();
            List<String> words = map.get(key);
            Map<String, List<String>> itemMap = new HashMap<>();
            for (String item : words) {
                String sortedItem = sort(item);
                if (itemMap.get(sortedItem) == null) {
                    List<String> list = new ArrayList<>();
                    list.add(item);
                    itemMap.put(sortedItem, list);
                } else {
                    List<String> list = itemMap.get(sortedItem);
                    list.add(item);
                }
            }
            result.add(itemMap);
        }
        result = result.stream().map(e -> {
            Iterator<String> iterator = e.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                List<String> subList = e.get(key);
                if (subList.size() <= 1) {
                    e.remove(key);
                }
            }
            return e;
        }).collect(Collectors.toList());
        return result;
    }

    static String sort(String item) {
        char[] chars = item.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }


    // 思路2：
    // 1. 标识每个单词，遍历每个单词，按照如下结构存储：
    //    class Sign {
    //      private String sign;
    //      private String word;
    //    }
    //    其中sign存储排序后的单词，word存储原本的单词
    // 2. 对于上一步获得的sign集合中的sign进行排序得到sortedSigns
    // 3. 对sortedSigns，按照每行一种变位词进行输出

    public static List<Sign> signWords() {
        List<Sign> result = Arrays.stream(dictionary)
                .map(e -> new Sign(sort(e), e))
                .collect(Collectors.toList());
        return result;
    }

    public static List<Sign> sorted(List<Sign> signs) {
        return signs.stream().sorted(Comparator.comparing(Sign :: getSign)).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<Sign> signs = signWords();
        System.out.println("step1:");
        signs.forEach(System.out :: println);
        System.out.println("step2:");
        signs = sorted(signs);
        signs.forEach(System.out :: println);
        System.out.println("step3:");
        StringBuffer sb = new StringBuffer();
        String preSign = "";
        for (int i = 0; i < signs.size(); i++) {
            if (!preSign.equals(signs.get(i).getSign())) {
                System.out.println(sb);
                sb.delete(0, sb.length());
            }
            sb.append(signs.get(i).getWord() + "\t");
            preSign = signs.get(i).getSign();

        }
        if (sb.length() > 0) {
            System.out.println(sb);
        }
    }
}

class Sign {
    private String sign;
    private String word;

    public Sign(String sign, String word) {
        this.sign = sign;
        this.word = word;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return sign + "\t" + word;
    }
}
