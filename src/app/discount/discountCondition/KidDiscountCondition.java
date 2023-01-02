package app.discount.discountCondition;

import app.discount.discountPolicy.DiscountPolicy;

import java.util.Scanner;

public class KidDiscountCondition implements DiscountCondition {
    boolean isSatisfied;

    private DiscountPolicy discountPolicy;

    public KidDiscountCondition(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    public int applyDiscount(int price) {
        return discountPolicy.calculateDiscountedPrice(price);
    }

    @Override
    public void checkDiscountCondition() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("나이가 어떻게 되십니까? ");
        int age = Integer.parseInt(scanner.nextLine());

        setSatisfied(age < 20);
    }
}
