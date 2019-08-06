package com.gojek.test;

import com.gojek.parkinglot.ParkingTicket;
import com.gojek.parkinglot.constants.Color;
import com.gojek.parkinglot.constants.ParkingErrorCodes;
import com.gojek.parkinglot.exception.ParkingException;
import com.gojek.parkinglot.managers.MultiBuildingAndStoryParkingManager;
import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.pojo.StatusPojo;
import com.gojek.parkinglot.service.IParkingService;
import com.gojek.parkinglot.service.ParkingService;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

public class ParkingServiceTest {

    @Test
    public void createParkingLot() {
        try {
            ParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(1);
            MultiBuildingAndStoryParkingManager parkingManager = (MultiBuildingAndStoryParkingManager) (parkingService.getParkingManager());
            Assert.assertEquals(1, parkingManager.getTotalSlotCapicity());
        } catch (ParkingException ex) {
            Assert.fail();
        }
    }

    @Test
    public void parkCar() {
        try {
            ParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(1);
            MultiBuildingAndStoryParkingManager parkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(1, parkingManager.getTotalSlotCapicity());
            Vehicle car = new Car(Color.BLACK, "KA-01-HH-3141");
            ParkingTicket ticket = parkingService.park(car);
            Integer slotNo = parkingService.getSlotNoUsingRegistrationNo("KA-01-HH-3141");
            Assert.assertNotNull(slotNo);
            Assert.assertEquals(ticket.getSlotNo().intValue(), slotNo.intValue());
        } catch (ParkingException ex) {
            Assert.fail();
        }

    }

    @Test
    public void parkCarMultipleFloor() {
        try {
            ParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(3, Arrays.asList(1,2,1));
            MultiBuildingAndStoryParkingManager parkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(4, parkingManager.getTotalSlotCapicity());

            //Parking at first floor
            Vehicle car1 = new Car(Color.BLACK, "KA-01-HH-3141");
            ParkingTicket ticket1 = parkingService.park(car1);
            Assert.assertNotNull(ticket1);
            Assert.assertEquals(1,ticket1.getSlotNo().intValue());
            Assert.assertEquals(1,ticket1.getFloorLevel().intValue());

            //Parking at second floor
            Vehicle car2 = new Car(Color.BLACK, "KA-01-HH-3142");
            ParkingTicket ticket2 = parkingService.park(car2);
            Assert.assertNotNull(ticket2);
            Assert.assertEquals(2,ticket2.getSlotNo().intValue());
            Assert.assertEquals(2,ticket2.getFloorLevel().intValue());

            Vehicle car3 = new Car(Color.BLACK, "KA-01-HH-3143");
            ParkingTicket ticket3 = parkingService.park(car3);
            Assert.assertNotNull(ticket3);
            Assert.assertEquals(3,ticket3.getSlotNo().intValue());
            Assert.assertEquals(2,ticket3.getFloorLevel().intValue());

            //Parking at third floor
            Vehicle car4 = new Car(Color.BLACK, "KA-01-HH-3144");
            ParkingTicket ticket4 = parkingService.park(car4);
            Assert.assertNotNull(ticket4);
            Assert.assertEquals(4,ticket4.getSlotNo().intValue());
            Assert.assertEquals(3,ticket4.getFloorLevel().intValue());

        } catch (ParkingException ex) {
            Assert.fail();
        }
    }

    @Test
    public void parkCarWhenParkingIsFull() {

        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(1);
            Vehicle car = new Car(Color.BLACK, "KA-01-HH-3141");
            parkingService.park(car);

            Vehicle car1 = new Car(Color.BLACK, "KA-01-HH-3142");
            parkingService.park(car1);
        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_FULL.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();
    }

