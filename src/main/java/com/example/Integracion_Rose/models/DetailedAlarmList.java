package com.example.Integracion_Rose.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailedAlarmList {

    @JsonProperty("idAlarma")
    private String idAlarma;

    @JsonProperty("managementObject")
    private String managementObject;

    @JsonProperty("aditionalInfo")
    private String aditionalInfo;
}
