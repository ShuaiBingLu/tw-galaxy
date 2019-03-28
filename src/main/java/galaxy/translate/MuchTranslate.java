package galaxy.translate;

import galaxy.entity.Declare;
import galaxy.entity.Output;
import galaxy.util.RomanConvert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: bingshuai.lu
 * @Description: 解析 how much is
 * @Date: Created in 13:23 2019/3/26
 * @Modified By:
 */
public class MuchTranslate implements ITranslate {

    private static final Pattern muchPattern = Pattern.compile("^how much is (.*)\\?$");

    private Declare declare;
    private Output output;

    public MuchTranslate(Declare declare, Output output) {
        this.declare = declare;
        this.output = output;
    }

    @Override
    public boolean canTranslate(String str) {
        return muchPattern.matcher(str).matches();
    }

    @Override
    public void translateLine(String str) {
        Matcher matcher = muchPattern.matcher(str);
        matcher.find();
        String goods = matcher.group(1);
        StringBuilder romanNumber = new StringBuilder();

        for (String value : goods.split(" ")) {
            String t = declare.getDeclare(value);
            romanNumber.append(t);
        }
        Integer value = RomanConvert.calculateRomal(romanNumber.toString());
        if (value == null) {
            output.addError();
        }else{
            output.addOutput(goods+" is "+value);
        }
    }
}
