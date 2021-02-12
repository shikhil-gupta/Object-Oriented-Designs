package com.example.bookmyshow.providers;

import com.example.bookmyshow.model.Seat;
import com.example.bookmyshow.model.Show;
import com.example.bookmyshow.model.SeatLock;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemorySeatLockProvider implements ISeatLockProvider {

    private final Integer lockTimeoutInSeconds;
    private final Map<Show, Map<Seat, SeatLock>> locks;

    public InMemorySeatLockProvider() {
        this.locks = new HashMap<>();
        this.lockTimeoutInSeconds = 200;
    }

    @Override
    synchronized public void lockSeats(@NonNull final Show show, @NonNull final List<Seat> seats,
                                       @NonNull final String user) {
        for (Seat seat : seats) {
            if (isSeatLocked(show, seat)) {
                //throw new SeatTemporaryUnavailableException();
            }
        }

        for (Seat seat : seats) {
            lockSeat(show, seat, user, lockTimeoutInSeconds);
        }
    }

    @Override
    public void unlockSeats(@NonNull final Show show, @NonNull final List<Seat> seats, @NonNull final String user) {
        for (Seat seat: seats) {
            if (validateLock(show, seat, user)) {
                unlockSeat(show, seat);
            }
        }
    }

    @Override
    public boolean validateLock(@NonNull final Show show, @NonNull final Seat seat, @NonNull final String user) {
        return isSeatLocked(show, seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(@NonNull final Show show) {
        if (!locks.containsKey(show)) {
            return new ArrayList<>();
        }
        final List<Seat> lockedSeats = new ArrayList<>();

        for (Seat seat : locks.get(show).keySet()) {
            if (isSeatLocked(show, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private void unlockSeat(final Show show, final Seat seat) {
        if (!locks.containsKey(show)) {
            return;
        }
        locks.get(show).remove(seat);
    }

    private void lockSeat(final Show show, final Seat seat, final String user, final Integer timeoutInSeconds) {
        if (!locks.containsKey(show)) {
            locks.put(show, new HashMap<>());
        }
        final SeatLock lock = new SeatLock(seat, show, timeoutInSeconds, new Date(), user);
        locks.get(show).put(seat, lock);
    }

    private boolean isSeatLocked(final Show show, final Seat seat) {
        return locks.containsKey(show) && locks.get(show).containsKey(seat) && !locks.get(show).get(seat).isLockExpired();
    }
}