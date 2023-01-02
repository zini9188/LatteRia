package app;

import app.food.FoodRepository;

import java.util.Scanner;

public class OrderApp {
    private FoodRepository foodRepository;
    private Menu menu;
    private Cart cart;
    private Order order;

    public OrderApp(FoodRepository foodRepository, Menu menu, Cart cart, Order order) {
        this.foodRepository = foodRepository;
        this.menu = menu;
        this.cart = cart;
        this.order = order;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\uD83C\uDF54 LatteLia Order Service");

        while (true) {
            menu.printMenu();
            String input = scanner.nextLine();
            if (input.equals("+")) {
                order.makeOrder();
                break;
            } else {
                int orderId = Integer.parseInt(input);
                if (orderId >= 1 && orderId <= foodRepository.getFoods().length) {
                    cart.addCart(orderId);
                } else if (orderId == 0) {
                    cart.printCart();
                }
            }

        }
    }

}
