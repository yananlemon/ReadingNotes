package site.ilemon;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>给定一个英语字典，找出其中所有变位词集合。例如，"stop"、"pots"和"tops"互为变位词。</p>
 * <p>因为每一个单词都可以通过改变其他单词中字母的顺序来得到。</p>
 */
public class ProblemC {
    static String[] dictionary = {"stop", "pots", "tops", "abc", "cba", "xyz"};

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

    public static void main(String[] args) {
        List<Map<String, List<String>>> result = find();
        for (Map<String, List<String>> item : result) {
            System.out.println(item);
        }
    }
}
