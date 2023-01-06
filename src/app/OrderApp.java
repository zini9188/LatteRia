package app;

import app.operator.Cart;
import app.operator.Menu;
import app.operator.Order;
import app.product.ProductRepository;

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
        while (true) {
            menu.printMenu();
            String input = Reader.readString();
            if (input.equals("+")) {
                order.makeOrder();
                break;
            }
            addCart(input);
        }
    }

    private void addCart(String input) {
        try {
            int menuId = Integer.parseInt(input);
            if (menuId >= 1 && menuId <= productRepository.getProducts().size()) {
                cart.addCart(menuId);
            } else if (menuId == 0) {
                cart.printCart();
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Input Error");
        }
    }
}
