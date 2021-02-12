package com.example.bookmyshow.services;

import com.example.bookmyshow.model.Movie;
import com.example.bookmyshow.model.Screen;
import com.example.bookmyshow.model.Show;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShowService
{
    private final Map<String, Show> showIDsToShowMap;

    public ShowService()
    {
        this.showIDsToShowMap = new HashMap<>();
    }

    public Show getShow(@NonNull final String showID)
    {
        if(!showIDsToShowMap.containsKey(showID))
        {
            //throw notFoundException
        }

        return showIDsToShowMap.get(showID);
    }

    public Show createShow(@NonNull final Movie movie, @NonNull final Screen screen, @NonNull final Date startTime,
                           @NonNull final Integer durationInSeconds,@NonNull final Double showCharges)
    {
        if (!checkIfShowCreationAllowed(screen, startTime, durationInSeconds)) {
            //throw new ScreenAlreadyOccupiedException();
        }
        String showID = UUID.randomUUID().toString();
        final Show show = new Show(showID, movie, screen, startTime, durationInSeconds, showCharges);
        this.showIDsToShowMap.put(showID, show);
        return show;
    }

    public List<Show> getShowsForScreen(final Screen screen) {
        final List<Show> response = new ArrayList<>();
        for (Show show : showIDsToShowMap.values()) {
            if (show.getScreen().equals(screen)) {
                response.add(show);
            }
        }
        return response;
    }

    private boolean checkIfShowCreationAllowed(final Screen screen, final Date startTime, final Integer durationInSeconds) {
        // TODO: Implement this. This method will return whether the screen is free at a particular time for
        // specific duration. This function will be helpful in checking whether the show can be scheduled in that slot
        // or not.
        return true;
    }

}
