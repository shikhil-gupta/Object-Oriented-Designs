package booking.movie;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Booking {

    private Integer bookingId;
    private Integer userId;
    private Integer totalSeatsBooked;
    private Integer showid;
    private List<Integer> seatsChoosen;
    private Integer cinemaThreatureid;
    private Screen screen;
    private Integer bookingamount;
    private PaymentStatus paymentStatus;
    private BookingStatus bookingStatus;


    public void makePayment(Payment payment) {

        //pass booking id and totalAmount
        payment.pay();
    }

    public Integer save() {
        //save to Db
        return bookingId;
    }
}
