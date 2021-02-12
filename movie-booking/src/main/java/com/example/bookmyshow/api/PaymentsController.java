package com.example.bookmyshow.api;

import com.example.bookmyshow.services.BookingService;
import com.example.bookmyshow.services.PaymentsService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
public class PaymentsController
{
    @Autowired
    private PaymentsService paymentsService;

    @Autowired
    private BookingService bookingService;

    public Response paymentFailed(@NonNull final String bookingId, @NonNull final String user) {
        paymentsService.processPaymentFailed(bookingService.getBooking(bookingId), user);
        return Response.ok().build();
    }

    public Response paymentSuccess(@NonNull final  String bookingId, @NonNull final String user) {
        bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
        return Response.ok().build();
    }
}
