package vending.machine.dispenser;

import vending.machine.constants.BillsValue;
import vending.machine.exception.VendingMachineException;

public class Bill {

    private BillsValue billsValue;

    private int totalBills;

    private Bill bill;

    public Bill(BillsValue billsValue) {

        this.billsValue=billsValue;
        this.totalBills=0;
    }

    public boolean canRefundAmount(int refundAmoumt) throws VendingMachineException {

        int remainder = refundAmoumt % billsValue.getValue();
        int count = refundAmoumt / billsValue.getValue();

        if(count <= totalBills) {
            if(remainder == 0) {
                return true;
            } else  {
                return bill.canRefundAmount(remainder);
            }
        }
        return false;
    }

    public void refundAmount(int refundAmoumt) throws VendingMachineException {
        if(canRefundAmount(refundAmoumt)) {


        } else {
            throw new VendingMachineException("Insufficent funds");
        }
    }
}
