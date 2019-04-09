package galaxy;

import galaxy.entity.Input;
import galaxy.entity.Output;
import galaxy.entity.RomanNumerals;
import galaxy.translate.*;
import galaxy.util.XmlUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 程序启动类
 */
public class Trade {

    //改为private
    public static final RomanNumerals romans;

    static {
        romans = readXml();
        if (romans == null)
            System.exit(0);
    }


    public static void main(String[] args) throws Exception {
        Input input = new Input();
        Output output = new Output();
        List<ITranslate> translates = new ArrayList<>(
                Arrays.asList(new DeclareTranslate(input.getDeclare()), new CreditTranslate(input.getCredit(),
                                input.getDeclare(), output),
                        new ManyTranslate(output, input.getCredit(), input.getDeclare()),
                        new MuchTranslate(input.getDeclare(), output)
                )
        );
        System.out.println(readInput(translates,output));
    }

    /*
     * 读取xml，路径为resources/roman.xml
     */
    private static RomanNumerals readXml() {
        String fileName = Trade.class.getClassLoader().getResource("roman.xml").getPath();
        File file = new File(fileName);
        if (file.exists()) {
            RomanNumerals romanNumerals =
                    (RomanNumerals) XmlUtil.convertXmlFileToObject(RomanNumerals.class, fileName);

            return romanNumerals;
        }
        return null;
    }

    /*
     * 读取输入，路径为resources/input.txt
     */
    private static String readInput(List<ITranslate> translates,Output output) throws Exception {
        BufferedReader br = null;
        InputStream input = Trade.class.getClassLoader().getResourceAsStream("input.txt");
        br = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = br.readLine()) != null) {
            boolean foundTranslator = false;
            if (!foundTranslator) {
                output.addError();
            }
            for (ITranslate translateFunc : translates) {
                if (translateFunc.canTranslate(line)) {
                    translateFunc.translateLine(line);
                    foundTranslator = true;
                    break;
                }
            }
        }
        return output.getOutputResult();
    }

}
