package com.gojek.test;

import com.gojek.parkinglot.RequestHandler;
import com.gojek.parkinglot.constants.Color;
import com.gojek.parkinglot.constants.ParkingErrorCodes;
import com.gojek.parkinglot.exception.ParkingException;
import com.gojek.parkinglot.managers.MultiBuildingAndStoryParkingManager;
import com.gojek.parkinglot.pojo.StatusPojo;
import com.gojek.parkinglot.service.ParkingService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RequestHandlerTest {

    @Test
    public void createParkingLotWithValidCommand() {
        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String command = "create_parking_lot 6";
            requestHandler.execute(command);
            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(6, multiStoryParkingManager.getTotalSlotCapicity());
        } catch (ParkingException ex) {
            Assert.fail();
        }

    }

    @Test
    public void createParkingLotWithInvalidCommand() {
        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String command = "create_parking_lot dcd";
            requestHandler.execute(command);
            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(6, multiStoryParkingManager.getTotalSlotCapicity());
        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_INVALID_COMMAND.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();

    }

    @Test
    public void parkCarWithValidCommand() {
        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String createParkingLotCommand = "create_parking_lot 1";
            requestHandler.execute(createParkingLotCommand);

            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(1, multiStoryParkingManager.getTotalSlotCapicity());

            String parkCarCommand = "park KA-01-HH-1234 White";
            requestHandler.execute(parkCarCommand);
            Integer slotNo = multiStoryParkingManager.getSlotNoUsingRegistrationNo("KA-01-HH-1234");
            Assert.assertNotNull(slotNo);

        } catch (ParkingException ex) {
            Assert.fail();
        }
    }

    @Test
    public void parkCarWithInvalidCommand() {

        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String createParkingLotCommand = "create_parking_lot 2";
            requestHandler.execute(createParkingLotCommand);

            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(2, multiStoryParkingManager.getTotalSlotCapicity());

            String parkCarCommand = "par KA-01-HH-1234 White";
            requestHandler.execute(parkCarCommand);
        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_INVALID_COMMAND.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();


    }

    @Test
    public void leaveCarWithValidCommand() {
        try{
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String createParkingLotCommand = "create_parking_lot 2";
            requestHandler.execute(createParkingLotCommand);

            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(2, multiStoryParkingManager.getTotalSlotCapicity());

            String parkCarCommand1 = "park KA-01-HH-1234 White";
            requestHandler.execute(parkCarCommand1);

            String parkCarCommand2 = "park KA-01-HH-1235 White";
            requestHandler.execute(parkCarCommand2);

            String leaveCommand1 = "leave 1";
            requestHandler.execute(leaveCommand1);


            String leaveCommand2 = "leave 2";
            requestHandler.execute(leaveCommand2);

            List<StatusPojo> statusPojos = multiStoryParkingManager.getStatus();

            Assert.assertEquals(0,statusPojos.size());

        } catch (ParkingException ex) {
            Assert.fail();
        }
    }

    @Test
    public void leaveCarWithInvalidCommand1() {
        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String createParkingLotCommand = "create_parking_lot 2";
            requestHandler.execute(createParkingLotCommand);

            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(2, multiStoryParkingManager.getTotalSlotCapicity());

            String parkCarCommand1 = "park KA-01-HH-1234 White";
            requestHandler.execute(parkCarCommand1);

            String leaveInvalidCommand1 = "leave";
            requestHandler.execute(leaveInvalidCommand1);

        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_INVALID_COMMAND.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();
    }

    @Test
    public void leaveCarWithInvalidCommand2() {
        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String createParkingLotCommand = "create_parking_lot 2";
            requestHandler.execute(createParkingLotCommand);

            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(2, multiStoryParkingManager.getTotalSlotCapicity());

            String parkCarCommand1 = "park KA-01-HH-1234 White";
            requestHandler.execute(parkCarCommand1);

            String leaveInvalidCommand1 = "leave 43";
            requestHandler.execute(leaveInvalidCommand1);

        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_SLOT_NO_NOT_VALID.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();
    }

    @Test
    public void registrationNOsForCarWithColorWithValidCommand() {
        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String createParkingLotCommand = "create_parking_lot 2";
            requestHandler.execute(createParkingLotCommand);

            String parkCarCommand1 = "park KA-01-HH-1234 White";
            requestHandler.execute(parkCarCommand1);

            String parkCarCommand2 = "park KA-01-HH-1235 White";
            requestHandler.execute(parkCarCommand2);

            String registrationNoForCarColourCommand = "registration_numbers_for_cars_with_colour White";
            requestHandler.execute(registrationNoForCarColourCommand);

        } catch (ParkingException ex) {
            Assert.fail();
        }

    }

    @Test
    public void registrationNOsForCarWithColorWithInValidCommand() {

        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String createParkingLotCommand = "create_parking_lot 2";
            requestHandler.execute(createParkingLotCommand);

            String parkCarCommand1 = "park KA-01-HH-1234 White";
            requestHandler.execute(parkCarCommand1);

            String parkCarCommand2 = "park KA-01-HH-1235 White";
            requestHandler.execute(parkCarCommand2);

            String registrationNoForCarColourCommand = "registration_numbers_for_cars_with_colo";
            requestHandler.execute(registrationNoForCarColourCommand);

        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_INVALID_COMMAND.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();


    }


    @Test
    public void slotNOsForCarWihColourWithValidCommand() {
        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String createParkingLotCommand = "create_parking_lot 2";
            requestHandler.execute(createParkingLotCommand);

            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(2, multiStoryParkingManager.getTotalSlotCapicity());

            String parkCarCommand1 = "park KA-01-HH-1234 White";
            requestHandler.execute(parkCarCommand1);

            String parkCarCommand2 = "park KA-01-HH-1235 White";
            requestHandler.execute(parkCarCommand2);

            String registrationNoForCarColourCommand = "slot_numbers_for_cars_with_colour White";
            requestHandler.execute(registrationNoForCarColourCommand);

            List<Integer> slots = multiStoryParkingManager.getSlotNOsUsingColor(Color.WHITE);
            Assert.assertEquals(2, slots.size());
            Assert.assertTrue(slots.contains(1));
            Assert.assertTrue(slots.contains(2));

        } catch (ParkingException ex) {
            Assert.fail();
        }

    }

    @Test
    public void slotNOsForCarWihColourWithInValidCommand() {
        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String createParkingLotCommand = "create_parking_lot 2";
            requestHandler.execute(createParkingLotCommand);

            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(2, multiStoryParkingManager.getTotalSlotCapicity());

            String parkCarCommand1 = "park KA-01-HH-1234 White";
            requestHandler.execute(parkCarCommand1);

            String parkCarCommand2 = "park KA-01-HH-1235 White";
            requestHandler.execute(parkCarCommand2);

            String registrationNoForCarColourCommand = "slot_numbers_for_cars_with_colou";
            requestHandler.execute(registrationNoForCarColourCommand);
        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_INVALID_COMMAND.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();
    }

    @Test
    public void slotNOsForRegistrationNoWithValidCommand() {
        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String createParkingLotCommand = "create_parking_lot 2";
            requestHandler.execute(createParkingLotCommand);

            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(2, multiStoryParkingManager.getTotalSlotCapicity());

            String parkCarCommand1 = "park KA-01-HH-1234 White";
            requestHandler.execute(parkCarCommand1);

            String parkCarCommand2 = "park KA-01-HH-1235 White";
            requestHandler.execute(parkCarCommand2);

            String registrationNoForCarColourCommand = "slot_number_for_registration_number KA-01-HH-1234";
            requestHandler.execute(registrationNoForCarColourCommand);

            Integer slotNo = multiStoryParkingManager.getSlotNoUsingRegistrationNo("KA-01-HH-1234");
            Assert.assertNotNull(slotNo);

        } catch (ParkingException ex) {
            Assert.fail();
        }
    }

    @Test
    public void slotNOsForRegistrationNoWithInValidCommand() {

        try {
            ParkingService parkingService = new ParkingService();
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setParkingService(parkingService);
            String createParkingLotCommand = "create_parking_lot 2";
            requestHandler.execute(createParkingLotCommand);

            MultiBuildingAndStoryParkingManager multiStoryParkingManager = (MultiBuildingAndStoryParkingManager) parkingService.getParkingManager();
            Assert.assertEquals(2, multiStoryParkingManager.getTotalSlotCapicity());

            String parkCarCommand1 = "park KA-01-HH-1234 White";
            requestHandler.execute(parkCarCommand1);

            String parkCarCommand2 = "park KA-01-HH-1235 White";
            requestHandler.execute(parkCarCommand2);

            String registrationNoForCarColourCommand = "slot_number_for_registration_numb";
            requestHandler.execute(registrationNoForCarColourCommand);
        } catch (ParkingException ex) {
            Assert.assertEquals(ParkingErrorCodes.PARKING_INVALID_COMMAND.getErrorMessage(), ex.getMessage());
            return;
        }
        Assert.fail();

    }

}
