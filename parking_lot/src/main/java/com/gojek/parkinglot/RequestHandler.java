package com.gojek.parkinglot;


import com.gojek.parkinglot.constants.Color;
import com.gojek.parkinglot.constants.ParkingErrorCodes;
import com.gojek.parkinglot.exception.ParkingException;
import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.service.IParkingService;
import com.gojek.parkinglot.validation.UserActionValidationStrategy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestHandler implements IRequestHandler {


    private IParkingService parkingService;

    @Override
    public void execute(final String command) throws ParkingException {

        if (command == null) {
            throwInvalidCommandException();
        }

        String[] commandValues = command.split(" ");
        String commandKey = commandValues[0].trim().toLowerCase();

        Boolean isValidCommand = Boolean.FALSE;

        switch (commandKey) {
            case "create_parking_lot":
                isValidCommand = UserActionValidationStrategy.CREATE_PARKING_LOT.isValidInput(command);
                if (isValidCommand) {
                    parkingService.createParkingLot(Integer.valueOf(commandValues[1]));
                }
                break;
            case "park":
                isValidCommand = UserActionValidationStrategy.PARK.isValidInput(command);
                if (isValidCommand) {
                    String registrationNo = commandValues[1];
                    String colorString = commandValues[2];
                    parkingService.park(new Car(Color.getColorByValue(colorString), registrationNo));
                }
                break;
            case "leave":
                isValidCommand = UserActionValidationStrategy.LEAVE.isValidInput(command);
                if (isValidCommand) {
                    Integer slotNo = Integer.valueOf(commandValues[1]);
                    parkingService.unpark(slotNo);
                }
                break;
            case "status":
                isValidCommand = UserActionValidationStrategy.STATUS.isValidInput(command);
                if (isValidCommand) {
                    parkingService.status();
                }
                break;
            case "registration_numbers_for_cars_with_colour":
                isValidCommand = UserActionValidationStrategy.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR.isValidInput(command);
                if (isValidCommand) {
                    Color color = Color.getColorByValue(commandValues[1]);
                    parkingService.getRegistrationNOsUsingColor(color);
                }
                break;
            case "slot_numbers_for_cars_with_colour":
                isValidCommand = UserActionValidationStrategy.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR.isValidInput(command);
                if (isValidCommand) {
                    Color color = Color.getColorByValue(commandValues[1]);
                    parkingService.getSlotNOsUsingColor(color);
                }
                break;
            case "slot_number_for_registration_number":
                isValidCommand = UserActionValidationStrategy.SLOT_NUMBER_FOR_REGISTRATION_NUMBER.isValidInput(command);
                if (isValidCommand) {
                    String registrationNo = commandValues[1];
                    parkingService.getSlotNoUsingRegistrationNo(registrationNo);
                }
                break;
        }

        if (!isValidCommand) {
            throwInvalidCommandException();
        }
    }

    private void throwInvalidCommandException() throws ParkingException {
        throw new ParkingException(ParkingErrorCodes.PARKING_INVALID_COMMAND.getErrorMessage());
    }
}
