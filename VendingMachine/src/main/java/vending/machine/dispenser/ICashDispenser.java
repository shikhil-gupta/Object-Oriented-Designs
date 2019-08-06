package vending.machine.dispenser;

import vending.machine.exception.VendingMachineException;

public interface ICashDispenser {

    boolean canRefundAmount(int amountToBeRefunded) throws VendingMachineException;

    void refundAmount(int amountToBeRefunded) throws VendingMachineException;


}
