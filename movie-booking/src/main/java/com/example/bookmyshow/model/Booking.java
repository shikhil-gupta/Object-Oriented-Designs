package com.example.bookmyshow.model;

import lombok.Getter;
import lombok.NonNull;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class Booking
{
    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final String user;
    private BookingStatus bookingStatus;
    private final Timestamp timestamp;
    private final Double totalCharges;

    public Booking(@NonNull final String id, @NonNull final Show show, @NonNull final String user,
                   @NonNull final List<Seat> seatsBooked, @NonNull final Double totalCharges) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.totalCharges = totalCharges;
        this.bookingStatus = BookingStatus.Created;
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.Confirmed;
    }

    public void confirmBooking() {
        if (this.bookingStatus != BookingStatus.Created) {
            //throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public void expireBooking() {
        if (this.bookingStatus != BookingStatus.Created) {
            //throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Expired;
    }
}
