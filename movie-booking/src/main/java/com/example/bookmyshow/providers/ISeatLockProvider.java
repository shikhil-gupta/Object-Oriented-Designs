package com.example.bookmyshow.providers;

import com.example.bookmyshow.model.Seat;
import com.example.bookmyshow.model.Show;

import java.util.List;

public interface ISeatLockProvider
{
    void lockSeats(Show show, List<Seat> seat, String user);
    void unlockSeats(Show show, List<Seat> seat, String user);
    boolean validateLock(Show show, Seat seat, String user);

    List<Seat> getLockedSeats(Show show);
}
