package galaxy.entity;

import java.util.HashMap;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 9:41 2019/4/9
 * @Modified By:
 */
public class Credit {

    private HashMap<String, Float> creditMap = new HashMap<>();

    public float getCreditValue(String key) {
        return creditMap.get(key);
    }

    public void addCredit(String key, float value) {
        creditMap.put(key, value);
    }
}
