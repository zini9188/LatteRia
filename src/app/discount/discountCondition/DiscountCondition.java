package app.discount.discountCondition;

public interface DiscountCondition {
    boolean isSatisfied();

    int applyDiscount(int price);

    void checkDiscountCondition();
}
