package com.example.bookmyshow.api;

import com.example.bookmyshow.model.Movie;
import com.example.bookmyshow.model.Screen;
import com.example.bookmyshow.model.Seat;
import com.example.bookmyshow.model.Show;
import com.example.bookmyshow.services.MovieService;
import com.example.bookmyshow.services.SeatAvailabilityService;
import com.example.bookmyshow.services.ShowService;
import com.example.bookmyshow.services.TheatreService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShowController
{
    @Autowired
    private SeatAvailabilityService seatAvailabilityService;

    @Autowired
    private ShowService showService;

    @Autowired
    private TheatreService theatreService;

    @Autowired
    private MovieService movieService;

    public Response createShow(@NonNull final String movieId, @NonNull final String screenId, @NonNull final Date startTime,
                               @NonNull final Integer durationInSeconds, @NonNull final Double showCharges) {
        final Screen screen = theatreService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);
        return Response.ok(showService.createShow(movie, screen, startTime, durationInSeconds, showCharges).getId()).build();
    }

    public Response getAvailableSeats(@NonNull final String showId) {
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return Response.ok(availableSeats.stream().map(Seat::getId).collect(Collectors.toList())).build();
    }
}
