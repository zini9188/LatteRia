package app.product.food;

import app.product.Product;

public class Drink extends Product {
    private boolean hasStraw;

    public Drink(int id, String name, int price, int kcal, boolean hasStraw) {
        super(id, name, price, kcal);
        this.hasStraw = hasStraw;
    }

    public boolean hasStraw() {
        return hasStraw;
    }

    public void setStraw(boolean hasStraw) {
        this.hasStraw = hasStraw;
    }
}
