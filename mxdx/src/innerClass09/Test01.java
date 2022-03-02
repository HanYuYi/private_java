package innerClass09;

/**
 * inner class 内部类
 * @author yuyihan
 * 实现一个充值返利的活动，并且生日当天，再返利的基础上再加用户流水的5%红包
 */
public class Test01 {
    public static void main(String[] args) {

        TotalRebateAmount u1 = new TotalRebateAmount(0, 1000);
        TotalRebateAmount.BirthdayRebateAmount u1Birthday = u1.new BirthdayRebateAmount("07-23");

        TotalRebateAmount u2 = new TotalRebateAmount(2, 3800);

        TotalRebateAmount u3 = new TotalRebateAmount(2, 3800);
        TotalRebateAmount.BirthdayRebateAmount u3Birthday = u3.new BirthdayRebateAmount("02-11");

        double[] totalAmountList = new double[] {
                u1.getRebateAmount() + u1Birthday.getBirthdayRebateAmount(),
                u2.getRebateAmount(),
                u3.getRebateAmount() + u3Birthday.getBirthdayRebateAmount(),
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
abstract class UserLevel implements Inter {

    protected double level;
    protected double depositAmount;

    // 返利利率和用户级别对应
    protected final double[][] levelList = new double[][] {
            {0, 0},
            {1, 0.05},
            {2, 0.12},
            {2, 0.15}
    };

    public UserLevel(double level, double depositAmount) {
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
class TotalRebateAmount extends UserLevel {

    public TotalRebateAmount(double level, double depositAmount) {
        super(level, depositAmount);
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
        return amount;
    }

    /**
     * 常规内部类
     * @author yuyihan
     *
     */
    class BirthdayRebateAmount {
        private String birthday;
        // 当天日期
        private String DATE_TODAY = "02-11";
        // 生日返水利率
        private double BIRTHDAY_RETATE = 0.05;

        public BirthdayRebateAmount(String birthday) {
            this.birthday = birthday;
        }

        public double getBirthdayRebateAmount() {
            if (birthday == DATE_TODAY) {
                return depositAmount * BIRTHDAY_RETATE;
            }
            return 0;
        }
    }
}