package app.discount.condition;

public interface DiscountCondition {
    void checkDiscountCondition();
    boolean isSatisfied();
    int applyDiscount(int price);
}
