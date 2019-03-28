package galaxy;

import galaxy.entity.Declare;
import galaxy.entity.Output;
import galaxy.entity.RomanNumerals;
import galaxy.util.XmlUtil;
import galaxy.translate.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 程序启动类
 *
 */
public class Main
{

    public static RomanNumerals romans;

    private static Declare declare = new Declare();
    private static HashMap<String, Float> creditsMap = new HashMap<>();
    private static Output output = new Output();
    private static final List<ITranslate> translates = new ArrayList<>(
            Arrays.asList(new DeclareTranslate(declare), new CreditTranslate(creditsMap,declare,output),
                    new ManyTranslate(output,creditsMap,declare), new MuchTranslate(declare,output)
            )
    );

    public static void main( String[] args ) throws Exception {

        romans = readXml();
        System.out.println(readInput() );
    }
    /*
     * 读取xml，路径为resources/roman.xml
     */
    public static RomanNumerals readXml(){
        String fileName = Main.class.getClassLoader().getResource("roman.xml").getPath();
        RomanNumerals romanNumerals =
                (RomanNumerals) XmlUtil.convertXmlFileToObject(RomanNumerals.class,fileName);
        return romanNumerals;
    }
    /*
     * 读取输入，路径为resources/input.txt
     */
    public static String readInput() throws Exception{
        BufferedReader br = null;
        InputStream input = Main.class.getClassLoader().getResourceAsStream("input.txt");
        br = new BufferedReader(new InputStreamReader(input));
        String line=null;
        while((line = br.readLine())!=null){
            boolean foundTranslator = false;
            for (ITranslate translateFunc : translates) {
                if (translateFunc.canTranslate(line) ) {
                    translateFunc.translateLine(line);
                    foundTranslator = true;
                    break;
                }
            }
            if (!foundTranslator) {
                output.addError();
            }
        }
        return  output.getOutputResult();
    }

}
