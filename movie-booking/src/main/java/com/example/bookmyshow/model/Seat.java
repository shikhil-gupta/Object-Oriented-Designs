package com.example.bookmyshow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Seat
{
    private final String id;
    private final int rowNo;
    private final int seatNo;
}
