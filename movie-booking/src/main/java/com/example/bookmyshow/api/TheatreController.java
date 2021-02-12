package com.example.bookmyshow.api;

import com.example.bookmyshow.model.Screen;
import com.example.bookmyshow.model.Theatre;
import com.example.bookmyshow.services.TheatreService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
public class TheatreController
{
    @Autowired
    private TheatreService theatreService;

    public Response createTheatre(@NonNull final String theatreName) {
        return Response.ok(theatreService.createTheatre(theatreName).getId()).build();
    }

    public Response createScreenInTheatre(@NonNull final String screenName, @NonNull final String theatreId) {
        final Theatre theatre = theatreService.getTheatre(theatreId);
        return Response.ok(theatreService.createScreenInTheatre(screenName, theatre).getId()).build();
    }

    public Response createSeatInScreen(@NonNull final Integer rowNo, @NonNull final Integer seatNo, @NonNull final String screenId) {
        final Screen screen = theatreService.getScreen(screenId);
        return Response.ok(theatreService.createSeatInScreen(rowNo, seatNo, screen).getId()).build();
    }
}
