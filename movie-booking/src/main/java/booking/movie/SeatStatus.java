package booking.movie;

public enum SeatStatus {
    AVAILABLE("available"), OCCUPIED("occupied");

    private String seatStatus;

    SeatStatus(String seatStatus) {
        this.seatStatus=seatStatus;
    }

    public String getSeatStatus() {
        return seatStatus;
    }
}
