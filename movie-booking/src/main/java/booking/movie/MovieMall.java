package booking.movie;

import java.util.List;

public class MovieMall {

    private Integer mallid;
    private Address mallAddress;
    private String mallname;
    private List<CinemaThreature> threatureList;

    public MovieMall() {

    }

    public void bookShow(int userid,int cinemaId, int showid, List<Integer> seats) {

        CinemaThreature cinemaThreature = threatureList.get(cinemaId);
        cinemaThreature.bookshow(showid,seats);
        Booking booking = new Booking();
        booking.setUserId(userid);
        booking.setShowid(showid);
        booking.setSeatsChoosen(seats);
        Integer bookingid = booking.save();
        //send to payment gatway using booking id and booking amount
    }

}
