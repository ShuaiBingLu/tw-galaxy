package galaxy.translate;

/**
 * @Author: bingshuai.lu
 * @Description: 解析输出的接口类
 * @Date: Created in 13:23 2019/3/26
 * @Modified By:
 */
public interface ITranslate {

    /*
      判断是否可以进行验证
     */
    boolean canTranslate(String str);

    /*
        验证行并处理
     */
    void translateLine(String str);
}
