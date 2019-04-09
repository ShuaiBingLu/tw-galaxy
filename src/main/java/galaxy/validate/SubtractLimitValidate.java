package galaxy.validate;

import galaxy.Trade;
import galaxy.entity.RomanNumerals;
import galaxy.util.RomanConvert;

import java.util.*;

/**
 * @Author: bingshuai.lu
 * @Description: Only one small-value symbol may be subtracted from any large-value symbol.
 * @Date: Created in 2019/3/26
 * @Modified By:
 */
public class SubtractLimitValidate extends ValidateChain {

    public boolean validate(String romanStr) {
        RomanNumerals romans = Trade.romans;
        HashMap<String, String> map = new HashMap<>();
        boolean isSuccess = true;
        for (int i = 0; i < romanStr.length() - 1; i++) {
            String childStr = romanStr.substring(i, i + 2);
            String[] childArr = childStr.split("");
            Integer left = RomanConvert.getRomanNumeral(childArr[0].toUpperCase()).getValue();
            Integer right = RomanConvert.getRomanNumeral(childArr[1].toUpperCase()).getValue();
            if (left < right) {
                map.put(childStr, romanStr.substring(1));
            }

        }
        isSuccess = checkSubstractedRule(map);
        return isSuccess;
    }
    /*
     * 判断是否有重复的值出现
     * @param 对应的map
     * @return 是否重复
     */
    private boolean checkSubstractedRule(HashMap<String, String> map) {
        Collection<String> values = map.values();
        Set<String> set = new HashSet<>();
        set.addAll(values);
        if (values.size() != set.size()) {
            return false;
        }
        return true;
    }

}
