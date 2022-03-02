package abstract05;

/**
 * 抽象类，抽象公共方法
 * @author yuyihan
 *
 */
public class Main {
    public static void main(String[] args) {
        TaxAmount[] taxRateList = new TaxAmount[] {
                new DefaultTaxAmount(4300),
                new Salary(6854),
                new StateCouncilSpecialAllowance(27311)
        };

        System.out.print(toatlTaxRate(taxRateList));
    }

    public static double toatlTaxRate(TaxAmount... taxRateList) {
        double amount = 0;
        for (TaxAmount taxRate: taxRateList) {
            amount += taxRate.getTaxAmount();
        }
        return amount;
    }
}

/**
 * 抽象类，抽象所有公共字段，方法
 * @author yuyihan
 *
 */
abstract class TaxAmount {
    //  final修饰的方法可以阻止被覆写；
    //	final修饰的class可以阻止被继承；
    //	final修饰的field必须在创建对象时初始化，之后不可修改。
    final protected double TAX_RATE = 0.1;
    protected double amount;

    public TaxAmount(double amount) {
        this.amount = amount;
    }

    public abstract double getTaxAmount();
}


/**
 * 普通报税金额
 * @author yuyihan
 *
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