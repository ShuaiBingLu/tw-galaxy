package galaxy.util;

import galaxy.Trade;
import galaxy.entity.RomanNumeral;
import galaxy.entity.RomanNumerals;
import galaxy.validate.RepeatValidate;
import galaxy.validate.SubtractLimitValidate;
import galaxy.validate.SubtractValidate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: bingshuai.lu
 * @Description: 用于转换数字与罗马数字
 * @Date: Created in 13:23 2019/3/26
 * @Modified By:
 */
public class RomanConvert {

    /**
     * 根据罗马字符去获取对应的实体类
     *
     * @param str roman like 'I,V'
     * @return the RomanNumeral实体类
     */
    public static RomanNumeral getRomanNumeral(String str) {
        List<RomanNumeral> romanNumeralList = Trade.romans.getRoman();

        Collection<RomanNumeral> res = romanNumeralList.stream().filter(romanNumeral ->
                romanNumeral.getSymbol().equals(str)).collect(Collectors.toList());
        if (res.size() > 0)
            return ((List<RomanNumeral>) res).get(0);
        return null;
    }
    /**
     * 根据罗马字符去计算对应的值
     *
     * @param str roman like 'I,V'
     * @return the result of str
     */
    public static Integer calculateRomal(String str) {

        RomanNumerals romanNumerals = Trade.romans;
        RepeatValidate repeatValidate = new RepeatValidate();
        SubtractLimitValidate subtractLimitValidate = new SubtractLimitValidate();
        SubtractValidate subtractValidate = new SubtractValidate();
        repeatValidate.setNext(repeatValidate).setNext(subtractLimitValidate).setNext(subtractValidate);
        if (!repeatValidate.handleValidate(str)) {
            return null;
        }
        char[] chars = str.toCharArray();
        int addValue = 0;
        int minusValue = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            int preValue = getRomanNumeral(String.valueOf(chars[i])).getValue();
            int nextValue = getRomanNumeral( String.valueOf(chars[i + 1])).getValue();
            if (preValue < nextValue) {
                minusValue = minusValue - preValue;
            } else {
                addValue = addValue + preValue;
            }
            if (i == chars.length - 2)
                addValue = addValue + nextValue;
        }
        return addValue + minusValue;
    }
}
