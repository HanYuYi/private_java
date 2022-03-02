package innerClass09;

/**
 * Anonymous class 匿名内部类
 * @author yuyihan
 * 实现一个充值返利的活动，并且生日当天，再返利的基础上再加用户流水的5%红包
 */
public class Test02 {
    public static void main(String[] args) {

        double[] totalAmountList = new double[] {
                new TotalRebateAmount02(0, 1000, "07-23").getRebateAmount(),
                new TotalRebateAmount02(2, 3800).getRebateAmount(),
                new TotalRebateAmount02(2, 3800, "02-11").getRebateAmount(),
        };

        double backAmount = 0;
        for (double amount: totalAmountList) {
            backAmount += amount;
        }

        System.out.println("本次一共派发金额：" + backAmount + "元");
    }
}

/**
 * 抽象用户的返利利率和用户级别对应
 * @author yuyihan
 *
 */
abstract class UserLevel02 implements Inter {
    protected String birthday;
    protected double level;
    protected double depositAmount;

    // 返利利率和用户级别对应
    protected final double[][] levelList = new double[][] {
            {0, 0},
            {1, 0.05},
            {2, 0.12},
            {2, 0.15}
    };

    public UserLevel02(double level, double depositAmount) {
        this.level = level;
        this.depositAmount = depositAmount;
    }

    @Override
    public abstract double getRebateAmount();
}

/**
 * 计算返利金额
 * @author yuyihan
 * 这里用到了内部类，计算生日返利
 */
class TotalRebateAmount02 extends UserLevel02 {

    // 没有生日字段不计算生日返利
    public TotalRebateAmount02(double level, double depositAmount) {
        super(level, depositAmount);
    }

    public TotalRebateAmount02(double level, double depositAmount, String birthday) {
        super(level, depositAmount);
        this.birthday = birthday;
    }

    @Override
    public double getRebateAmount() {
        double amount = 0;
        for (double[] item: levelList) {
            if (level == item[0] && depositAmount >= NEED_DEPOSIT_AMOUNT) {
                amount = depositAmount * item[1];
                break;
            }
        }

        /**
         * 匿名内部类，可以new 接口和抽象类
         */
        InterBirthday bAmount = new InterBirthday() {
            String birthday;
            // 当天日期
            String DATE_TODAY = "02-11";
            // 生日返水利率
            double BIRTHDAY_RETATE = 0.05;

            @Override
            public double getBirthdayRebateAmount() {
                if (birthday == DATE_TODAY) {
                    return depositAmount * BIRTHDAY_RETATE;
                }
                return 0;
            }
        };

        return amount + bAmount.getBirthdayRebateAmount();
    }
}