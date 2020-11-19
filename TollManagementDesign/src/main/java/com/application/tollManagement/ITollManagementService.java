package com.application.tollManagement;


import com.application.tollManagement.constants.TollPassType;
import com.application.tollManagement.models.LeaderBoard;
import com.application.tollManagement.models.TollPass;
import com.application.tollManagement.models.Vehicle;
import com.application.tollManagement.toll.Toll;
import com.application.tollManagement.toll.TollBooth;
import com.application.tollManagement.toll.TollCompany;

public interface ITollManagementService {

     TollPass IssueTollPass(final Integer tollBoothID, final Vehicle vehicle, final TollPassType tollPassType);
     boolean IsValidPass(final TollPass tollPass);
     TollCompany RegisterNewTollCompany(final String tollCompanyAddress, final String tollCompanyName);
     Toll RegisterNewToll(final Integer tollCompanyID, final String tollAddress);
     TollBooth RegisterNewTollBooth(final Integer tollCompanyID, final Integer tollID, final String tollBoothAddress);
     LeaderBoard ShowLeaderBoard(final Integer tollCompanyID, final Integer tollID, final Integer tollBoothID);
     void ShowPassCharges(final Integer tollCompanyID, final Integer tollID, final Integer tollBoothID);
}
