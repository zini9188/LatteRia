package app;

import app.food.Food;
import app.food.category.Drink;
import app.food.category.Hamburger;
import app.food.category.Side;

public class Menu {
    private Food[] foods;

    public Menu(Food[] foods) {
        this.foods = foods;
    }

    public void printMenu() {
        System.out.println("[\uD83D\uDD3B] 메뉴");
        System.out.println("-".repeat(60));

        printHamburger();
        printSides();
        printDrinks();

        System.out.println("\uD83E\uDDFA (0) 장바구니");
        System.out.println("\uD83D\uDCE6 (+) 주문하기");
        System.out.println("-".repeat(60));
    }

    protected void printDrinks() {
        System.out.println("\uD83E\uDD64 음료\n");
        for (Food food : foods) {
            if (food instanceof Drink) {
                printEachMenu(food);
            }
        }
        System.out.println();
    }

    protected void printSides() {
        System.out.println("\uD83C\uDF5F 사이드\n");
        for (Food food : foods) {
            if (food instanceof Side) {
                printEachMenu(food);
            }
        }
        System.out.println();
    }

    protected void printHamburger() {
        System.out.println("\uD83C\uDF54 햄버거\n");
        for (Food food : foods) {
            if (food instanceof Hamburger) {
                printEachMenu(food);
            }
        }
        System.out.println();
    }

    private static void printEachMenu(Food food) {
        System.out.printf(
                "   (%d) %s %5dKcal %5d원\n",
                food.getId(), food.getName(), food.getPrice(), food.getKcal());
    }
}
