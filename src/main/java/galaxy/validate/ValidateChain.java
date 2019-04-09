package galaxy.validate;


/**
 * @Author: bingshuai.lu
 * @Description: 责任链模式处理类
 * @Date: Created in 2019/3/26
 * @Modified By:
 */
public abstract class ValidateChain {

    protected abstract boolean validate(String str);

    private ValidateChain next;
    /*
     *
     * @Description: 
     * 
     * @auther: bingshuai.lu
     * @date: 15:14 2019/4/8 
     * @param: [validate]
     * @return: galaxy.validate.ValidateChain
     *
     */
    public ValidateChain setNext(ValidateChain validate){
        this.next = validate;
        return this.next;
    }
    /*
     * 检查是否存在不可重复的 比如 "D", "L", and "V" can never be repeated.
     * @param str
     */
    public boolean handleValidate(String str){
        boolean res = this.validate(str);
        if(this.next!=null && res){
           res= this.next.handleValidate(str);
        }
        return res;
    }
}
