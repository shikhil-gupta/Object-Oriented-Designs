package vending.machine.exception;

public class VendingMachineException extends Exception {
    public VendingMachineException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public VendingMachineException(final String message) {
        super(message);
    }
}
