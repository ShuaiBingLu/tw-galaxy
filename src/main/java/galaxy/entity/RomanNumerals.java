package galaxy.entity;

import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * @Author: bingshuai.lu
 * @Description: 方便转化xml而实现的外部封装类
 * @Date: Created in 2019/3/26
 * @Modified By:
 */
@XmlRootElement(name = "Romans")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"roman"})

public class RomanNumerals {

    private List<RomanNumeral> roman;

    public List<RomanNumeral> getRoman() {
        return roman;
    }

    public void setRoman(List<RomanNumeral> roman) {
        this.roman = roman;
    }
}
