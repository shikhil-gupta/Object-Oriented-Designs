package com.gojek.parkinglot.managers;


import com.gojek.parkinglot.constants.Color;
import com.gojek.parkinglot.constants.ParkingErrorCodes;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.pojo.ParkingResponsePojo;
import com.gojek.parkinglot.pojo.StatusPojo;
import com.gojek.parkinglot.strategy.IAssignmentStrategy;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class MultiBuildingAndStoryParkingManager implements IParkingManager {

    private int totalSlotCapicity;
    private int totalBuilding;
    private AtomicInteger availibility;
    private TreeMap<Integer, MultiStoryParkingManager> buildingNoToParkingBuildingMap;
    private int startSlotNo = 1;
    private int minSlotNot = 1;
    private int endSlotNot = 1;
    private int buildingNo =1;

    public MultiBuildingAndStoryParkingManager(final int totalFloors, final List<Integer> floorsCapacity){

        buildingNoToParkingBuildingMap = new TreeMap<>();
        availibility = new AtomicInteger(0);
        addMoreBuildings(totalFloors,floorsCapacity);
    }


    public synchronized void addMoreBuildings(final int totalFloors, final List<Integer> floorsCapacity) {

        buildingNoToParkingBuildingMap.put(buildingNo, new MultiStoryParkingManager(buildingNo, startSlotNo, totalFloors, floorsCapacity));
        totalSlotCapicity += floorsCapacity.stream().reduce(0, Integer::sum);
        totalBuilding++;
        startSlotNo +=floorsCapacity.stream().reduce(0, Integer::sum);
        buildingNo++;
        availibility.addAndGet(floorsCapacity.stream().reduce(0, Integer::sum));
        endSlotNot = totalSlotCapicity;
    }

    @Override
    public ParkingResponsePojo park(Vehicle vehicle, final IAssignmentStrategy assignmentStrategy) {
        return assignmentStrategy.assignSlot(vehicle, this);
    }

    @Override
    public int unpark(int slotNo) {


        /*if(!isValidSlotNo(slotNo)) {
            return ParkingErrorCodes.PARKING_SLOT_NO_NOT_VALID.getErrorCode();
        }*/

        if(slotNo < minSlotNot || slotNo > endSlotNot) {
            return ParkingErrorCodes.PARKING_SLOT_NO_NOT_VALID.getErrorCode();
        }

        for (Map.Entry<Integer, MultiStoryParkingManager> pair : buildingNoToParkingBuildingMap.entrySet()) {

            int status = pair.getValue().unpark(slotNo);

            if(status == ParkingErrorCodes.PARKING_SUCCESS.getErrorCode()) {
                return ParkingErrorCodes.PARKING_SUCCESS.getErrorCode();
            }

        }
        return ParkingErrorCodes.PARKING_SLOT_ALREADY_VACCATED.getErrorCode();

    }

    @Override
    public List<StatusPojo> getStatus() {
        List<StatusPojo> result = new ArrayList<>();
        for (Map.Entry<Integer, MultiStoryParkingManager> pair : buildingNoToParkingBuildingMap.entrySet()) {
            result.addAll(pair.getValue().getStatus());
        }
        return result;
    }

    @Override
    public List<String> getRegistrationNoUsingColor(Color color) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<Integer, MultiStoryParkingManager> pair : buildingNoToParkingBuildingMap.entrySet()) {
            result.addAll(pair.getValue().getRegistrationNoUsingColor(color));
        }
        return result;
    }

    @Override
    public List<Integer> getSlotNOsUsingColor(Color color) {
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, MultiStoryParkingManager> pair : buildingNoToParkingBuildingMap.entrySet()) {
            result.addAll(pair.getValue().getSlotNOsUsingColor(color));
        }
        return result;
    }

    @Override
    public Integer getSlotNoUsingRegistrationNo(String registrationNo) {
        Integer slotNo = null;
        for (Map.Entry<Integer, MultiStoryParkingManager> pair : buildingNoToParkingBuildingMap.entrySet()) {
            slotNo = pair.getValue().getSlotNoUsingRegistrationNo(registrationNo);
            if (slotNo != null) {
                break;
            }
        }
        return slotNo;
    }
}
