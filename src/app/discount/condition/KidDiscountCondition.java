package app.discount.condition;

import app.Reader;
import app.discount.policy.DiscountPolicy;

public class KidDiscountCondition implements DiscountCondition {
    private boolean isSatisfied;
    private DiscountPolicy discountPolicy;

    public KidDiscountCondition(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    @Override
    public void checkDiscountCondition() {
        System.out.println("20세 미만입니까? (1)_예 (2)_아니오");
        setSatisfied(Reader.readDiscount() == 1);
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
