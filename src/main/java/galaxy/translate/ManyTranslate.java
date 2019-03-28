package galaxy.translate;

import galaxy.entity.Declare;
import galaxy.entity.Output;
import galaxy.util.RomanConvert;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: bingshuai.lu
 * @Description: 解析 how many Credits is
 * @Date: Created in 13:23 2019/3/26
 * @Modified By:
 */
public class ManyTranslate implements ITranslate {

    private static final Pattern manyPattern = Pattern.compile("^how many Credits is (.*) (\\w+) \\?$");

    private Output output;

    private HashMap<String, Float> credits;

    private Declare declare;


    public ManyTranslate(Output output, HashMap<String, Float> credits,Declare declare){
        this.output = output;
        this.credits = credits;
        this.declare = declare;
    }
    @Override
    public boolean canTranslate(String str) {
        return manyPattern.matcher(str).matches();
    }

    @Override
    public void translateLine(String str) {
        Matcher matcher = manyPattern.matcher(str);
        matcher.find();
        String symbol = matcher.group(1);
        String goods = matcher.group(2);
        StringBuilder romanNumber = new StringBuilder();

        for (String value : symbol.split(" ")) {
            String t = declare.getDeclare(value);
            romanNumber.append(t);
        }
        Integer value = RomanConvert.calculateRomal(romanNumber.toString());
        if(value==null){
            output.addError();
            return;
        }
        float res = credits.get(goods);
        String resStr = symbol + " " +goods + " is " + new Float(res * value).longValue() + " Credits";
        output.addOutput(resStr);
    }
}
