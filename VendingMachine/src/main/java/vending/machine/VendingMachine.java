package vending.machine;

import lombok.Getter;
import vending.machine.constants.VendingMachineStates;
import vending.machine.dispenser.CashDispenser;
import vending.machine.inventory.Inventory;

@Getter
public class VendingMachine implements IVendingMachine, Runnable {

    private CashDispenser cashDispenser;
    private CustomerConsole customerConsole;
    private Inventory inventory;

    private VendingMachineStates currentState;

    public VendingMachine() {
        this.cashDispenser = new CashDispenser();
        this.customerConsole = new CustomerConsole();
    }

    public void switchON() {
        currentState = VendingMachineStates.START;
    }

    public void switcOff() {
        currentState = VendingMachineStates.STOP;
    }

    public void run() {

        Session customerSession = null;
        while (true) {
            try {

                if (currentState.equals(VendingMachineStates.START)) {
                    customerConsole.readSelection();
                    currentState = VendingMachineStates.NEW_TRANSACTION;
                }

                if (currentState.equals(VendingMachineStates.NEW_TRANSACTION)) {
                    customerSession = new Session();
                    customerSession.performTransaction();
                }

            } catch (Exception ex) {

            }
        }

    }
}
