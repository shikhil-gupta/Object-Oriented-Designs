package vending.machine;

import vending.machine.inventory.Item;

public class CustomerConsole {

    private IVendingMachine vendingMachine;

    public void start() {

    }

    public String readSelection() {
        return "2";
    }

    public String takeMoney() {
        return "10";
    }

    public void dispense(Item item) {
        System.out.println(item);
    }

    public void returnAmount(int amount) {

    }

}
