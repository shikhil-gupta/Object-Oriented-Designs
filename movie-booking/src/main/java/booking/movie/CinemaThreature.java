package booking.movie;

import java.util.HashMap;
import java.util.List;

public class CinemaThreature {

    private Integer cinemaId;
    private List<Screen> screens;
    private Integer floorno;
    /**
     * Keep refreshing this map from datbase to update showsid to Screen mapping.
     */
    private static HashMap<Integer, List<Screen>> showIdtoScreenMap;


    public void bookshow(int showid, List<Integer> seatsToBeBooked) {
        //find the any screen which has available seats
        List<Screen> screens = showIdtoScreenMap.get(showid);
        for (Screen screen : screens) {
            if (screen.hasSeatsAvailable(seatsToBeBooked)) {
                screen.bookSeat(seatsToBeBooked);
            }
        }
    }

}
