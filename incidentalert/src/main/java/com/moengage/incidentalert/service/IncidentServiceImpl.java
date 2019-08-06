package com.moengage.incidentalert.service;

import com.moengage.incidentalert.constants.EmployeeLevel;
import com.moengage.incidentalert.exception.IncidentException;
import com.moengage.incidentalert.incident.Incident;
import com.moengage.incidentalert.project.managers.IProjectManager;

import javax.ws.rs.core.Response;

public class IncidentServiceImpl implements IncidentService {

    IProjectManager projectManager;


    @Override
    public Response addProject(String projectName) throws IncidentException {

        boolean result = projectManager.addProject(projectName);
        return null;

    }

    @Override
    public Response addEmployee(String employeeId, String emailId, String phoneNo) throws IncidentException {
        return null;
    }

    @Override
    public Response assignProject(String projectName, String employeeId) throws IncidentException {
        return null;
    }

    @Override
    public Response setLevel(String projectName, String employeeId, EmployeeLevel employeeLevel) throws IncidentException {
        return null;
    }

    @Override
    public Response unSetLevel(String projectName, EmployeeLevel employeeLevel) throws IncidentException {
        return null;
    }

    @Override
    public Response createIncident(String projectName, Incident incident) throws IncidentException {
        return null;
    }

    @Override
    public Response notifyIncident(String projectName, String incidentId) throws IncidentException {
        return null;
    }

    @Override
    public Response escalateIncident(String projectName, String incidentId) throws IncidentException {
        return null;
    }

    @Override
    public Response ackIncident(String projectName, String incidentId, EmployeeLevel level) throws IncidentException {
        return null;
    }
}
