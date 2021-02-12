package com.gojek.parkinglot.managers;

import com.gojek.parkinglot.constants.Color;
import com.gojek.parkinglot.constants.ParkingErrorCodes;
import com.gojek.parkinglot.model.ParkingFloorSpace;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.pojo.ParkingResponsePojo;
import com.gojek.parkinglot.pojo.StatusPojo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
public class MultiStoryParkingManager {

    private int buildingNo;
    private int totalFloors;
    private int totalSlotCapicity;
    private TreeMap<Integer, ParkingFloorSpace> parkingLevelToFloorSpaceMap;

    public MultiStoryParkingManager(final int totalFloors, final List<Integer> floorsCapacity) {
        this.totalFloors = totalFloors;
        this.totalSlotCapicity = floorsCapacity.stream().reduce(0, Integer::sum);
        this.parkingLevelToFloorSpaceMap = new TreeMap<>();
        int slotNo = 1;
        for (int i = 0; i < totalFloors; i++) {
            ParkingFloorSpace parkingFloorSpace = new ParkingFloorSpace(i + 1, floorsCapacity.get(i));
            parkingFloorSpace.createParkingSlots(slotNo);
            slotNo += floorsCapacity.get(i);
            parkingLevelToFloorSpaceMap.put(i + 1, parkingFloorSpace);
        }
    }

    public MultiStoryParkingManager(final int buildingNo,int startSlotNo, final int totalFloors, final List<Integer> floorsCapacity) {
        this.totalFloors = totalFloors;
        this.totalSlotCapicity = floorsCapacity.stream().reduce(0, Integer::sum);
        this.parkingLevelToFloorSpaceMap = new TreeMap<>();
        this.buildingNo=buildingNo;
        int slotNo = startSlotNo;
        for (int i = 0; i < totalFloors; i++) {
            ParkingFloorSpace parkingFloorSpace = new ParkingFloorSpace(i + 1, floorsCapacity.get(i));
            parkingFloorSpace.createParkingSlots(slotNo);
            slotNo += floorsCapacity.get(i);
            parkingLevelToFloorSpaceMap.put(i + 1, parkingFloorSpace);
        }
    }

    public ParkingResponsePojo park(final Vehicle vehicle) {
        Integer slotNo = null;
        ParkingResponsePojo parkingResponsePojo = new ParkingResponsePojo();
        parkingResponsePojo.setErrorCode(ParkingErrorCodes.PARKING_SUCCESS.getErrorCode());

        if (isVehicleAlreadyExist(vehicle)) {
            parkingResponsePojo.setErrorCode(ParkingErrorCodes.PARKING_VEHICLE_ALREADY_PAPRKED.getErrorCode());
            return parkingResponsePojo;
        }

        for (Map.Entry<Integer, ParkingFloorSpace> pair : parkingLevelToFloorSpaceMap.entrySet()) {

            if (pair.getValue().getEmptySlotsCount() > 0) {
                slotNo = parkingLevelToFloorSpaceMap.get(pair.getKey()).getAndReserveNearestParkingSlot(vehicle);
            }
            if (slotNo != null) {
                parkingResponsePojo.setSlotNo(slotNo);
                parkingResponsePojo.setFloorLevel(pair.getKey());
                parkingResponsePojo.setCharges(parkingLevelToFloorSpaceMap.get(pair.getKey()).getParkingChargesOfSlot(slotNo));
                parkingResponsePojo.setBuildingNo(buildingNo);
                return parkingResponsePojo;
            }
        }
        parkingResponsePojo.setErrorCode(ParkingErrorCodes.PARKING_FULL.getErrorCode());
        return parkingResponsePojo;

    }

    public int unpark(final int slotNo) {
        if(!isValidSlotNo(slotNo)) {
            return ParkingErrorCodes.PARKING_SLOT_NO_NOT_VALID.getErrorCode();
        }
        for (Map.Entry<Integer, ParkingFloorSpace> pair : parkingLevelToFloorSpaceMap.entrySet()) {
            if (pair.getValue().doesParkingSpaceContainsSlot(slotNo) && pair.getValue().checkSlotIsOccupied(slotNo)) {
                pair.getValue().vaccateParkingSlot(slotNo);
                return ParkingErrorCodes.PARKING_SUCCESS.getErrorCode();
            }
        }
        return ParkingErrorCodes.PARKING_SLOT_ALREADY_VACCATED.getErrorCode();

    }

    public List<StatusPojo> getStatus() {
        List<StatusPojo> result = new ArrayList<>();
        for (Map.Entry<Integer, ParkingFloorSpace> pair : parkingLevelToFloorSpaceMap.entrySet()) {
            result.addAll(pair.getValue().getStatusOfOccupiedSlots());
        }
        return result;
    }

    public List<String> getRegistrationNoUsingColor(final Color color) {

        List<String> result = new ArrayList<>();
        for (Map.Entry<Integer, ParkingFloorSpace> pair : parkingLevelToFloorSpaceMap.entrySet()) {
            result.addAll(pair.getValue().getRegistrationNoUsingColor(color));
        }
        return result;
    }

    public List<Integer> getSlotNOsUsingColor(final Color color) {
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, ParkingFloorSpace> pair : parkingLevelToFloorSpaceMap.entrySet()) {
            result.addAll(pair.getValue().getSlotNOsUsingColor(color));
        }
        return result;
    }

    public Integer getSlotNoUsingRegistrationNo(final String registrationNo) {

        Integer slotNo = null;
        for (Map.Entry<Integer, ParkingFloorSpace> pair : parkingLevelToFloorSpaceMap.entrySet()) {
            slotNo = pair.getValue().getSlotNoUsingRegistrationNo(registrationNo);
            if (slotNo != null) {
                break;
            }
        }
        return slotNo;

    }

    private boolean isVehicleAlreadyExist(final Vehicle vehicle) {
        boolean isExists = false;
        for (Map.Entry<Integer, ParkingFloorSpace> pair : parkingLevelToFloorSpaceMap.entrySet()) {
            isExists |= pair.getValue().isVehicleAlreadyExist(vehicle);
            if(isExists) {
                break;
            }
        }
        return isExists;
    }

    private boolean isValidSlotNo(final int slotNo) {
        boolean isValidSlot = false;
        for (Map.Entry<Integer, ParkingFloorSpace> pair : parkingLevelToFloorSpaceMap.entrySet()) {
            isValidSlot |= pair.getValue().doesParkingSpaceContainsSlot(slotNo);
            if(isValidSlot) {
                break;
            }
        }
        return isValidSlot;
    }

}
