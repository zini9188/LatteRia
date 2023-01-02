package app.food.category;

import app.food.Food;

public class Drink extends Food {
    private boolean straw;

    public Drink(int id, String name, int price, int kcal, boolean straw) {
        super(id, name, price, kcal);
        this.straw = straw;
    }

    public boolean hasStraw() {
        return straw;
    }

    public void setStraw(boolean straw) {
        this.straw = straw;
    }
}
