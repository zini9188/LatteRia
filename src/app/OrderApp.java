package app;

import app.discount.Discount;
import app.discount.condition.DiscountCondition;
import app.discount.policy.FixedAmountDiscount;
import app.discount.policy.FixedRateDiscount;
import app.operator.Cart;
import app.operator.Menu;
import app.operator.Order;
import app.product.ProductRepository;

import java.util.Scanner;

public class OrderApp {

    private ProductRepository productRepository;
    private Menu menu;
    private Cart cart;
    private Order order;

    public OrderApp(ProductRepository productRepository, Menu menu, Cart cart, Order order) {
        this.productRepository = productRepository;
        this.menu = menu;
        this.cart = cart;
        this.order = order;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu.printMenu();
            String input = scanner.nextLine();
            if (input.equals("+")) {
                order.makeOrder();
                break;
            } else {
                int menuId = Integer.parseInt(input);
                if (menuId >= 1 && menuId <= productRepository.getProducts().length) {
                    cart.addCart(menuId);
                } else if (menuId == 0) {
                    cart.printCart();
                }
            }

        }


    }
}
