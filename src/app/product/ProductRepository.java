package app.product;

import app.product.food.Drink;
import app.product.food.Hamburger;
import app.product.food.Side;

import java.util.ArrayList;

public class ProductRepository {
    private final ArrayList<Product> products = new ArrayList<>() {
        {
            add(new Hamburger(1, "새우버거", 3500, 500, false, 4500));
            add(new Hamburger(2, "치킨버거", 4000, 600, false, 5000));
            add(new Side(3, "감자튀김", 1000, 300, 1));
            add(new Side(4, "어니언링", 1000, 300, 1));
            add(new Drink(5, "코카콜라", 1000, 200, false));
            add(new Drink(6, "제로콜라", 1000, 0, false));
        }
    };

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Product findById(int productId) {
        return products.stream().filter(product -> product.getId() == productId).findFirst().orElse(null);
    }
}
