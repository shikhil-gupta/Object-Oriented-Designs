package com.application.tollManagement.toll;

import com.application.tollManagement.charges.PassCharges;
import com.application.tollManagement.constants.TollPassType;
import com.application.tollManagement.models.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class TollBooth {

    private Integer tollBoothID;
    private String tollBoothAddress;
    private List<TollPass> tollPassesIssued;
    private Double totalChargesCollected;
    private ConcurrentHashMap<TollPassType, PassCharges> passTypeToChargesMap;
    private Object lock;

    public TollBooth(final Integer tollBoothID, final String tollBoothAddress)
    {
        this.tollBoothID = tollBoothID;
        this.tollBoothAddress = tollBoothAddress;
        this.tollPassesIssued = new ArrayList<>();
        this.passTypeToChargesMap = new ConcurrentHashMap<>();
        this.totalChargesCollected = new Double(0);
        this.lock = new Object();
    }


    public LeaderBoard ShowLeaderBoard() {
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard.setTollBoothID(tollBoothID);
        leaderBoard.setTotalChargesCollected(totalChargesCollected);
        leaderBoard.setTotalVehicleCount(tollPassesIssued.size());
        return leaderBoard;
    }

    public TollPass IssueTollPass(final Vehicle vehicle, final TollPassType tollPassType) {

        double charges = passTypeToChargesMap.get(tollPassType).getCharges(vehicle.getVehicleType());

        TollPass tollPass = null;
        switch (tollPassType) {

            case ONE_TIME:
                tollPass = new OneTimeTollPass(charges, tollBoothID, vehicle.getRegistrationNo(), vehicle.getVehicleType(), tollPassType);
                break;

            case RETURN_PASS:
                tollPass = new ReturnTollPass(charges, tollBoothID, vehicle.getRegistrationNo(), vehicle.getVehicleType(), tollPassType);
                break;

            case WEEKLY_PASS:
                tollPass = new WeeklyTollPass(charges, tollBoothID, vehicle.getRegistrationNo(), vehicle.getVehicleType(), tollPassType);
                break;
        }

        synchronized (lock) {
            if(tollPass !=  null) {
                tollPassesIssued.add(tollPass);
                totalChargesCollected += charges;
            }
        }

        return tollPass;
    }

    public void ShowPassCharges() {
        System.out.println("Pass Charges for Both ID " + tollBoothID);
    }
}
