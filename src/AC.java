import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Range {
    public Double start;
    public Double end;

    public Range(Double start, Double end) {
        this.start = start;
        this.end = end;
    }

    public Range() {
        this.start = 0.0;
        this.end = 1.0;

    }

    @Override
    public String toString() {
        return "Range{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

public class AC {
    private Map<String, Double> probability;

    public AC(Map<String, Double> probability) {
        this.probability = probability;
    }

    public void calculate(String message) {
        Range range = new Range();
        for (String s : message.split("")) {
            range = range(s, range.start, range.end);
        }
        System.out.println(range);
    }


    private Range range(String letter, Double start, Double end) {
        Map<String, Range> tmp = new HashMap<>();

        double onePercent = (end - start) / 100;
        this.probability = this.probability.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        double lastMAX = start;
        for (Map.Entry<String, Double> kv : this.probability.entrySet()) {
            Double m = lastMAX + (kv.getValue() * 100) * onePercent;
            tmp.put(kv.getKey(), new Range(lastMAX, m));
            lastMAX = m;
        }
        return tmp.get(letter);


    }

    public static void main(String[] args) {
        //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Map<String, Double> map = new HashMap<>();
        map.put("M", 0.1);
        map.put("U", 0.3);
        map.put("L", 0.3);
        map.put("T", 0.2);
        map.put("I", 0.1);
        AC a = new AC(map);
        a.calculate("MULTI");
    }
}
