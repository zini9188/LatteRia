package app;

import app.discount.Discount;
import app.discount.discountCondition.CouponDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;
import app.food.FoodRepository;

public class AppConfigurer {
    private Cart cart;

    public FoodRepository foodRepository() {
        return new FoodRepository();
    }

    public Menu menu() {
        return new Menu(foodRepository().getFoods());
    }

    public Cart cart() {
        if (cart == null) cart = new Cart(foodRepository(), menu());
        return cart;
    }

    public Order order() {
        return new Order(cart(), discount());
    }

    public Discount discount() {
        return new Discount(
                new DiscountCondition[]{
                        new CouponDiscountCondition(new FixedRateDiscountPolicy(10)),
                        new KidDiscountCondition(new FixedAmountDiscountPolicy(1000)),
                }
        );
    }
}
