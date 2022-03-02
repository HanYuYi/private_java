package interface06;

/**
 * interface 接口
 * @author yuyihan
 *
 */
public class Main {
    public static void main(String[] args) {
        ITaxAmount[] taxAmountList = new ITaxAmount[] {
                new DefaultTaxAmount(3728),
                new Salary(8330),
                new StateCouncilSpecialAllowance(4197)
        };

        System.out.println(totalTaxAmount(taxAmountList));
    }

    public static double totalTaxAmount(ITaxAmount... totalTaxAmountList) {
        double total = 0;
        for(ITaxAmount taxAmount: totalTaxAmountList) {
            total += taxAmount.getTaxAmount();
            taxAmount.tip();
        }
        return total;
    }
}

interface IHello {
    //	接口可以有属性，不过会被默认为public static final 修饰
    default void tip() {
        System.out.println("我缴税了！");
    }
}

interface ITaxAmount extends IHello {
    double getTaxAmount();
}

/**
 * 抽象类，抽象公共字段、方法
 * @author yuyihan
 *
 */
abstract class TaxAmountBase implements ITaxAmount {
    protected final double TAX_TATE = 0.1;
    protected double amount;

    public TaxAmountBase(double amount) {
        this.amount = amount;
    }
}

/**
 * 普通报税金额
 * @author yuyihan
 *
 */
class DefaultTaxAmount extends TaxAmountBase {
    protected double amount;

    public DefaultTaxAmount(double amount) {
        super(amount);
    }

    @Override
    public double getTaxAmount() {
        return amount * TAX_TATE;
    }
}

/**
 * 薪水报税金额，5000以内不报税
 * @author yuyihan
 *
 */
class Salary extends TaxAmountBase {
    private final double BASE = 5000;

    public Salary(double amount) {
        super(amount);
    }

    @Override
    public double getTaxAmount() {
        if (amount <= BASE) {
            return 0;
        }
        return (amount - BASE) * TAX_TATE;
    }
}

/**
 * 工资收入和享受国务院特殊津贴的报税,10000以内不报税
 * @author yuyihan
 *
 */
class StateCouncilSpecialAllowance extends TaxAmountBase {
    private final double BASE = 20000;

    public StateCouncilSpecialAllowance(double amount) {
        super(amount);
    }

    @Override
    public double getTaxAmount() {
        if (amount <= BASE) {
            return 0;
        }
        return (amount - BASE) * TAX_TATE;
    }
}