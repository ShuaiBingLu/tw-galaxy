package galaxy.validate;

import galaxy.Trade;
import galaxy.entity.RomanNumeral;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: bingshuai.lu
 * @Description: "I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only.
 * "C" can be subtracted from "D" and "M" only. "V", "L", and "D" can never be subtracted.
 * @Date: Created in 2019/3/26
 * @Modified By:
 */

public class SubtractValidate  extends ValidateChain  {

    @Override
    public boolean validate(String romanStr){

        List<RomanNumeral> romanNumeralList = Trade.romans.getRoman();
        List<String> compose = new ArrayList<>();

        for(int index = 0;index<romanNumeralList.size();index++){
            RomanNumeral romanNumeral = romanNumeralList.get(index);
            if(romanNumeral.getCanSubtract()){
                for(int step=romanNumeral.getSubtractLength()+1;step<romanNumeralList.size()-index;step++){
                    compose.add(romanNumeral.getSymbol().concat(romanNumeralList.get(step+index).getSymbol()));
                }
            }else{
                for(int step = index;step<romanNumeralList.size();step++){
                    compose.add(romanNumeral.getSymbol().concat(romanNumeralList.get(step).getSymbol()));
                }
            }
        }
        int i =0;
       return !compose.stream().anyMatch(str-> romanStr.contains(str));
    }

}
