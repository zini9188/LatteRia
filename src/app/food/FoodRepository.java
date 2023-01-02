package app.food;

import app.food.category.Drink;
import app.food.category.Hamburger;
import app.food.category.Side;

public class FoodRepository {
    private Food[] foods = {
            new Hamburger(1, "새우버거", 3500, 500, false, 4500),
            new Hamburger(2, "치킨버거", 4000, 600, false, 5000),
            new Side(3, "감자튀김", 1000, 300, 1),
            new Side(4, "어니언링", 1000, 300, 1),
            new Drink(5, "코카콜라", 1000, 200, true),
            new Drink(6, "제로콜라", 1000, 0, true),
    };

    public Food[] getFoods() {
        return foods;
    }

    public Food findById(int foodId) {
        for (Food food : foods) {
            if (food.getId() == foodId) {
                return food;
            }
        }
        return null;
    }
}
