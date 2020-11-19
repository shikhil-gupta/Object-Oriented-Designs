package com.application.tollManagement;

import com.application.tollManagement.constants.TollPassType;
import com.application.tollManagement.models.LeaderBoard;
import com.application.tollManagement.models.TollPass;
import com.application.tollManagement.models.Vehicle;
import com.application.tollManagement.toll.Toll;
import com.application.tollManagement.toll.TollBooth;
import com.application.tollManagement.toll.TollCompany;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TollManagerImpl implements ITollManager
{

    private ConcurrentHashMap<Integer, TollBooth> tollBoothIDToTollBoothMap;
    private ConcurrentHashMap<Integer, Toll> tollIDToTollMap;
    private ConcurrentHashMap<Integer, TollCompany> tollCompanyIDToTollCompanyMap;
    private AtomicInteger tollCompanyIDCounter;

    public TollManagerImpl() {
        this.tollBoothIDToTollBoothMap = new ConcurrentHashMap<>();
        this.tollCompanyIDToTollCompanyMap = new ConcurrentHashMap<>();
        this.tollIDToTollMap = new ConcurrentHashMap<>();
        this.tollCompanyIDCounter = new AtomicInteger(1);
    }

    @Override
    public TollPass IssueTollPass(final Integer tollBoothID, final Vehicle vehicle, final TollPassType tollPassType) {

        TollBooth tollBooth = tollBoothIDToTollBoothMap.get(tollBoothID);
        TollPass tollPass = null;
        if(tollBooth != null) {
            tollPass = tollBooth.IssueTollPass(vehicle, tollPassType);
        }

        return tollPass;
    }

    @Override
    public boolean IsValidPass(final TollPass tollPass) {

        if(tollPass != null) {
            return tollPass.IsValidTollPass();
        } else {
            return false;
        }
    }

    @Override
    public TollCompany RegisterNewTollCompany(final String tollCompanyAddress, final String tollCompanyName) {
        TollCompany tollCompany = new TollCompany(tollCompanyIDCounter.getAndIncrement(), tollCompanyAddress, tollCompanyName);
        tollCompanyIDToTollCompanyMap.put(tollCompany.getTollCompanyID(), tollCompany);
        return tollCompany;
    }

    @Override
    public Toll RegisterNewToll(final Integer tollCompanyID, final String tollAddress) {
        TollCompany tollCompany = tollCompanyIDToTollCompanyMap.get(tollCompanyID);
        Toll newToll = tollCompany.RegisterToll(tollAddress);
        tollIDToTollMap.put(newToll.getTollID(), newToll);
        return newToll;
    }

    @Override
    public TollBooth RegisterNewTollBooth(final Integer tollCompanyID, final Integer tollID, final String tollBoothAddress) {
        TollCompany tollCompany = tollCompanyIDToTollCompanyMap.get(tollCompanyID);
        TollBooth tollBooth = tollCompany.RegisterTollBooth(tollID, tollBoothAddress);
        tollBoothIDToTollBoothMap.put(tollBooth.getTollBoothID(), tollBooth);
        return tollBooth;
    }

    @Override
    public LeaderBoard ShowLeaderBoard(Integer tollCompanyID, Integer tollID, Integer tollBoothID) {
        TollCompany tollCompany = tollCompanyIDToTollCompanyMap.get(tollCompanyID);
        return tollCompany.ShowLeaderBoard(tollID, tollBoothID);
    }

    @Override
    public void ShowPassCharges(Integer tollCompanyID, Integer tollID, Integer tollBoothID) {
        TollCompany tollCompany = tollCompanyIDToTollCompanyMap.get(tollCompanyID);
        tollCompany.ShowPassCharges(tollID, tollBoothID);
    }


}
