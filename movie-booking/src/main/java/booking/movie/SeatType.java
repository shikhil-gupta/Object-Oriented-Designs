package booking.movie;

public enum SeatType
{
    Regular(0), Special(1);

    private int seatType;

    SeatType(int seatType) {
        this.seatType = seatType;
    }
}
