package vending.machine.constants;


import lombok.Getter;

@Getter
public enum VendingMachineStates {
    START("start", "VendingMachine Starting"), STOP("stop", "VendingMachine Stopping"), WAITING("waiting", "VendingMachine waiting for user input"),
    NEW_TRANSACTION("new transaction", "VendingMachine starting a new transactions");

    private String status;

    private String message;

    VendingMachineStates(String status, String message) {

        this.status = status;
        this.message = message;
    }

}
