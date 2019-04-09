package galaxy.entity;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 9:39 2019/4/9
 * @Modified By:
 */
public class Input {

    private Credit credit = new Credit();

    private Declare declare = new Declare();

    public Declare getDeclare() {
        return declare;
    }

    public Credit getCredit() {

        return credit;
    }

}
