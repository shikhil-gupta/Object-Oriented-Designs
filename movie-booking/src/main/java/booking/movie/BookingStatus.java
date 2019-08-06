package booking.movie;

public enum BookingStatus {
    SUCCESS("success"), PENDINGPAYEMNT("payment-pending");

    private String bookingStatus;

    BookingStatus(String bookingStatus) {
        this.bookingStatus= bookingStatus;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }
}
