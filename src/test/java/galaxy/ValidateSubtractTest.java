package galaxy;

import galaxy.validate.SubtractValidate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidateSubtractTest {
    static List<String> lists  = new ArrayList<>();

    @Before
    public void setUp(){
        //Trade.romans = Trade.readXml();
        lists = new ArrayList<>(
                Arrays.asList("IIIVI")
        );

    }
    @Test
    public void validateRepeat(){
        SubtractValidate validate = new SubtractValidate();
        Assert.assertTrue(validate.validate("IV"));
        Assert.assertTrue(validate.validate("IX"));
        Assert.assertTrue(!validate.validate("IC"));
        Assert.assertTrue(!validate.validate("ID"));
        Assert.assertTrue(!validate.validate("IM"));

        Assert.assertTrue(validate.validate("XL"));
        Assert.assertTrue(validate.validate("XC"));
        Assert.assertTrue(!validate.validate("XD"));
        Assert.assertTrue(!validate.validate("XM"));


        Assert.assertTrue(validate.validate("CD"));
        Assert.assertTrue(validate.validate("CM"));

    }
}
