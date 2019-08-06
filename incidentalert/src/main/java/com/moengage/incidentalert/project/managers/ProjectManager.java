package com.moengage.incidentalert.project.managers;

import com.moengage.incidentalert.constants.EmployeeLevel;
import com.moengage.incidentalert.exception.IncidentException;
import com.moengage.incidentalert.incident.AbstractIncident;
import com.moengage.incidentalert.project.AbstractProject;
import com.moengage.incidentalert.project.IncidentManager;
import com.moengage.incidentalert.project.Project;
import com.moengage.incidentalert.project.RoundRobinStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class ProjectManager implements IProjectManager {

    ConcurrentHashMap<String, AbstractProject> hashMap;
    IncidentManager incidentManager;


    public ProjectManager() {
        hashMap = new ConcurrentHashMap<String, AbstractProject>();
        incidentManager = new IncidentManager();
    }


    @Override
    public boolean addProject(String projectName) throws IncidentException {

        AbstractProject abstractProject = new Project();
        hashMap.put(projectName, abstractProject);
        return true;
    }

    @Override
    public void addEmployee(String employeeId, String emailId, String phoneNo) throws IncidentException {

    }

    @Override
    public void assignProject(String projectName, String employeeId) throws IncidentException {

    }

    @Override
    public void setLevel(String projectName, String employeeId, EmployeeLevel employeeLevel) throws IncidentException {

    }

    @Override
    public void unSetLevel(String projectName, EmployeeLevel employeeLevel) throws IncidentException {

    }

    @Override
    public String createIncident(final String projectName, final AbstractIncident incident) throws IncidentException {

        AbstractProject project = hashMap.get(projectName);
        if (project != null) {
            project.getAbstractIncidentList().get().add(incident);
            return incident.getId();
        } else {
            throw new IncidentException("Invalid Project");
        }
    }

    @Override
    public void notifyIncident(final String projectName, final String incidentId) throws IncidentException {

        AbstractProject project1 = hashMap.get(projectName);
        if (project1 != null) {
            //incidentManager.assign(project1.getEmployeeConcurrentHashMap(), new RoundRobinStrategy());
        } else {
            throw new IncidentException("Invalid Project");
        }
    }

    @Override
    public void esclateIncident(String projectName, String incidentId) throws IncidentException {

    }

    @Override
    public void ackIncident(String projectName, String incidentId, EmployeeLevel level) throws IncidentException {

    }



}
