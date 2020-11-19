package com.application.tollManagement.toll;

import com.application.tollManagement.models.LeaderBoard;
import lombok.Getter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class TollCompany {
    private Integer tollCompanyID;
    private String address;
    private String tollCompanyName;
    private ConcurrentHashMap<Integer, Toll> tollIDToTollMap;
    private AtomicInteger tollIDCounter;

    public TollCompany(final Integer tollCompanyID, final String address, final String tollCompanyName) {
        this.tollCompanyID = tollCompanyID;
        this.address = address;
        this.tollCompanyName=tollCompanyName;
        this.tollIDToTollMap = new ConcurrentHashMap<>();
        this.tollIDCounter = new AtomicInteger(1);
    }

    public Toll RegisterToll(final String tollAddress) {
        Toll toll = new Toll(tollIDCounter.getAndIncrement(), tollAddress);
        tollIDToTollMap.put(toll.getTollID(), toll);
        return toll;
    }

    public TollBooth RegisterTollBooth(final Integer tollID, final String tollBoothAddress) {

        TollBooth tollBooth = null;
        if(tollIDToTollMap.containsKey(tollID)) {
            Toll toll = tollIDToTollMap.get(tollID);
            tollBooth = toll.RegisterTollBooth(tollBoothAddress);
        } else {
            System.out.println("Toll Does not exist");
        }
        return tollBooth;
    }

    public LeaderBoard ShowLeaderBoard(final Integer tollID, final Integer tollBoothID) {
        Toll toll = tollIDToTollMap.get(tollID);
        return toll.ShowLeaderBoard(tollBoothID);
    }

    public void ShowPassCharges(Integer tollID, Integer tollBoothID) {
        Toll toll = tollIDToTollMap.get(tollID);
        toll.ShowPassCharges(tollBoothID);
    }
}
