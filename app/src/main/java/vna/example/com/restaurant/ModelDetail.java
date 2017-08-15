package vna.example.com.restaurant;

/**
 * Created by Google       Company on 14/08/2017.
 */

public class ModelDetail {

    String name;
    String price;

    public ModelDetail(String name, String price) {
        this.name = name;

  this.price = price;
    }
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
