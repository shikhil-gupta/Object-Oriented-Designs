package com.gojek.parkinglot.model;

import com.gojek.parkinglot.constants.Color;
import com.gojek.parkinglot.constants.ParkingSlotStatus;
import com.gojek.parkinglot.pojo.StatusPojo;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.*;

@Getter
public class ParkingFloorSpace {
    private int floorNo;

    private int parkingFloorCapacity;

    private int emptySlotsCount;

    private TreeMap<Integer, ParkingSlot> slotNoToParkingSlotMap;

    public ParkingFloorSpace(final int floorNo, final int parkingFloorCapacity) {
        this.floorNo = floorNo;
        this.parkingFloorCapacity = parkingFloorCapacity;
        this.emptySlotsCount = parkingFloorCapacity;
        this.slotNoToParkingSlotMap = new TreeMap<>();
    }

    public void createParkingSlots(int slotNo) {
        synchronized (slotNoToParkingSlotMap) {
            int i = 0;
            while (i < parkingFloorCapacity) {
                slotNoToParkingSlotMap.put(slotNo, new CompactParkingSlot(slotNo, 10));
                slotNo++;
                i++;
            }
        }
    }

    public Integer getAndReserveNearestParkingSlot(final Vehicle vehicle) {

        if (emptySlotsCount <= 0) {
            return null;
        }

        for (Map.Entry<Integer, ParkingSlot> pair : slotNoToParkingSlotMap.entrySet()) {
            if (!checkSlotIsOccupied(pair.getValue())) {

                synchronized (pair.getValue()) {
                    if (!checkSlotIsOccupied(pair.getValue())) {
                        pair.getValue().setStatus(ParkingSlotStatus.OCCUPIED);
                        pair.getValue().setVehicleOptional(Optional.of(vehicle));
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        pair.getValue().setLastModifiedTimeStamp(Optional.of(timestamp));
                        emptySlotsCount--;
                        return pair.getKey();
                    }
                }
            }
        }
        return null;
    }

    public void vaccateParkingSlot(final int slotNo) {
        ParkingSlot parkingSlot = slotNoToParkingSlotMap.get(slotNo);
        synchronized (parkingSlot) {
            slotNoToParkingSlotMap.get(slotNo).setVehicleOptional(Optional.empty());
            slotNoToParkingSlotMap.get(slotNo).setStatus(ParkingSlotStatus.VACCANT);
            slotNoToParkingSlotMap.get(slotNo).setLastModifiedTimeStamp(Optional.empty());
            emptySlotsCount++;
        }
    }

    public Integer getSlotNoUsingRegistrationNo(final String registrationNo) {
        for (Map.Entry<Integer, ParkingSlot> pair : slotNoToParkingSlotMap.entrySet()) {
            ParkingSlot slot = pair.getValue();
            synchronized (slot) {
                if (checkSlotIsOccupied(slot) && slot.getVehicleOptional().get().getRegistrationNo().equalsIgnoreCase(registrationNo)) {
                    return pair.getKey();
                }
            }
        }
        return null;
    }


    public boolean doesParkingSpaceContainsSlot(final int slot) {
        return slotNoToParkingSlotMap.containsKey(slot);
    }

    public Integer getParkingChargesOfSlot(final int slotNo) {
        synchronized (slotNoToParkingSlotMap) {
            if (slotNoToParkingSlotMap.containsKey(slotNo)) {
                return slotNoToParkingSlotMap.get(slotNo).getParkingSlotCharges();
            }
        }
        return null;
    }

    public boolean isVehicleAlreadyExist(final Vehicle vehicle) {
        for (Map.Entry<Integer, ParkingSlot> pair : slotNoToParkingSlotMap.entrySet()) {
            synchronized (pair.getValue()) {
                if (checkSlotIsOccupied(pair.getValue()) && pair.getValue().getVehicleOptional().get().equals(vehicle)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSlotIsOccupied(final int slotNo) {
        synchronized (slotNoToParkingSlotMap) {
            ParkingSlot parkingSlot = slotNoToParkingSlotMap.get(slotNo);
            return checkSlotIsOccupied(parkingSlot);
        }
    }

    public List<StatusPojo> getStatusOfOccupiedSlots() {

        List<StatusPojo> result = new ArrayList<>();
        for (Map.Entry<Integer, ParkingSlot> pair : slotNoToParkingSlotMap.entrySet()) {
            ParkingSlot slot = pair.getValue();
            synchronized (slot) {
                if (checkSlotIsOccupied(slot)) {
                    result.add(new StatusPojo(pair.getKey(), slot.getVehicleOptional().get().getRegistrationNo(), slot.getVehicleOptional().get().getColor().getColor()));
                }
            }
        }
        return result;

    }

    public List<String> getRegistrationNoUsingColor(final Color color) {

        List<String> result = new ArrayList<>();
        for (Map.Entry<Integer, ParkingSlot> pair : slotNoToParkingSlotMap.entrySet()) {
            ParkingSlot slot = pair.getValue();
            synchronized (slot) {
                if (checkSlotIsOccupied(slot) && slot.getVehicleOptional().get().getColor().equals(color)) {
                    result.add(slot.getVehicleOptional().get().getRegistrationNo());
                }
            }
        }
        return result;
    }


    public List<Integer> getSlotNOsUsingColor(final Color color) {
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, ParkingSlot> pair : slotNoToParkingSlotMap.entrySet()) {
            ParkingSlot slot = pair.getValue();
            synchronized (slot) {
                if (checkSlotIsOccupied(slot) && slot.getVehicleOptional().get().getColor().equals(color)) {
                    result.add(pair.getKey());
                }
            }
        }
        return result;
    }

    private boolean checkSlotIsOccupied(final ParkingSlot parkingSlot) {

        return parkingSlot.getVehicleOptional().isPresent() && parkingSlot.getStatus().equals(ParkingSlotStatus.OCCUPIED);
    }

}
