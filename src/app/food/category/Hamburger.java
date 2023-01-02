package app.food.category;

import app.food.Food;

public class Hamburger extends Food {
    private boolean isBurgerSet;
    private int setPrice;

    public Hamburger(int id, String name, int price, int kcal, boolean isBurgerSet, int setPrice) {
        super(id, name, price, kcal);
        this.isBurgerSet = isBurgerSet;
        this.setPrice = setPrice;
    }

    public boolean isBurgerSet() {
        return isBurgerSet;
    }

    public void setBurgerSet(boolean burgerSet) {
        isBurgerSet = burgerSet;
    }

    public int getSetPrice() {
        return setPrice;
    }
}
