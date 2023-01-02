package app.discount.discountPolicy;

public class FixedRateDiscountPolicy implements DiscountPolicy {
    int discountRate;

    public FixedRateDiscountPolicy(int discountPrice) {
        this.discountRate = discountPrice;
    }

    @Override
    public int calculateDiscountedPrice(int price) {
        return Math.max(price - (price * discountRate / 100), 0);
    }
}
