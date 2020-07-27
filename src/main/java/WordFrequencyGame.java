import java.util.*;

public class WordFrequencyGame {
    public static final String SPACE_PATTERN = "\\s+";
    public static final String DELIMITER = "\n";
    public static final String EMPTY_SPACE = " ";
    public static final String CALCULATE_ERROR = "calculate error";
    public String getResult(String sentence) {
        if (sentence.split(SPACE_PATTERN).length == 1) {
            return sentence + " 1";
        } else {
            try {
                String[] words = sentence.split(SPACE_PATTERN);
                List<WordInfo> inputList = new ArrayList<>();
                for (String word : words) {
                    WordInfo input = new WordInfo(word, 1);
                    inputList.add(input);
                }
                Map<String, List<WordInfo>> map = getListMap(inputList);
                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
                    WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;
                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                StringJoiner joiner = new StringJoiner(DELIMITER);
                for (WordInfo w : inputList) {
                    String s = w.getValue() + EMPTY_SPACE + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo input : inputList) {
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
