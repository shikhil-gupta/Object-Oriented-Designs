package com.gojek.parkinglot;

import com.gojek.parkinglot.constants.ParkingErrorCodes;
import com.gojek.parkinglot.exception.ParkingException;
import com.gojek.parkinglot.service.ParkingService;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        RequestHandler requestHandler = new RequestHandler();
        requestHandler.setParkingService(new ParkingService());
        BufferedReader bufferReader = null;
        String input = null;

        try {
            switch (args.length) {
                case 0:
                    //System.out.println("please type exit to stop execution");
                    try {
                        bufferReader = new BufferedReader(new InputStreamReader(System.in));
                        while (true) {
                            input = bufferReader.readLine();
                            if (input != null && input.trim().equalsIgnoreCase("exit")) {
                                break;
                            } else {
                                try {
                                    requestHandler.execute(input.trim());
                                } catch (ParkingException ex) {
                                    System.err.println(ex.getMessage());
                                }
                            }
                        }
                    } catch (Exception ex) {
                        throw new ParkingException(ParkingErrorCodes.PARKING_INTERNAL_DEFAULT_ERROR.getErrorMessage(), ex);
                    }
                    break;
                case 1:
                    if (args[0].equalsIgnoreCase("-help")) {
                        printUsage();
                    } else {
                        try {
                            File file = new File(args[0]);
                            bufferReader = new BufferedReader(new FileReader(file));
                            while ((input = bufferReader.readLine()) != null) {
                                try {
                                    requestHandler.execute(input.trim());
                                } catch (ParkingException ex) {
                                    System.err.println(ex.getMessage());

                                }
                            }

                        } catch (Exception ex) {
                            throw new ParkingException(ParkingErrorCodes.PARKING_INVALID_FILE.getErrorMessage(), ex);
                        }
                        break;
                    }
            }

        } catch (ParkingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                if (bufferReader != null)
                    bufferReader.close();
            } catch (IOException e) {
            }
        }
    }

    private static void printUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer = buffer.append(
                "--------------Please Enter one of the below commands. {variable} to be replaced -----------------------")
                .append("\n");
        buffer = buffer.append("A) For creating parking lot of size n               ---> create_parking_lot {capacity}")
                .append("\n");
        buffer = buffer
                .append("B) To park a car                                    ---> park <<car_number>> {car_clour}")
                .append("\n");
        buffer = buffer.append("C) Remove(Unpark) car from parking                  ---> leave {slot_number}")
                .append("\n");
        buffer = buffer.append("D) Print status of parking slot                     ---> status").append("\n");
        buffer = buffer.append(
                "E) Get cars registration no for the given car color ---> registration_numbers_for_cars_with_color {car_color}")
                .append("\n");
        buffer = buffer.append(
                "F) Get slot numbers for the given car color         ---> slot_numbers_for_cars_with_color {car_color}")
                .append("\n");
        buffer = buffer.append(
                "G) Get slot number for the given car number         ---> slot_number_for_registration_number {car_number}")
                .append("\n");
        buffer = buffer.append(
                "H) To stop execution, please type                   ---> exit")
                .append("\n");

        System.out.println(buffer.toString());
    }
}
