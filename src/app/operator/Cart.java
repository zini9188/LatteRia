package app.operator;

import app.Reader;
import app.product.Product;
import app.product.ProductRepository;
import app.product.food.BurgerSet;
import app.product.food.Drink;
import app.product.food.Hamburger;
import app.product.food.Side;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> products = new ArrayList<>();
    private ProductRepository productRepository;
    private Menu menu;

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

        Product newProduct;
        if (product instanceof Hamburger) newProduct = new Hamburger((Hamburger) product);
        else if (product instanceof Side) newProduct = new Side((Side) product);
        else if (product instanceof Drink) newProduct = new Drink((Drink) product);
        else if (product instanceof BurgerSet) newProduct = product;
        else newProduct = null;

        products.add(newProduct);
    }

    private Product setCompose(Hamburger hamburger) {
        menu.printSide();
        int sideId = Reader.readInteger();
        Side side = (Side) productRepository.findById(sideId);
        Side newSide = new Side(side);
        chooseOption(newSide);

        menu.printDrink();
        int drinkId = Reader.readInteger();
        Drink drink = (Drink) productRepository.findById(drinkId);
        Drink newDrink = new Drink(drink);
        chooseOption(newDrink);

        String name = hamburger.getName() + "세트";
        int price = hamburger.getSetPrice() + drink.getPrice() + side.getPrice();
        int kcal = hamburger.getKcal() + drink.getKcal() + side.getKcal();

        return new BurgerSet(name, price, kcal, hamburger, newSide, newDrink);
    }

    private void chooseOption(Product product) {
        if (product instanceof Hamburger) {
            System.out.printf(
                    "단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n",
                    product.getPrice(), ((Hamburger) product).getSetPrice());
            ((Hamburger) product).setBurgerSet(Reader.readString().equals("2"));
        } else if (product instanceof Side) {
            System.out.print("케첩은 몇개가 필요하신가요?\n");
            ((Side) product).setKetchup(Reader.readInteger());
        } else if (product instanceof Drink) {
            System.out.print("빨대가 필요하신가요? (1)_예 (2)_아니오\n");
            ((Drink) product).setStraw(Reader.readString().equals("1"));
        }
    }

    protected int calculateTotalPrice() {
        return products.stream().mapToInt(Product::getPrice).sum();
    }

    public void printCart() {
        System.out.println("🧺 장바구니");
        System.out.println("-".repeat(60));
        printCartDetail();
        System.out.println("-".repeat(60));
        System.out.printf("합계 : %d원\n", calculateTotalPrice());
        System.out.println("이전으로 돌아가려면 엔터를 누르세요.");
        Reader.readString();
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
