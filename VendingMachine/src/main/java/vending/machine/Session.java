package vending.machine;

import vending.machine.exception.VendingMachineException;
import vending.machine.inventory.Item;

public class Session {


    private String state;

    private String itemSelected;

    private String moneyTaken;

    private VendingMachine vendingMachine;

    public Session() {

    }

    public void performTransaction() throws VendingMachineException {

        if(state == "user selection waiting") {
            itemSelected = vendingMachine.getCustomerConsole().readSelection();
            state = "waiting for money";
        }

        if(state == "waiting for money") {
            moneyTaken = vendingMachine.getCustomerConsole().takeMoney();
            state = "item and money validation";

        }

        if(state == "item and money validation") {

            vendingMachine.getCashDispenser().canRefundAmount(Integer.valueOf(moneyTaken));
            vendingMachine.getInventory().validate(Integer.valueOf(itemSelected));
            state = "item and Money Validated";
        }

        if(state == "item and Money Validated") {
            Item item = vendingMachine.getInventory().getItem(0);
            vendingMachine.getCustomerConsole().dispense(item);
            vendingMachine.getCashDispenser().refundAmount(0);
        }
    }


}
