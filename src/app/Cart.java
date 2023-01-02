package app;

import app.food.Food;
import app.food.FoodRepository;
import app.food.category.BurgerSet;
import app.food.category.Drink;
import app.food.category.Hamburger;
import app.food.category.Side;

import java.util.Scanner;

public class Cart {
    private Food[] foods = new Food[0];
    private FoodRepository foodRepository;
    private Menu menu;
    private Scanner scanner = new Scanner(System.in);

    public Cart(FoodRepository foodRepository, Menu menu) {
        this.foodRepository = foodRepository;
        this.menu = menu;
    }

    public void addCart(int foodId) {
        Food food = foodRepository.findById(foodId);
        chooseOption(food);
        if (food instanceof Hamburger) {
            Hamburger hamburger = ((Hamburger) food);
            if (hamburger.isBurgerSet()) food = composeSet(hamburger);
        }


        Food[] newFoods = new Food[foods.length + 1];
        System.arraycopy(foods, 0, newFoods, 0, foods.length);
        newFoods[foods.length] = food;
        foods = newFoods;

        System.out.printf("[📣] %s를(을) 장바구니에 담았습니다.\n", food.getName());
    }

    private Food composeSet(Hamburger hamburger) {
        menu.printSides();

        int sideId = Integer.parseInt(scanner.nextLine());
        Side side = (Side) foodRepository.findById(sideId);
        chooseOption(side);

        System.out.println("음료를 골라주세요.");
        menu.printDrinks();

        int drinkId = Integer.parseInt(scanner.nextLine());
        Drink drink = (Drink) foodRepository.findById(drinkId);
        chooseOption(drink);

        String name = hamburger.getName() + "세트";
        int price = hamburger.getSetPrice();
        int kcal = hamburger.getKcal();

        return new BurgerSet(name, price, kcal, hamburger, side, drink);
    }

    private void chooseOption(Food food) {
        if (food instanceof Hamburger) {
            System.out.printf(
                    "단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n",
                    food.getPrice(), ((Hamburger) food).getSetPrice());
            String input = scanner.nextLine();
            if (input.equals("2")) ((Hamburger) food).setBurgerSet(true);
        } else if (food instanceof Side) {
            System.out.printf("케첩은 몇개가 필요하신가요?\n");
            String input = scanner.nextLine();
            if (input.equals("2")) ((Side) food).setKetchup(Integer.parseInt(input));
        } else if (food instanceof Drink) {
            System.out.printf("빨대가 필요하신가요? (1)_예 (2)_아니오\n");
            String input = scanner.nextLine();
            if (input.equals("1")) ((Drink) food).setStraw(true);
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

    protected void printCartDetail() {
        for (Food food : foods) {
            if (food instanceof Hamburger) {
                System.out.printf("  %-8s %6d원 (단품)\n", food.getName(), food.getPrice());
            } else if (food instanceof Side) {
                System.out.printf("  %-8s %6d원 (케첩 %d개)\n", food.getName(), food.getPrice(), ((Side) food).getKetchup());
            } else if (food instanceof Drink) {
                System.out.printf("  %-8s %6d원 (빨대 %s)\n", food.getName(), food.getPrice(), ((Drink) food).hasStraw() ? "있음" : "없음");
            } else if (food instanceof BurgerSet) {
                if (((BurgerSet) food).getDrink().hasStraw())
                    System.out.printf("  %s %6d원 (%s(케첩 %d개), %s(빨대 %s))\n",
                            food.getName(),
                            food.getPrice(),
                            ((BurgerSet) food).getSide().getName(),
                            ((BurgerSet) food).getSide().getKetchup(),
                            ((BurgerSet) food).getDrink().getName(),
                            ((BurgerSet) food).getDrink().hasStraw() ? "있음" : "없음"
                    );
            }
        }
    }

    protected int calculateTotalPrice() {
        int price = 0;
        for (Food food : foods) {
            price += food.getPrice();
        }
        return price;
    }
}
