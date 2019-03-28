package galaxy.translate;

import galaxy.entity.Declare;
import galaxy.entity.Output;
import galaxy.util.RomanConvert;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: bingshuai.lu
 * @Description: 翻译 is Creidits
 * @Date: Created in 2019/3/26
 * @Modified By:
 */
public class CreditTranslate implements ITranslate {

    private static final Pattern creditsPattern = Pattern.compile("^(.*) (\\w+\\b) is (\\d+) Credits$");

    private HashMap<String, Float> creditsMap;
    private Declare declare;
    private Output output;
    public CreditTranslate(HashMap<String, Float> map,Declare declare,Output output){
        creditsMap = map;
        this.declare = declare;
        this.output = output;
    }
    @Override
    public boolean canTranslate(String str) {
        return creditsPattern.matcher(str).matches();
    }

    @Override
    public void translateLine(String str) {
        Matcher matcher = creditsPattern.matcher(str);
        matcher.find();
        String unit = matcher.group(1);
        String goods = matcher.group(2);
        String credits = matcher.group(3);


        StringBuilder romanNumber = new StringBuilder();
        for (String value : unit.split(" ")) {
            String symbol = declare.getDeclare(value);
            romanNumber.append(symbol);
        }
        Integer nums = RomanConvert.calculateRomal(romanNumber.toString());
        if(nums==null){
            output.addError();
            return;
        }
        float res = Long.parseLong(credits)/(float)nums;
        creditsMap.put(goods,res);
    }

}
