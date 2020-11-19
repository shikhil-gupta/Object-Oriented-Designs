package com.application.tollManagement.toll;

import com.application.tollManagement.models.LeaderBoard;
import lombok.Getter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Toll {

    private Integer tollID;
    private String tollAddress;
    private ConcurrentHashMap<Integer, TollBooth> tollBoothIDToTollBoothMap;
    private AtomicInteger tollBoothIDCounter;

    public Toll(final Integer tollID, final String tollAddress) {
        this.tollID = tollID;
        this.tollAddress = tollAddress;
        this.tollBoothIDToTollBoothMap = new ConcurrentHashMap<>();
        this.tollBoothIDCounter = new AtomicInteger(1);
    }

    public TollBooth RegisterTollBooth(final String tollBoothAddress) {
        TollBooth tollBooth = new TollBooth(tollBoothIDCounter.getAndIncrement(), tollBoothAddress);
        tollBoothIDToTollBoothMap.put(tollBooth.getTollBoothID(), tollBooth);
        return tollBooth;
    }

    public LeaderBoard ShowLeaderBoard(final Integer tollBoothID) {
        TollBooth tollBooth = tollBoothIDToTollBoothMap.get(tollBoothID);
        return tollBooth.ShowLeaderBoard();
    }

    public void ShowPassCharges(Integer tollBoothID) {
        TollBooth tollBooth = tollBoothIDToTollBoothMap.get(tollBoothID);
        tollBooth.ShowPassCharges();
    }
}
