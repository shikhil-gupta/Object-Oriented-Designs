package vending.machine.dispenser;

import lombok.Getter;
import lombok.Setter;
import vending.machine.VendingMachine;
import vending.machine.exception.VendingMachineException;

@Getter
public class CashDispenser implements ICashDispenser {

    @Setter
    private VendingMachine vendingMachine;
    private Bill topLevelBill;

    public boolean canRefundAmount(int amountToBeRefunded) throws VendingMachineException {
        return topLevelBill.canRefundAmount(amountToBeRefunded);
    }

    public void refundAmount(int amountToBeRefunded) throws VendingMachineException {
        topLevelBill.refundAmount(amountToBeRefunded);
        vendingMachine.getCustomerConsole().returnAmount(amountToBeRefunded);
    }

    public void initialiseBills() {


    }
}
