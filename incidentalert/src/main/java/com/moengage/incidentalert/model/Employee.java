package com.moengage.incidentalert.model;

import com.moengage.incidentalert.constants.EmployeeDepartment;
import com.moengage.incidentalert.constants.EmployeeLevel;
import com.moengage.incidentalert.incident.AbstractIncident;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Employee {

    private String employeeId;

    private String employeeEmailId;

    private String phoneNo;

    private User userInformation;

    private EmployeeLevel employeeLevel;

    private EmployeeDepartment employeeDepartment;

    private Optional<ConcurrentLinkedQueue<AbstractIncident>> abstractIncidentList;

}
