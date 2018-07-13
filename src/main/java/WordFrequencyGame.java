import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {
          return  returnString(inputStr);

        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
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

    public String returnString(String inputStr){
        try {
            String[] arr = inputStr.split("\\s+");
            Map<String, List<Input>> map = getListMap(Arrays.asList(arr).stream().map(x->{return  new Input(x,1);}).collect(Collectors.toList()));
            List<Input> list = new ArrayList<>();
            for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                Input input = new Input(entry.getKey(), entry.getValue().size());
                list.add(input);
            }
            list.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
      return       list.stream().map(w-> w.getValue() + " " + w.getWordCount()).collect(Collectors.joining("\n"));

        } catch (Exception e) {
            return "Calculate Error";
        }

    }
}
