package core.example.model;

import java.awt.*;
import java.util.HashMap;

public class Order {

    public static final int AMOUNT_NOT_SPECIFIED = -1;

    private Point destination;
    private int totalProductAmount = AMOUNT_NOT_SPECIFIED;

    /** ProductType(ID) -> true */
    private HashMap<Integer, Integer> items = new HashMap<>();

    public Order(Point destination) {
        this.destination = destination;
    }

    public void addProduct(int id) {
        if (getItemCount(id) == -1)
            items.put(id, 1);
        else
            items.put(id, items.get(id) + 1);
    }

    public int getItemCount(int productType) {
        return items.get(productType) != null ? items.get(productType) : -1;
    }

    public void setTotalProductAmount(int amount) {
        this.totalProductAmount = amount;
    }

    public int getTotalProductAmount() {
        return totalProductAmount;
    }
}
