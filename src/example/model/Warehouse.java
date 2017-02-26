package example.model;

import java.awt.*;
import java.util.HashMap;

public class Warehouse {

    private Point location;
    /** Product ID -> # stored of that product */
    private HashMap<Integer, Integer> storedProducts = new HashMap<>();

    public Warehouse(Point location) {
        this.location = location;
    }

    public void addProductInfo(int id, int count) {
        storedProducts.put(id, count);
    }

    public HashMap<Integer, Integer> getStoredProducts() {
        return storedProducts;
    }
}
