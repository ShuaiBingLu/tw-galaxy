package galaxy;


import galaxy.validate.RepeatValidate;
import galaxy.validate.ValidateChain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidateTest {

    /*
    The symbols "I", "X", "C", and "M" can be repeated three times in succession,
    but no more. (They may appear four times if the third and
    fourth are separated by a smaller value, such as XXXIX.)
     "D", "L", and "V" can never be repeated.
     */
    static List<String> lists  = new ArrayList<>();
    static List<String> noRepeatList = new ArrayList<>();
    @Before
    public void setUp(){
       // Trade.romans = Trade.readXml();
        lists = new ArrayList<>(
                Arrays.asList("IIIVI")
        );
        noRepeatList = new ArrayList<>(
                Arrays.asList("DD","LL","VV")
        );
    }
    @Test
    public void validateRepeat(){
        ValidateChain chain = new RepeatValidate();
        String test = "";
        System.out.println(chain.handleValidate("III"));
        for(String str:lists){
            if(!chain.handleValidate(str))
                test="test";
        }

        Assert.assertEquals("test","test",test);
    }
}
