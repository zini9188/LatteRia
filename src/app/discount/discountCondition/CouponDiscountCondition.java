package app.discount.discountCondition;

import app.discount.discountPolicy.DiscountPolicy;

import java.util.Scanner;

public class CouponDiscountCondition implements DiscountCondition {
    boolean isSatisfied;

    private DiscountPolicy discountPolicy;

    public CouponDiscountCondition(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    @Override
    public int applyDiscount(int price) {
        return discountPolicy.calculateDiscountedPrice(price);
    }

    @Override
    public void checkDiscountCondition() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("할인 쿠폰을 보유중 입니까? (1)_예 (2)_아니오");
        String input = scanner.nextLine();
        if (input.equals("1")) setSatisfied(true);
        else if (input.equals("2")) setSatisfied(false);
    }
}
