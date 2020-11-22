package booking.movie;
import java.util.List;

public class Screen {

    private Integer screenId;
    private String screenName;
    private List<Seat> seatList;

    //sorted by start time.
    private List<Show> listOfShow;

    public synchronized void bookSeat(List<Integer> seats) {
        //block seats here
    }


    public boolean hasSeatsAvailable(List<Integer> seats) {

        Boolean isAvailable = true;
        for (Integer seatid: seats) {
            Seat seat = seatList.get(seatid);
            if(seat.getSeatStatus().equals(SeatStatus.AVAILABLE)) {
                isAvailable = Boolean.TRUE;
            }

        }
        return isAvailable;
    }
}
