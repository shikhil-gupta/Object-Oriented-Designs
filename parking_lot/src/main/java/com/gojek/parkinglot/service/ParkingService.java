package com.gojek.parkinglot.service;

import com.gojek.parkinglot.ParkingTicket;
import com.gojek.parkinglot.constants.Color;
import com.gojek.parkinglot.constants.ParkingErrorCodes;
import com.gojek.parkinglot.exception.ParkingException;
import com.gojek.parkinglot.managers.IParkingManager;
import com.gojek.parkinglot.managers.MultiBuildingAndStoryParkingManager;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.pojo.ParkingResponsePojo;
import com.gojek.parkinglot.pojo.StatusPojo;
import com.gojek.parkinglot.strategy.FirstFillStrategy;
import com.gojek.parkinglot.strategy.IAssignmentStrategy;
import com.gojek.parkinglot.utils.FormattedOutput;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * This class should be singleton class.
 */

@Getter
public class ParkingService implements IParkingService {

    private IParkingManager parkingManager;

    private IAssignmentStrategy strategy = new FirstFillStrategy();

    public void createParkingLot(final int capacity) throws ParkingException {
        createParkingLot(1, Arrays.asList(capacity));
    }

    public void createParkingLot(final int parkingLevels, final List<Integer> capacityList) throws ParkingException {
        if (parkingManager != null) {
            parkingManager.addMoreBuildings(parkingLevels, capacityList);
            System.out.println("Created a parking lot with " + capacityList.stream().reduce(0, Integer::sum) + " slots");
            return;
        }
        parkingManager = new MultiBuildingAndStoryParkingManager(parkingLevels, capacityList);
        System.out.println("Created a parking lot with " + capacityList.stream().reduce(0, Integer::sum) + " slots");


    }

    public ParkingTicket park(final Vehicle vehicle) throws ParkingException {

        isValidParkingLot();
        ParkingResponsePojo parkingResponsePojo = parkingManager.park(vehicle,strategy);

        if (parkingResponsePojo.getErrorCode() != ParkingErrorCodes.PARKING_SUCCESS.getErrorCode()) {
            FormattedOutput.printErrorMessage(parkingResponsePojo.getErrorCode());
            throw new ParkingException(ParkingErrorCodes.getParkingErrorMessageByErrorValue(parkingResponsePojo.getErrorCode()));
        } else {

            ParkingTicket parkingTicket = new ParkingTicket();
            parkingTicket.setSlotNo(parkingResponsePojo.getSlotNo());
            parkingTicket.setCharges(parkingResponsePojo.getCharges());
            parkingTicket.setFloorLevel(parkingResponsePojo.getFloorLevel());
            parkingTicket.setColor(vehicle.getColor().getColor());
            parkingTicket.setRegistationNo(vehicle.getRegistrationNo());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            parkingTicket.setTimeStamp(timestamp.toString());
            System.out.println("Allocated slot number: " + parkingResponsePojo.getSlotNo().intValue());
            return parkingTicket;
        }
    }

    public void unpark(final int slotNo) throws ParkingException {
        isValidParkingLot();
        int vaccated = parkingManager.unpark(slotNo);
        if (vaccated == ParkingErrorCodes.PARKING_SUCCESS.getErrorCode()) {
            System.out.println("Slot number " + slotNo + " is free");
        } else {
            throw new ParkingException(ParkingErrorCodes.getParkingErrorMessageByErrorValue(vaccated));
        }
    }

    public void status() throws ParkingException {
        isValidParkingLot();
        List<StatusPojo> result =parkingManager.getStatus();
        FormattedOutput.printStatusOfSlots(result);
    }

    public List<String> getRegistrationNOsUsingColor(final Color color) throws ParkingException {
        isValidParkingLot();
        List<String> result = parkingManager.getRegistrationNoUsingColor(color);
        FormattedOutput.printStringOutputAsCommaSeprated(result);
        return result;

    }

    public List<Integer> getSlotNOsUsingColor(final Color color) throws ParkingException {
        isValidParkingLot();
        List<Integer> result = parkingManager.getSlotNOsUsingColor(color);
        FormattedOutput.printIntegerOutputAsCommaSeprated(result);
        return result;
    }


    public Integer getSlotNoUsingRegistrationNo(final String registrationNo) throws ParkingException {
        isValidParkingLot();
        Integer slotNo = parkingManager.getSlotNoUsingRegistrationNo(registrationNo);
        if (slotNo != null) {
            System.out.println(slotNo.intValue());
        } else {
            System.out.println("Not found");
        }
        return slotNo;

    }

    private void isValidParkingLot() throws ParkingException {
        if (parkingManager == null) {
            throw new ParkingException(ParkingErrorCodes.PARKING_DOES_NOT_EXISTS.getErrorMessage());
        }
    }

}
