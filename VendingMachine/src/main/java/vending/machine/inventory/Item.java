package vending.machine.inventory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    private Integer itemId;
    private String itemName;
    private Integer itemCost;
}
