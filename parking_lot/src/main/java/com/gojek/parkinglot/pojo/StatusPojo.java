package com.gojek.parkinglot.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatusPojo {
    private int slotNo;
    private String registrationNo;
    private String Color;
}