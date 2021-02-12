package com.example.bookmyshow;

import com.example.bookmyshow.api.BookingController;
import com.example.bookmyshow.api.MovieController;
import com.example.bookmyshow.api.ShowController;
import com.example.bookmyshow.api.TheatreController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class BookmyshowApplication {

	public static void main(String[] args)
	{
		ConfigurableApplicationContext context = SpringApplication.run(BookmyshowApplication.class, args);
		TheatreController theatreController = context.getBean(TheatreController.class);
		BookingController bookingController = context.getBean(BookingController.class);
		ShowController showController = context.getBean(ShowController.class);
		MovieController movieController = context.getBean(MovieController.class);

		Response response = theatreController.createTheatre("Bellendur Cinema Hall");

		Response response1 = theatreController.createScreenInTheatre("Screen1", (String) response.getEntity());

		Response response2 = theatreController.createSeatInScreen(1,1,response1.getEntity().toString());

		Response movieResponse = movieController.createMovie("Fanna");

		Response showResponse = showController.createShow(movieResponse.getEntity().toString(), response1.getEntity().toString(), new Date(), 600, 100.0);

		Response bookingResponse = bookingController.createBooking("123", showResponse.getEntity().toString(), Arrays.asList(new String[]{response2.getEntity().toString()}));

		System.out.println("Hello");
	}
}
