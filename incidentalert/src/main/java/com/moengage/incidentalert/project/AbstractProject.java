package com.moengage.incidentalert.project;

import com.moengage.incidentalert.incident.AbstractIncident;
import com.moengage.incidentalert.model.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
@Setter
public class AbstractProject {

    private String projectId;

    private String projectName;

    private ConcurrentHashMap<String, Employee> employeeConcurrentHashMap;

    private Optional<ConcurrentLinkedQueue<AbstractIncident>> abstractIncidentList;


}
