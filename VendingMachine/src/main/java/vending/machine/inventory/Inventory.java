package vending.machine.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {

    List<Item> itemList;
    HashMap<Integer, Integer> itemIdtoItemCount;

    public Inventory() {
        itemList = new ArrayList<Item>();
        itemIdtoItemCount = new HashMap<Integer, Integer>();
    }

    public void validate(Integer position) {

    }

    public Item getItem(Integer position){
        return itemList.get(position);
    }
}
