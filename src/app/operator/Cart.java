package app.operator;

import app.product.Product;
import app.product.ProductRepository;
import app.product.food.BurgerSet;
import app.product.food.Drink;
import app.product.food.Hamburger;
import app.product.food.Side;

import java.util.Scanner;

public class Cart {

    private Product[] products = new Product[0];

    private ProductRepository productRepository;
    private Menu menu;

    private Scanner scanner = new Scanner(System.in);

    public Cart(ProductRepository productRepository, Menu menu) {
        this.productRepository = productRepository;
        this.menu = menu;
    }

    public void addCart(int itemId) {
        Product product = productRepository.findById(itemId);
        chooseOption(product);

        if (product instanceof Hamburger) {
            Hamburger hamburger = (Hamburger) product;
            if (hamburger.isBurgerSet()) product = setCompose(hamburger);
        }

        Product[] newProducts = new Product[products.length + 1];
        System.arraycopy(products, 0, newProducts, 0, products.length);
        newProducts[products.length] = product;
        products = newProducts;
    }

    private Product setCompose(Hamburger hamburger) {
        menu.printSide();
        int sideId = Integer.parseInt(scanner.nextLine());
        Side side = (Side) productRepository.findById(sideId);
        chooseOption(side);

        menu.printDrink();
        int drinkId = Integer.parseInt(scanner.nextLine());
        Drink drink = (Drink) productRepository.findById(drinkId);
        chooseOption(drink);

        String name = hamburger.getName() + "세트";
        int price = hamburger.getSetPrice() + drink.getPrice() + side.getPrice();
        int kcal = hamburger.getKcal() + drink.getKcal() + side.getKcal();

        return new BurgerSet(name, price, kcal, hamburger, side, drink);
    }

    private void chooseOption(Product product) {
        if (product instanceof Hamburger) {
            System.out.printf(
                    "단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n",
                    product.getPrice(), ((Hamburger) product).getSetPrice());
            String input = scanner.nextLine();
            if (input.equals("2")) ((Hamburger) product).setBurgerSet(true);
        } else if (product instanceof Side) {
            System.out.printf("케첩은 몇개가 필요하신가요?\n");
            String input = scanner.nextLine();
            if (input.equals("2")) ((Side) product).setKetchup(Integer.parseInt(input));
        } else if (product instanceof Drink) {
            System.out.printf("빨대가 필요하신가요? (1)_예 (2)_아니오\n");
            String input = scanner.nextLine();
            if (input.equals("1")) ((Drink) product).setStraw(true);
        }
    }

    public void printCart() {
        System.out.println("🧺 장바구니");
        System.out.println("-".repeat(60));

        printCartDetail();

        System.out.println("-".repeat(60));
        System.out.printf("합계 : %d원\n", calculateTotalPrice());

        System.out.println("이전으로 돌아가려면 엔터를 누르세요.");
        scanner.nextLine();

    }

    protected int calculateTotalPrice() {
        int sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    protected void printCartDetail() {
        for (Product product : products) {
            if (product instanceof Hamburger) {
                System.out.printf("  %-8s %6d원 (단품)\n", product.getName(), product.getPrice());
            } else if (product instanceof Side) {
                System.out.printf("  %-8s %6d원 (케첩 %d개)\n", product.getName(), product.getPrice(), ((Side) product).getKetchup());
            } else if (product instanceof Drink) {
                System.out.printf("  %-8s %6d원 (빨대 %s)\n", product.getName(), product.getPrice(), ((Drink) product).hasStraw() ? "있음" : "없음");
            } else if (product instanceof BurgerSet) {
                if (((BurgerSet) product).getDrink().hasStraw())
                    System.out.printf("  %s %6d원 (%s(케첩 %d개), %s(빨대 %s))\n",
                            product.getName(),
                            product.getPrice(),
                            ((BurgerSet) product).getSide().getName(),
                            ((BurgerSet) product).getSide().getKetchup(),
                            ((BurgerSet) product).getDrink().getName(),
                            ((BurgerSet) product).getDrink().hasStraw() ? "있음" : "없음"
                    );
            }
        }
    }
}
