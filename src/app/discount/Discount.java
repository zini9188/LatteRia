package app.discount;

import app.discount.condition.DiscountCondition;
import app.discount.policy.DiscountPolicy;

public class Discount {
    DiscountCondition[] discountConditions;

    public Discount(DiscountCondition[] discountConditions) {
        this.discountConditions = discountConditions;
    }

    public void checkAllDiscountCondition(){
        for(DiscountCondition discountCondition : discountConditions){
            discountCondition.checkDiscountCondition();
        }
    }

    public int discounted(int price){
        int discountedPrice = price;
        for(DiscountCondition discountCondition : discountConditions){
            if(discountCondition.isSatisfied()) discountedPrice = discountCondition.applyDiscount(discountedPrice);
        }
        return discountedPrice;
    }
}
