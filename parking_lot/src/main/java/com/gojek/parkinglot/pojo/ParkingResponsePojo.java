package com.gojek.parkinglot.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingResponsePojo {

    private Integer floorLevel;
    private Integer slotNo;
    private Integer charges;
    private Integer buildingNo;
    private int errorCode;
}