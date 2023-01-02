package app.food;

public class Food {
    private int id;
    private String name;
    private int price;
    private int kcal;

    public Food(int id, String name, int price, int kcal) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.kcal = kcal;
    }

    public Food(String name, int price, int kcal) {
        this.name = name;
        this.price = price;
        this.kcal = kcal;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getKcal() {
        return kcal;
    }
}
