package app.discount.policy;

public class FixedRateDiscount implements DiscountPolicy {
    private int rate;

    public FixedRateDiscount(int rate) {
        this.rate = rate;
    }

    @Override
    public int calculateDiscountedPrice(int price) {
        return Math.max(price - (price * rate / 100), 0);
    }
}
