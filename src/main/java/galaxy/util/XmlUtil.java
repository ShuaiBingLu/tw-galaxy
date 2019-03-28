package galaxy.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @Author: bingshuai.lu
 * @Description: xml解析帮助类
 * @Date: Created in 18:34 2019/3/26
 * @Modified By:
 */
public class XmlUtil {

    /*
     * 根据路径解析xml为实体类
     * @param clazz the type of class
     * @param xml path
     * @return object
     */
    public static Object convertXmlFileToObject(Class clazz, String xmlPath) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fr = null;
            try {
                fr = new FileReader(xmlPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            xmlObject = unmarshaller.unmarshal(fr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }
}
