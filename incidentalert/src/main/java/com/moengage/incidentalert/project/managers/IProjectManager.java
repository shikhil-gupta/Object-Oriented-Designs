package com.moengage.incidentalert.project.managers;


import com.moengage.incidentalert.constants.EmployeeLevel;
import com.moengage.incidentalert.exception.IncidentException;
import com.moengage.incidentalert.incident.AbstractIncident;

public interface IProjectManager {

    boolean addProject(final String projectName) throws IncidentException;

    void addEmployee(final String employeeId, final String emailId, final String phoneNo) throws IncidentException;

    void assignProject(final String projectName, final String employeeId) throws IncidentException;

    void setLevel(final String projectName, final String employeeId, final EmployeeLevel employeeLevel) throws IncidentException;

    void unSetLevel(final String projectName, final EmployeeLevel employeeLevel) throws IncidentException;

    String createIncident(final String projectName, final AbstractIncident incident) throws IncidentException;

    void notifyIncident(final String projectName, final String incidentId) throws IncidentException;

    void esclateIncident(final String projectName, final String incidentId) throws IncidentException;

    void ackIncident(final String projectName, final String incidentId, final EmployeeLevel level) throws IncidentException;


}
