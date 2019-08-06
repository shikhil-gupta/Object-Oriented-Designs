package com.onlinetaxi.booking.demand;

import com.onlinetaxi.booking.User;
import com.onlinetaxi.booking.constant.Category;
import com.onlinetaxi.booking.constant.PaymentMode;
import com.onlinetaxi.booking.pojo.Point;

import java.util.Optional;

public class CabRequest {

    private User requestedUser;

    private Category requestedCategory;

    private Optional<Integer> requestedSeats;

    private Point requestedStartingLocation;

    private Point requestedEndLocation;

    private double estimatedFareShown;

    private PaymentMode paymentModeSelected;

}
