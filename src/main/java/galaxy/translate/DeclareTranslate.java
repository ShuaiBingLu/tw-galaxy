package galaxy.translate;

import galaxy.entity.Declare;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @Author: bingshuai.lu
 * @Description: 翻译 XX is XX
 * @Date: Created in 13:23 2019/3/26
 * @Modified By:
 */
public class DeclareTranslate implements ITranslate {


    private static final Pattern declarePattern = Pattern.compile("^(\\w+) \\bis\\b ([I|V|X|L|C|D|M])$");

    private Declare declare;
    public DeclareTranslate(Declare declare){
        this.declare = declare;
    }
    @Override
    public boolean canTranslate(String str) {
        return declarePattern.matcher(str).matches();
    }

    @Override
    public void translateLine(String str) {
        Matcher matcher = declarePattern.matcher(str);
        matcher.find();
        String goods = matcher.group(1);
        String symbol = matcher.group(2);
        declare.putValue(goods,symbol);
    }
}
