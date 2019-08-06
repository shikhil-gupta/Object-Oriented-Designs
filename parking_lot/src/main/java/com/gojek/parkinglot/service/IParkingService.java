package com.gojek.parkinglot.service;

import com.gojek.parkinglot.ParkingTicket;
import com.gojek.parkinglot.constants.Color;
import com.gojek.parkinglot.exception.ParkingException;
import com.gojek.parkinglot.model.Vehicle;

import java.util.List;

public interface IParkingService {

    /**
     * This function can be used to create single story parking space
     * @param capacity
     * @throws ParkingException
     */

    void createParkingLot(int capacity) throws ParkingException;

    /**
     * This function can be used to create multi story parking space
     * @param parkingLevels no of floors
     * @param capacityList capacity of each floor
     * @throws ParkingException
     */

    void createParkingLot(int parkingLevels, List<Integer> capacityList) throws ParkingException;

    /**
     * This function can be used to park vehicle on nearest slot.
     * @param vehicle
     * @return Parking ticker
     * @throws ParkingException
     */

    ParkingTicket park(Vehicle vehicle) throws ParkingException;

    /**
     * This function can be used to vaccant the parking slot.
     * @param slotNo
     * @throws ParkingException
     */
    void unpark(int slotNo) throws ParkingException;

    /**
     * This function can be used to find the status of all occupied parking slots.
     * @throws ParkingException
     */
    void status() throws ParkingException;

    /**
     * This function can be used to get registration no of all
     * occupied slot where given color vehicle parked
     * @param color
     * @return list of registration no
     * @throws ParkingException
     */

    List<String> getRegistrationNOsUsingColor(Color color) throws ParkingException;

    /**
     * This function can be used to get list of all slots
     * where given color vehicle parked
     * @param color
     * @return list of slot no
     * @throws ParkingException
     */

    List<Integer> getSlotNOsUsingColor(Color color) throws ParkingException;

    /**
     * This function can be used to get slot no of given car registration no's
     * @param registrationNo
     * @return slot no
     * @throws ParkingException
     */

    Integer getSlotNoUsingRegistrationNo(String registrationNo) throws ParkingException;

}
