package galaxy.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: bingshuai.lu
 * @Description: 输出的帮助类
 * @Date: Created in 13:23 2019/3/26
 * @Modified By:
 */
public class Output {
    private static final  String error="I have no idea what you are talking about";

    private List<String> output = new ArrayList<>();

    public void addOutput(String line){
        output.add(line);
    }
    public String getOutputResult(){
        return String.join(System.lineSeparator(),output);
    }
    public void addError(){
        output.add(error);
    }
}