    @Test
    public void parkSameCarTwice() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(1);
            Vehicle car = new Car(Color.BLACK, "KA-01-HH-3141");
            parkingService.park(car);
            parkingService.park(car);
        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_VEHICLE_ALREADY_PAPRKED.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();
    }


    @Test
    public void parkCarsWithSameRegistationNoAndColor() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(1);
            Vehicle car = new Car(Color.BLACK, "KA-01-HH-3141");
            parkingService.park(car);

            Vehicle car1 = new Car(Color.BLACK, "KA-01-HH-3141");
            parkingService.park(car1);
        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_VEHICLE_ALREADY_PAPRKED.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();
    }

    @Test
    public void parkCarsWithSameRegistationNoAndDifferentColor() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(1);
            Vehicle car1 = new Car(Color.BLACK, "KA-01-HH-3141");
            parkingService.park(car1);

            Vehicle car2 = new Car(Color.BLUE, "KA-01-HH-3141");
            parkingService.park(car2);
        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_VEHICLE_ALREADY_PAPRKED.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();
    }


    @Test
    public void parkCarWithMultipleConcurrentRequest() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(5);
            ExecutorService executor = Executors.newFixedThreadPool(5);

            Set<Callable<ParkingErrorCodes>> callables = new HashSet<>();
            for (int i = 1; i <= 5; i++) {
                callables.add(new Callable<ParkingErrorCodes>() {

                    @Override
                    public ParkingErrorCodes call() throws Exception {
                        try {
                            Vehicle car = new Car(Color.BLACK, Thread.currentThread().getName());
                            parkingService.park(car);
                            return ParkingErrorCodes.PARKING_SUCCESS;
                        } catch (ParkingException ex) {
                            Assert.assertEquals(ParkingErrorCodes.PARKING_FULL.getErrorMessage(), ex.getMessage());
                            return ParkingErrorCodes.PARKING_FULL;
                        }
                    }
                });
            }
            List<Future<ParkingErrorCodes>> callableSet = executor.invokeAll(callables);
            for (Future<ParkingErrorCodes> parkingCode : callableSet) {
                Assert.assertEquals(ParkingErrorCodes.PARKING_SUCCESS, parkingCode.get());
            }
        } catch (ParkingException | InterruptedException | ExecutionException ex) {
            Assert.fail();
        }
    }

    @Test
    public void parkCarWithMultipleConcurrentRequestAndParkingCapacity() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(5);
            ExecutorService executor = Executors.newFixedThreadPool(10);
            int expectedPassRequest = 5;
            int actualPassRequest = 0;

            Set<Callable<ParkingErrorCodes>> callables = new HashSet<>();
            for (int i = 1; i <= 10; i++) {
                callables.add(new Callable<ParkingErrorCodes>() {

                    @Override
                    public ParkingErrorCodes call() throws Exception {
                        try {
                            Vehicle car = new Car(Color.BLACK, Thread.currentThread().getName());
                            parkingService.park(car);
                            return ParkingErrorCodes.PARKING_SUCCESS;
                        } catch (ParkingException ex) {
                            Assert.assertEquals(ParkingErrorCodes.PARKING_FULL.getErrorMessage(), ex.getMessage());
                            return ParkingErrorCodes.PARKING_FULL;
                        }
                    }
                });
            }
            List<Future<ParkingErrorCodes>> callableSet = executor.invokeAll(callables);
            for (Future<ParkingErrorCodes> parkingCode : callableSet) {
                if (parkingCode.get() == ParkingErrorCodes.PARKING_SUCCESS) {
                    actualPassRequest++;
                }
            }
            Assert.assertEquals(5, actualPassRequest);
        } catch (ParkingException | InterruptedException | ExecutionException ex) {
            Assert.fail();
        }
    }


    @Test
    public void parkCarOnNearestSlot() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(5);
            Vehicle car = new Car(Color.BLACK, "KA-01-HH-3141");
            ParkingTicket ticket = parkingService.park(car);
            Assert.assertNotNull(ticket);
            Assert.assertEquals(1, ticket.getSlotNo().intValue());

        } catch (ParkingException ex) {

        }
    }

    @Test
    public void unPark() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(1);
            Vehicle car = new Car(Color.BLACK, "KA-01-HH-3141");
            ParkingTicket ticket = parkingService.park(car);
            Assert.assertNotNull(ticket);
            Assert.assertEquals(1, ticket.getSlotNo().intValue());
            parkingService.unpark(1);
        } catch (ParkingException ex) {
            Assert.fail();
        }
    }

    @Test
    public void unParkWithWrongSlotNo() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(1);
            Vehicle car = new Car(Color.BLACK, "KA-01-HH-3141");
            parkingService.park(car);

            parkingService.unpark(2);
        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_SLOT_NO_NOT_VALID.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();

    }

    @Test
    public void unParkWhenSlotIsAlreadyVaccated() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(1);
            Vehicle car = new Car(Color.BLACK, "KA-01-HH-3141");
            parkingService.park(car);

            parkingService.unpark(1);

            parkingService.unpark(1);

        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_SLOT_ALREADY_VACCATED.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();
    }


    @Test
    public void statusOfParkingLot() {
        try {
            ParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(2);
            ParkingTicket parkingTicket1 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3141"));
            Assert.assertNotNull(parkingTicket1);
            Assert.assertEquals(1, parkingTicket1.getSlotNo().intValue());
            ParkingTicket parkingTicket2 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3142"));
            Assert.assertEquals(2, parkingTicket2.getSlotNo().intValue());
            parkingService.status();

            HashMap<Integer, ParkingTicket> slotToParkingTickerMap = new HashMap<>();
            slotToParkingTickerMap.put(parkingTicket1.getSlotNo().intValue(), parkingTicket1);
            slotToParkingTickerMap.put(parkingTicket2.getSlotNo().intValue(), parkingTicket2);


            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            List<StatusPojo> statusPojos = multiStoryParkingManager.getStatus();

            Assert.assertEquals(slotToParkingTickerMap.size(), statusPojos.size());

            for (StatusPojo carSatus : statusPojos) {
                if (slotToParkingTickerMap.containsKey(carSatus.getSlotNo())) {
                    ParkingTicket ticket = slotToParkingTickerMap.get(carSatus.getSlotNo());
                    Assert.assertEquals(carSatus.getSlotNo(), ticket.getSlotNo().intValue());
                    Assert.assertEquals(carSatus.getRegistrationNo().toLowerCase(), ticket.getRegistationNo().toLowerCase());
                    Assert.assertEquals(carSatus.getColor(), ticket.getColor());

                } else {
                    Assert.fail();
                }
            }
        } catch (ParkingException ex) {
            Assert.fail();
        }

    }

    @Test
    public void statusOfEmptyParkingLot() {
        try {
            ParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(2);
            ParkingTicket parkingTicket1 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3141"));
            Assert.assertNotNull(parkingTicket1);
            Assert.assertEquals(1, parkingTicket1.getSlotNo().intValue());
            ParkingTicket parkingTicket2 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3142"));
            Assert.assertEquals(2, parkingTicket2.getSlotNo().intValue());

            parkingService.unpark(1);
            parkingService.unpark(2);

            parkingService.status();

            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            List<StatusPojo> statusPojos = multiStoryParkingManager.getStatus();

            Assert.assertEquals(0, statusPojos.size());

        } catch (ParkingException ex) {
            Assert.fail();
        }
    }

    @Test
    public void getRegistrationNoForGivenCarColour() {

        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(2);
            ParkingTicket parkingTicket1 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3141"));
            Assert.assertNotNull(parkingTicket1);
            Assert.assertEquals(1, parkingTicket1.getSlotNo().intValue());
            ParkingTicket parkingTicket2 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3142"));
            Assert.assertEquals(2, parkingTicket2.getSlotNo().intValue());

            Set<String> expectedSet = new HashSet<>();
            expectedSet.add("KA-01-HH-3141");
            expectedSet.add("KA-01-HH-3142");

            List<String> registationNo = parkingService.getRegistrationNOsUsingColor(Color.BLACK);

            Assert.assertEquals(expectedSet.size(), registationNo.size());

            for (String string : registationNo) {
                if (!expectedSet.contains(string)) {
                    Assert.fail();
                }
            }

        } catch (ParkingException ex) {
            Assert.fail();
        }
    }

    @Test
    public void getRegistrationNoForGivenCarColourWhenParkingIsEmpty() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(2);
            ParkingTicket parkingTicket1 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3141"));
            Assert.assertNotNull(parkingTicket1);
            Assert.assertEquals(1, parkingTicket1.getSlotNo().intValue());
            ParkingTicket parkingTicket2 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3142"));
            Assert.assertEquals(2, parkingTicket2.getSlotNo().intValue());
            parkingService.unpark(1);
            parkingService.unpark(2);

            List<String> registationNo = parkingService.getRegistrationNOsUsingColor(Color.BLACK);
            Assert.assertEquals(0, registationNo.size());
        } catch (ParkingException ex) {
            Assert.fail();
            return;
        }
    }

    @Test
    public void getRegistrationNoForGivenColorWhichIsNotValid() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(2);
            ParkingTicket parkingTicket1 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3141"));
            Assert.assertNotNull(parkingTicket1);
            Assert.assertEquals(1, parkingTicket1.getSlotNo().intValue());
            ParkingTicket parkingTicket2 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3142"));
            Assert.assertEquals(2, parkingTicket2.getSlotNo().intValue());

            List<String> registationNo = parkingService.getRegistrationNOsUsingColor(Color.BLUE);
            Assert.assertEquals(0, registationNo.size());
        } catch (ParkingException ex) {
            Assert.fail();
            return;
        }

    }

    @Test
    public void getSlotNoForGivenColour() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(2);
            ParkingTicket parkingTicket1 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3141"));
            Assert.assertNotNull(parkingTicket1);
            Assert.assertEquals(1, parkingTicket1.getSlotNo().intValue());
            ParkingTicket parkingTicket2 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3142"));
            Assert.assertEquals(2, parkingTicket2.getSlotNo().intValue());

            List<Integer> slotsNo = parkingService.getSlotNOsUsingColor(Color.BLACK);
            Assert.assertEquals(2, slotsNo.size());
            Assert.assertTrue(slotsNo.contains(1));
            Assert.assertTrue(slotsNo.contains(2));
        } catch (ParkingException ex) {
            Assert.fail();
            return;
        }

    }

    @Test
    public void getSlotNotForGivenInvalidColor() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(2);
            ParkingTicket parkingTicket1 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3141"));
            Assert.assertNotNull(parkingTicket1);
            Assert.assertEquals(1, parkingTicket1.getSlotNo().intValue());
            ParkingTicket parkingTicket2 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3142"));
            Assert.assertEquals(2, parkingTicket2.getSlotNo().intValue());

            List<Integer> slotsNo = parkingService.getSlotNOsUsingColor(Color.BLUE);
            Assert.assertEquals(0, slotsNo.size());

        } catch (ParkingException ex) {
            Assert.fail();
            return;
        }

    }

    @Test
    public void getSlotNoForGivenColourWhenParkingIsEmpty() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(2);
            List<Integer> slotsNo = parkingService.getSlotNOsUsingColor(Color.BLUE);
            Assert.assertEquals(0, slotsNo.size());
        } catch (ParkingException ex) {
            Assert.fail();
        }

    }

    @Test
    public void getSlotNoForGivenRegistrationNo() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(2);
            ParkingTicket parkingTicket1 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3141"));
            Assert.assertNotNull(parkingTicket1);
            Assert.assertEquals(1, parkingTicket1.getSlotNo().intValue());
            ParkingTicket parkingTicket2 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3142"));
            Assert.assertEquals(2, parkingTicket2.getSlotNo().intValue());

            Integer slotNo1 = parkingService.getSlotNoUsingRegistrationNo("KA-01-HH-3141");
            Assert.assertNotNull(slotNo1);
            Assert.assertEquals(1, slotNo1.intValue());

            Integer slotNo2 = parkingService.getSlotNoUsingRegistrationNo("KA-01-HH-3142");
            Assert.assertNotNull(slotNo2);
            Assert.assertEquals(2, slotNo2.intValue());

        } catch (ParkingException ex) {
            Assert.fail();
        }
    }

    @Test
    public void getSlotNoForGivenRegistrationNoWhichDoesNotExist() {
        try {
            IParkingService parkingService = new ParkingService();
            parkingService.createParkingLot(2);
            ParkingTicket parkingTicket1 = parkingService.park(new Car(Color.BLACK, "KA-01-HH-3141"));
            Assert.assertNotNull(parkingTicket1);
            Assert.assertEquals(1, parkingTicket1.getSlotNo().intValue());

            String notExistRegNp = "KA-01-HH-31412";
            Integer slotNo = parkingService.getSlotNoUsingRegistrationNo(notExistRegNp);
            Assert.assertNull(slotNo);
        } catch (ParkingException ex) {
            Assert.fail();
        }
    }


}
