package app.discount;

import app.discount.condition.DiscountCondition;
import app.discount.policy.DiscountPolicy;

import java.util.ArrayList;

public class Discount {
    private ArrayList<DiscountCondition> discountConditions;

    public Discount(ArrayList<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public void checkAllDiscountCondition() {
        discountConditions.forEach(DiscountCondition::checkDiscountCondition);
    }

    public int discounted(int price) {
        final int[] discountedPrice = {price};
        discountConditions.stream()
                .filter(DiscountCondition::isSatisfied)
                .forEach(discountCondition -> discountedPrice[0] = discountCondition.applyDiscount(discountedPrice[0]));
        return discountedPrice[0];
    }
}
