package co.example.beverage.models;

/**
 * Created by Ussama Iftikhar on 28-Jan-2021.
 * Email iusama46@gmail.com
 * Email iusama466@gmail.com
 * Github https://github.com/iusama46
 */

public class Item {
    private int id;
    private String name;
    private String description;
    private String price;
    private int image;

    public Item() {
    }

    public Item(int id, String name, String description, String price, int image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }



    public Item(String name, String description, String price, int image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Item(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

