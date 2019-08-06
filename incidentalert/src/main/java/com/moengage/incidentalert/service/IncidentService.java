package com.moengage.incidentalert.service;


import com.moengage.incidentalert.constants.EmployeeLevel;
import com.moengage.incidentalert.exception.IncidentException;
import com.moengage.incidentalert.incident.Incident;

import javax.ws.rs.core.Response;

public interface IncidentService {

    Response addProject(final String projectName) throws IncidentException;

    Response addEmployee(final String employeeId, final String emailId, final String phoneNo) throws IncidentException;

    Response assignProject(final String projectName, final String employeeId) throws IncidentException;

    Response setLevel(final String projectName, final String employeeId, EmployeeLevel employeeLevel) throws IncidentException;

    Response unSetLevel(final String projectName, final EmployeeLevel employeeLevel) throws IncidentException;

    Response createIncident(final String projectName, Incident incident) throws IncidentException;

    Response notifyIncident(final String projectName, final String incidentId) throws IncidentException;

    Response escalateIncident(final String projectName, final String incidentId) throws IncidentException;

    Response ackIncident(final String projectName, final String incidentId, final EmployeeLevel level) throws IncidentException;

}
