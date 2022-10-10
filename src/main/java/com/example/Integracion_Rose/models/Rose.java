package com.example.Integracion_Rose.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@Entity
public class Rose {

    private String troubleTicketId;
    private String descriptionTT;
    private String typeState;
    private String typePriority;
    private String folioClass;

    //@JsonProperty("creationDate")
    private String dateTT;

    private String typeFolio;
    private String typeOrigen;
    private String typeRegion;
    private String severidadInicial;
    private String typeAffectation;
    private String typeAffectationClient;
    private String typeClient;
    private String rootTicket;
    private String history;
    private String historical;
    private Object elements;
    private Object detailedAlarmList;
    private String user;
    private String severity;
    private String bitacora;
    private String cancelData;

    private String userAsignado;
    private String accion;
    private String estado;
    private String subestado;
    private String elementSolucion;

    private String idAlarma;
    private String managementObject;
    private String aditionalInfo;
    private Object element;

    private List listProjects;
}
