package app.product.food;

import app.product.Product;

public class Hamburger extends Product {
    private boolean isBurgerSet;
    private int setPrice;

    public Hamburger(int id, String name, int price, int kcal, boolean isBurgerSet, int setPrice) {
        super(id, name, price, kcal);
        this.isBurgerSet = isBurgerSet;
        this.setPrice = setPrice;
    }

    public int getSetPrice() {
        return setPrice;
    }

    public boolean isBurgerSet() {
        return isBurgerSet;
    }

    public void setBurgerSet(boolean burgerSet) {
        isBurgerSet = burgerSet;
    }
}
