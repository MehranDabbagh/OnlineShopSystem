package entity;

public class Product extends BaseEntity {
    private String name;
    private int price;
    private Category category;
    private int stock;
    public Product() {
    }

    public Product(String name, int price,int stock, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock=stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
