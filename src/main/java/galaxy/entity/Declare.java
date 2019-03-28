package galaxy.entity;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author: bingshuai.lu
 * @Description: 获取输入的标识
 * @Date: Created in 13:23 2019/3/26
 * @Modified By:
 */
public class Declare {

    private Map<String,String> declareMap = new HashMap<>();

    public void putValue(String key,String value){
        declareMap.put(key,value);
    }

    public String getDeclare(String key){
        return declareMap.get(key);
    }
}
