package app.food.category;

import app.food.Food;

public class BurgerSet extends Food {
    private Hamburger hamburger;
    private Side side;
    private Drink drink;

    public BurgerSet(String name, int price, int kcal, Hamburger hamburger, Side side, Drink drink) {
        super(name, price, kcal);
        this.hamburger = hamburger;
        this.side = side;
        this.drink = drink;
    }

    public Side getSide() {
        return side;
    }

    public Drink getDrink() {
        return drink;
    }
}
