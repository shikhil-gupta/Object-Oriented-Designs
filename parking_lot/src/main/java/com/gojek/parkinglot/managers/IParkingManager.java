package com.gojek.parkinglot.managers;

import com.gojek.parkinglot.constants.Color;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.pojo.ParkingResponsePojo;
import com.gojek.parkinglot.pojo.StatusPojo;
import com.gojek.parkinglot.strategy.IAssignmentStrategy;

import java.util.List;

/**
 * This is interface for Parking Manager.
 */

public interface IParkingManager {
    /**
     * This function can be used to park given vehicle to nearest free parking slots.
     * @param vehicle
     * @return parking response pojo which contains floor level, slot no, charges of slots with error code.
     */

    ParkingResponsePojo park(final Vehicle vehicle, final IAssignmentStrategy assignmentStrategy);


    void addMoreBuildings(final int totalFloors, final List<Integer> floorsCapacity);

    /**
     * This function can be used to free the given slot no.
     * @param slotNo slot to be free
     * @return Parking Error codes values
     */

    int unpark(final int slotNo);

    /**
     * This function can be used to get the status of occupied parking slots in parking space.
     * @return statuspojo which contains details slot no, registation no etc. we can add more required params if needed.
     */
    List<StatusPojo> getStatus();

    /**
     * This function can be used to get the registration no of all parked car of given colour.
     * @param color
     * @return list of registration nos.
     */

    List<String> getRegistrationNoUsingColor(final Color color);

    /**
     * This function can be used to get slot no of all parked caf of given colour
     * @param color
     * @return list of slots no.
     */

    List<Integer> getSlotNOsUsingColor(final Color color);

    /**
     * This function can be used to get slot no of given car's registration no
     * @param registrationNo
     * @return return null if it dont find the slot.
     */

    Integer getSlotNoUsingRegistrationNo(final String registrationNo);

}
