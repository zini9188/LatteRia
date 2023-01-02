package app.discount.discountPolicy;

public class FixedAmountDiscountPolicy implements DiscountPolicy {
    int discountPrice;

    public FixedAmountDiscountPolicy(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public int calculateDiscountedPrice(int price) {
        return Math.max(price - discountPrice, 0);
    }
}
