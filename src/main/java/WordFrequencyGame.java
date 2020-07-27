import java.util.*;

public class WordFrequencyGame {
    public static final String SPACE_PATTERN = "\\s+";
    public static final String DELIMITER = "\n";
    public static final String EMPTY_SPACE = " ";
    public static final String CALCULATE_ERROR = "calculate error";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfos = calculateWordFrequency(sentence);
            wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
            return generateWordFrequencyResult(wordInfos);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<WordInfo> wordInfos = new ArrayList<>();
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        for (String uniqueWord : new HashSet<>(words)) {
            wordInfos.add(new WordInfo(uniqueWord, Collections.frequency(words, uniqueWord)));
        }
        return wordInfos;
    }

    private String generateWordFrequencyResult(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner(DELIMITER);
        for (WordInfo wordInfo : wordInfos) {
            String result = wordInfo.getValue() + EMPTY_SPACE + wordInfo.getWordCount();
            joiner.add(result);
        }
        return joiner.toString();
    }
}
