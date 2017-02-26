package example;

import core.ItemCreator;
import core.SingleLineConsumer;
import core.ParseUtil;
import core.Parser;
import example.model.Order;
import example.model.Warehouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static core.ParseUtil.*;

public class DroneDataLoader {
    public int rowCount, columnCount, droneCount, turns, maxPayload, productTypeCount, warehouseCount, orderCount;

    /** ID -> Weight */
    public HashMap<Integer, Integer> productWeights = new HashMap<>();
    public ArrayList<Order> orders = new ArrayList<>();
    public ArrayList<Warehouse> warehouses = new ArrayList<>();

    public DroneDataLoader(String path) {
        SingleLineConsumer getMetaData = new SingleLineConsumer(data -> {
                    rowCount = getInt(data, 0);
                    columnCount = getInt(data, 1);
                    droneCount = getInt(data, 2);
                    turns = getInt(data, 3);
                    maxPayload = getInt(data, 4);
                });

        SingleLineConsumer getProductTypes = new SingleLineConsumer(data -> productTypeCount = getInt(data, 0));
        SingleLineConsumer getProductWeights = new SingleLineConsumer(data -> productWeights.putAll(asIndexedMap(data)));
        SingleLineConsumer getWarehouseCount = new SingleLineConsumer(data -> warehouseCount = getInt(data, 0));
        WarehouseCreator getWarehouses = new WarehouseCreator();
        SingleLineConsumer getOrderCount = new SingleLineConsumer(data -> orderCount = getInt(data, 0));

        OrderCreator getOrders = new OrderCreator();

        Parser parser = new Parser(path, getMetaData, getProductTypes, getProductWeights, getWarehouseCount,
                getWarehouses, getOrderCount, getOrders);

        parser.parse();
    }

    private class WarehouseCreator extends ItemCreator<Warehouse> {
        @Override
        protected int getTotalItemAmount() {
            return warehouseCount;
        }

        @Override
        public void parseData(String[] data) {
            if (hasItem()) {
                getCurrentItem().getStoredProducts().putAll(asIndexedMap(data));
                setCurrentItem(null);
            } else {
                setCurrentItem(new Warehouse(new Point(getInt(data, 0), getInt(data, 1))));
                warehouses.add(getCurrentItem());
            }
        }
    }

    private class OrderCreator extends ItemCreator<Order> {
        @Override
        protected int getTotalItemAmount() {
            return orderCount;
        }

        public void parseData(String[] data) {
            if (hasItem()) {
                if (getCurrentItem().getTotalProductAmount() == Order.AMOUNT_NOT_SPECIFIED)
                    getCurrentItem().setTotalProductAmount(getInt(data, 0));
                else {
                    Arrays.stream(data).map(ParseUtil::asInt).forEach(getCurrentItem()::addProduct);
                    setCurrentItem(null);
                }
            } else {
                setCurrentItem(new Order(new Point(getInt(data, 0), getInt(data, 1))));
                orders.add(getCurrentItem());
            }
        }
    }
}