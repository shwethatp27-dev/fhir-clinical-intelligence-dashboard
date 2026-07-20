package com.shwetha.dashboard.dto;

import java.util.ArrayList;
import java.util.List;

public class PatientDashboardDTO {

    private String id;
    private String name;
    private String gender;
    private String birthDate;
    private List<String> conditions = new ArrayList<>();
    private List<String> medications = new ArrayList<>();
    private List<String> observations = new ArrayList<>();
    private List<String> alerts = new ArrayList<>();

    private String riskLevel;

    private String clinicalSummary;
    
    public PatientDashboardDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    public List<String> getConditions() {
        return conditions;
    }


    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }
    
    public List<String> getMedications() {
        return medications;
    }


    public void setMedications(List<String> medications) {
        this.medications = medications;
    }
    
    public List<String> getObservations() {
        return observations;
    }


    public void setObservations(List<String> observations) {
        this.observations = observations;
    }

	public List<String> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<String> alerts) {
		this.alerts = alerts;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getClinicalSummary() {
		return clinicalSummary;
	}

	public void setClinicalSummary(String clinicalSummary) {
		this.clinicalSummary = clinicalSummary;
	}
}