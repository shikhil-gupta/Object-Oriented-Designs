package com.gojek.parkinglot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingTicket {
    Integer slotNo;
    Integer floorLevel;
    Integer charges;
    String registationNo;
    String color;
    String timeStamp;

}
