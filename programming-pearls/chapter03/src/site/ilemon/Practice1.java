package site.ilemon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class Practice1 {

    static Item[] taxes = new Item[5];

    static {
        taxes[0] = new Item(0.0, 0, 2200);
        taxes[1] = new Item(0.14, 0, 2700);
        for (int i = 2; i < 5; i++) {
            taxes[i] = new Item(taxes[i - 1].getRate() + 0.01,
                    taxes[i - 1].getRate() * 500 + taxes[i - 1].getBase(),
                    taxes[i - 1].getBound() + 500);
        }
        System.out.println(taxes);
    }

    static void calTax(double income) {
        assert income > 4200 : "税前不能大于4200";
        int index = calIndex(income);
        Item item = taxes[index];
        double tax = item.getBase() + item.getRate() * ((income - ( index <= 1 ? 0.0 : taxes[index - 1].getBound()) ) );
        System.out.printf("计算公式: 税前%.2f, 缴税%.2f = %.2f + %.2f * (%.2f - %.2f)\n"
        , income, tax, item.getBase(), item.getRate(), income, index <= 1 ? 0.0 : taxes[index - 1].getBound());
    }

    public static int calIndex(double income) {
        Optional<Double> d = Arrays.stream(taxes)
                .map(e -> e.getBound())
                .collect(Collectors.toList())
                .stream()
                .filter(e -> e >= income)
                .min(Comparator.comparing(Double::doubleValue));
        int found = -1;
        for (int i = 0; i < taxes.length; i++) {
            if (d.isPresent() && d.get().equals(taxes[i].getBound())) {
                found = i;
                break;
            }
        }
        if (found == -1) {
            throw new RuntimeException("没有找到" + income + "对应的税率");
        }
        return found;
    }

    public static void main(String[] args) {
        calTax(2200);
        calTax(3000);
        calTax(4200);
        calTax(4230);
    }
}

class Item {
    // 税率
    private double rate;
    // 基本税收
    private double base;
    // 当前等级的下界
    private double bound;

    public Item(double rate, double base, double bound) {
        this.rate = rate;
        this.base = base;
        this.bound = bound;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getBound() {
        return bound;
    }

    public void setBound(double bound) {
        this.bound = bound;
    }

    @Override
    public String toString() {
        return "Item{" +
                "rate=" + rate +
                ", base=" + base +
                ", bound=" + bound +
                '}';
    }
}
