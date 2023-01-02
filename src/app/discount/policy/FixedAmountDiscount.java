package app.discount.policy;

public class FixedAmountDiscount implements DiscountPolicy {
    int amount;

    public FixedAmountDiscount(int amount) {
        this.amount = amount;
    }

    @Override
    public int calculateDiscountedPrice(int price) {
        return price - amount;
    }
}
