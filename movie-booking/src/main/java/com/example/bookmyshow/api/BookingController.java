package com.example.bookmyshow.api;

import com.example.bookmyshow.model.Seat;
import com.example.bookmyshow.model.Show;
import com.example.bookmyshow.services.BookingService;
import com.example.bookmyshow.services.ShowService;
import com.example.bookmyshow.services.TheatreService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookingController
{
    @Autowired
    private ShowService showService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TheatreService theatreService;

    public Response createBooking(@NonNull final String userId, @NonNull final String showId, @NonNull final List<String> seatsIds)
    {
        final Show show = showService.getShow(showId);
        final List<Seat> seats = seatsIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return Response.ok(bookingService.createBooking(userId, show, seats).getId()).build();
    }
}
