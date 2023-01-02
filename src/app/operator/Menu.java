package app.operator;

import app.product.Product;
import app.product.food.Drink;
import app.product.food.Hamburger;
import app.product.food.Side;

import java.io.PrintStream;

public class Menu {
    private Product[] products;

    public Menu(Product[] products) {
        this.products = products;
    }

    public void printMenu(){
        System.out.println("[\uD83D\uDD3B] 메뉴");
        System.out.println("-".repeat(60));

        printHamburger();
        printSide();
        printDrink();

        System.out.println("\uD83E\uDDFA (0) 장바구니");
        System.out.println("\uD83D\uDCE6 (+) 주문하기");
        System.out.println("-".repeat(60));
    }

    protected void printHamburger() {
        System.out.println("\uD83C\uDF54 햄버거\n");
        for(Product product : products){
            if(product instanceof Hamburger){
                printEachMenu(product);
            }
        }
        System.out.println();
    }

    protected void printSide() {
        System.out.println("\uD83C\uDF5F 사이드\n");
        for(Product product : products){
            if(product instanceof Side){
                printEachMenu(product);
            }
        }
        System.out.println();
    }

    protected void printDrink() {
        System.out.println("\uD83E\uDD64 음료\n");
        for(Product product : products){
            if(product instanceof Drink){
                printEachMenu(product);
            }
        }
        System.out.println();
    }

    private void printEachMenu(Product product) {
        System.out.printf(
                "   (%d) %s %5dKcal %5d원\n",
                product.getId(), product.getName(), product.getPrice(), product.getKcal());
    }
}
