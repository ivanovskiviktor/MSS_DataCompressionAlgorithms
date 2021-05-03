import java.util.*;

public class LZW {
    Map<String, Long> indexes = new TreeMap<>();
    long currentIndex = 0;

    public Long generateIndex() {
        return ++currentIndex;
    }

    public List<Long> encode(String message) {
        List<Long> result = new ArrayList<>();
        String tmp = "";
        for (Character c : message.toCharArray()) {
            indexes.computeIfAbsent(String.valueOf(c), k -> generateIndex());
        }
        for (int i = 0; i < message.length() - 1; i++) {
            int j = i;
            while (true) {
                if (j == message.length()) {
                    result.add(indexes.get(message.substring(i, j)));
                    i = j;
                    break;
                }
                tmp = message.substring(i, j + 1);
                if (indexes.containsKey(tmp)) {
                    j++;
                } else {
                    result.add(indexes.get(message.substring(i, j)));
                    indexes.put(tmp, generateIndex());
                    i = j - 1;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LZW lzw = new LZW();
        lzw.encode("/TAN/HAN/HAN/AN/").forEach(System.out::println);

    }
}
