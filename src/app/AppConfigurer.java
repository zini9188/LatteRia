package app;

import app.discount.Discount;
import app.discount.condition.CouponDiscountCondition;
import app.discount.condition.DiscountCondition;
import app.discount.condition.KidDiscountCondition;
import app.discount.policy.FixedAmountDiscount;
import app.discount.policy.FixedRateDiscount;
import app.operator.Cart;
import app.operator.Menu;
import app.operator.Order;
import app.product.ProductRepository;

public class AppConfigurer {
    private Cart cart;

    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    public Menu menu() {
        return new Menu(productRepository().getProducts());
    }

    public Order order() {
        return new Order(cart(), discount());
    }

    public Discount discount() {
        return new Discount(new DiscountCondition[]{
                new CouponDiscountCondition(new FixedAmountDiscount(1000)),
                new KidDiscountCondition(new FixedRateDiscount(10))
        });
    }

    public Cart cart() {
        if(cart == null) cart = new Cart(productRepository(), menu());
        return cart;
    }
}
