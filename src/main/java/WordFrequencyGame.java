import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {


            List<InputModel> inputList = getInputsModel(inputStr.split("\\s+"));


            Map<String, List<InputModel>> map = getListMap(inputList);

            List<WordModel> modellist = getWordModels(map);

            modellist.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            return getWordView(modellist).toString();

        }
    }

    private StringJoiner getWordView(List<WordModel> modellist) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordModel w : modellist) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner;
    }

    private List<WordModel> getWordModels(Map<String, List<InputModel>> map) {
        List<WordModel> modellist = new ArrayList<>();
        for (Map.Entry<String, List<InputModel>> entry : map.entrySet()) {
            WordModel wordModel = new WordModel(entry.getKey(), entry.getValue().size());
            modellist.add(wordModel);
        }
        return modellist;
    }

    private List<InputModel> getInputsModel(String[] arr) {
        List<InputModel> inputList = new ArrayList<>();
        for (String s : arr) {
            InputModel input = new InputModel(s);
            inputList.add(input);
        }
        return inputList;
    }

    private Map<String, List<InputModel>> getListMap(List<InputModel> inputList) {
        Map<String, List<InputModel>> map = new HashMap<>();
        for (InputModel input : inputList) {
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