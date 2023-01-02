package app.discount.condition;

import app.discount.policy.DiscountPolicy;

import java.util.Scanner;

public class KidDiscountCondition implements DiscountCondition{
    boolean isSatisfied;

    DiscountPolicy discountPolicy;

    public KidDiscountCondition(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    @Override
    public void checkDiscountCondition() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("20세 미만입니까? (1)_예 (2)_아니오");
        String input = scanner.nextLine();
        if (input.equals("1")) setSatisfied(true);
        else if (input.equals("2")) setSatisfied(false);
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    @Override
    public int applyDiscount(int price) {
        return discountPolicy.calculateDiscountedPrice(price);
    }
}
