package galaxy.validate;

import galaxy.Main;
import galaxy.entity.RomanNumeral;
import galaxy.util.RomanConvert;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: bingshuai.lu
 * @Description: 验证是否可以重复出现
 * @Date: Created in 2019/3/26
 * @Modified By:
 */
public class RepeatValidate extends ValidateChain {

    public boolean validate(String romanStr){
        if(romanStr==null || romanStr.equals(""))
            return  false;
        String[] strArray = romanStr.split("");
        //check repeat symbol
        List<RomanNumeral> romanNumeralList = Main.romans.getRoman();
        if(checckNoRepeat(romanNumeralList,strArray)){
            if(strArray.length>3){
                return checkRepeatable4Times(strArray)&&checkRepeatableRule(strArray);
            }
            return true;
        }
            return  false;
    }
    /*
     * 检查是否存在不可重复的 比如 "D", "L", and "V" can never be repeated.
     * @param List<RomanNumeral> 字符的对应列表
     * @param strArray 需要判断的字符数组
     * @return 是否重复
     */
    private boolean checckNoRepeat(List<RomanNumeral> romanNumeralList,String[] strArray){
        HashMap<String,Integer> norepeatMap = new HashMap<>();
        for(RomanNumeral item : romanNumeralList){
            if(!item.isRepeat()){
                norepeatMap.put(item.getSymbol(),0);
            }
        }
        for (String s : strArray) {
            s = s.toUpperCase();
            if (norepeatMap.containsKey(s)) {
                norepeatMap.put(s, norepeatMap.get(s) + 1);
            }
        }
        Collection<Integer> values = norepeatMap.values();
        return !values.stream().anyMatch(x->x>1);
    }
    /*
     *
     *判断是否超过4次
     * @param strArray 需要判断的字符数组
     * @return 是否通过
     */
    private boolean checkRepeatable4Times(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 记录当前字符重复次数
            int count = 0;
            String s = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                String s1 = arr[j];
                if (s1.equals(s)) {
                    count++;
                }
            }
            if (count > 4) {
                return false;
            }
        }
        return true;
    }
    /*
     * They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX.
     *
     * @param strArray 需要判断的字符数组
     * @return 是否通过
     */
    private boolean checkRepeatableRule(String[] arr) {
        int len = arr.length;
        for (int head = 0; head < len; head++) {
            String flag = arr[head];
            int value = RomanConvert.getRomanNumeral(flag).getValue();
            for (int tail = head + 1; tail < len; tail++) {
                String cursor = arr[tail];

                if (!flag.equals(cursor) && tail != 3) {
                    break;
                }
                if (tail == head + 3 && flag.equals(cursor)) {
                    return false;
                }
                if (tail == head + 4 && flag.equals(cursor) ) {
                    String temp = arr[tail-1];
                    int tempValue = RomanConvert.getRomanNumeral(temp).getValue();
                    if(tempValue<value)
                        return true;
                    return false;
                }
            }
        }

        return true;
    }

}
