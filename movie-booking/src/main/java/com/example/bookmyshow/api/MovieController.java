package com.example.bookmyshow.api;

import com.example.bookmyshow.services.MovieService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    public Response createMovie(@NonNull final String movieName) {
        return Response.ok(movieService.createMovie(movieName).getId()).build();
    }
}
