package galaxy.entity;

import javax.xml.bind.annotation.*;

/**
 * @Author: bingshuai.lu
 * @Description: 字符与数字的对应实体类
 * @Date: Created in 13:23 2019/3/26
 * @Modified By:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "symbol",
        "value",
        "isRepeat",
        "canSubtract",
        "subtractLength"

})
@XmlRootElement(name = "roman")
public class RomanNumeral {

    //罗马字符
    private String symbol;
    //对应的值
    private int value;
    //是否可重复
    private boolean isRepeat;
    //是否可被减
    private boolean canSubtract;
    //减去的长度
    private int subtractLength;

    public boolean getCanSubtract() {
        return canSubtract;
    }


    public int getSubtractLength() {
        return subtractLength;
    }


    public boolean isRepeat() {
        return isRepeat;
    }


    public String getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

}
