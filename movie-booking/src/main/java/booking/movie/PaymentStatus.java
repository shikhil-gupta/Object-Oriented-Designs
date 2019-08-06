package booking.movie;

public enum PaymentStatus {
    SETTLED("settled"), PENDING("pending");

    private String paymentStatus;

    PaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}
