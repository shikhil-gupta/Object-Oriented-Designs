package com.moengage.incidentalert.incident;

import com.moengage.incidentalert.constants.IncidentPriority;
import com.moengage.incidentalert.project.AbstractProject;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;


@Getter
@Setter
public abstract class AbstractIncident {

    private String Id;
    private IncidentPriority incidentPriority;
    private Optional<AbstractProject> assignedProject;

}
