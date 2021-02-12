package com.example.bookmyshow.services;

import com.example.bookmyshow.model.Seat;
import com.example.bookmyshow.model.Show;
import com.example.bookmyshow.providers.ISeatLockProvider;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatAvailabilityService
{
    @Autowired
    private final BookingService bookingService;

    @Autowired
    private final ISeatLockProvider seatLockProvider;

    public SeatAvailabilityService(@NonNull final BookingService bookingService,
                                   @NonNull final ISeatLockProvider seatLockProvider) {
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;
    }

    public List<Seat> getAvailableSeats(@NonNull final Show show) {
        final List<Seat> allSeats = show.getScreen().getSeats();
        final List<Seat> unavailableSeats = getUnavailableSeats(show);

        final List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(unavailableSeats);
        return availableSeats;
    }

    private List<Seat> getUnavailableSeats(@NonNull final Show show) {
        final List<Seat> unavailableSeats = bookingService.getBookedSeats(show);
        unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeats;
    }

}
