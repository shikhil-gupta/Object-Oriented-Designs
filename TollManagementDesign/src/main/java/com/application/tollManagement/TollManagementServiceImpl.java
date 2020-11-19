package com.application.tollManagement;

import com.application.tollManagement.constants.TollPassType;
import com.application.tollManagement.models.LeaderBoard;
import com.application.tollManagement.models.TollPass;
import com.application.tollManagement.models.Vehicle;
import com.application.tollManagement.toll.Toll;
import com.application.tollManagement.toll.TollBooth;
import com.application.tollManagement.toll.TollCompany;
import org.springframework.beans.factory.annotation.Autowired;

public class TollManagementServiceImpl implements ITollManagementService {

    @Autowired
    private ITollManager tollManager;

    @Override
    public TollPass IssueTollPass(Integer tollBoothID, Vehicle vehicle, TollPassType tollPassType) {
        TollPass tollPass = tollManager.IssueTollPass(tollBoothID, vehicle, tollPassType);
        return tollPass;
    }

    public boolean IsValidPass(final TollPass tollPass) {
        boolean result = tollManager.IsValidPass(tollPass);
        return result;
    }

    @Override
    public TollCompany RegisterNewTollCompany(String tollCompanyAddress, String tollCompanyName) {
        TollCompany tollCompany = tollManager.RegisterNewTollCompany(tollCompanyAddress, tollCompanyName);
        return tollCompany;
    }

    @Override
    public Toll RegisterNewToll(Integer tollCompanyID, String tollAddress) {
        Toll toll = tollManager.RegisterNewToll(tollCompanyID, tollAddress);
        return toll;
    }

    @Override
    public TollBooth RegisterNewTollBooth(Integer tollCompanyID, Integer tollID, String tollBoothAddress) {
        TollBooth tollBooth = tollManager.RegisterNewTollBooth(tollCompanyID, tollID, tollBoothAddress);
        return tollBooth;
    }

    @Override
    public LeaderBoard ShowLeaderBoard(Integer tollCompanyID, Integer tollID, Integer tollBoothID) {
        return tollManager.ShowLeaderBoard(tollCompanyID, tollID, tollBoothID);
    }

    @Override
    public void ShowPassCharges(final Integer tollCompanyID, final Integer tollID, final Integer tollBoothID) {
        tollManager.ShowPassCharges(tollCompanyID, tollID, tollBoothID);
    }
}
