package app.discount.condition;

import app.Reader;
import app.discount.policy.DiscountPolicy;

public class CouponDiscountCondition implements DiscountCondition {
    private boolean isSatisfied;
    private DiscountPolicy discountPolicy;

    public CouponDiscountCondition(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    @Override
    public void checkDiscountCondition() {
        System.out.print("%d원 할인 쿠폰을 보유중 입니까? (1)_예 (2)_아니오");
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
