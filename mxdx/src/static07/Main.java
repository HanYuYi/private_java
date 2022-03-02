package static07;

/**
 * 静态字段和静态方法
 * @author yuyihan
 *
 */
public class Main {
    public static void main(String[] args) {
        TaxAmount[] taxRateList = new TaxAmount[] {
                new DefaultTaxAmount(4118),
                new Salary(6540),
                new StateCouncilSpecialAllowance(34880)
        };

        System.out.println("缴费金额：" + toatlTaxRate(taxRateList) + "元");

        System.out.println("缴费人数：" + TaxAmount.getCount() + "人");
    }

    public static double toatlTaxRate(TaxAmount... taxRateList) {
        double total = 0;
        for (TaxAmount taxRate: taxRateList) {
            total += taxRate.getTaxAmount();
            taxRate.say();
        }
        return total;
    }
}

interface IHello {
    // 接口里静态方法必须有body
    public default void say() {
        System.out.println("我缴税了！");
    }
}

/**
 * 抽象所有公共字段，方法
 * @author yuyihan
 *
 */
interface ITaxAmount extends IHello {
    //  接口里定义的字段编译器会加上 public static final 修饰符
    double TAX_RATE = 0.1;

    //	编译器会加上 public abstract
    double getTaxAmount();
}

/**
 * 接口可以多实现
 * @author yuyihan
 *
 */
abstract class TaxAmount implements ITaxAmount {

    private static int count;

    protected double amount;

    public TaxAmount(double amount) {
        this.amount = amount;
        TaxAmount.count++;
    }

    /**
     * 统计缴费次数
     * @return
     */
    public static int getCount() {
        return TaxAmount.count;
    }
}

/**
 * 普通人报税金额
 * @author yuyihan
 * 接口可以多实现
 */
class DefaultTaxAmount extends TaxAmount {

    public DefaultTaxAmount(double amount) {
        super(amount);
    }


    @Override
    public double getTaxAmount() {
        return amount * TAX_RATE;
    }
}

/**
 * 薪水报税金额，5000以内不报税
 * @author yuyihan
 *
 */
class Salary extends TaxAmount {

    final private int BASE = 5000;

    public Salary(double amount) {
        super(amount);
    }

    @Override
    public double getTaxAmount() {
        if (amount <= BASE) {
            return 0;
        }
        return (amount - BASE) * TAX_RATE;
    }
}

/**
 * 工资收入和享受国务院特殊津贴的报税,10000以内不报税
 * @author yuyihan
 *
 */
class StateCouncilSpecialAllowance extends TaxAmount {

    final private int BASE = 10000;

    public StateCouncilSpecialAllowance(double amount) {
        super(amount);
    }

    @Override
    public double getTaxAmount() {
        if (amount <= BASE) {
            return 0;
        }
        return (amount - BASE) * TAX_RATE;
    }
}