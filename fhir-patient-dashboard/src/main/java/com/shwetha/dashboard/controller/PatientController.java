package com.shwetha.dashboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.shwetha.dashboard.dto.PatientDashboardDTO;
import com.shwetha.dashboard.service.ClinicalInsightService;
import com.shwetha.dashboard.service.ConditionService;
import com.shwetha.dashboard.service.FHIRPatientService;
import com.shwetha.dashboard.service.MedicationService;
import com.shwetha.dashboard.service.ObservationService;

@Controller
public class PatientController {

    private final FHIRPatientService service;
    private final ConditionService conditionService;
    private final MedicationService medicationService;
    private final ObservationService observationService;
    private final ClinicalInsightService clinicalInsightService;

    public PatientController(
            FHIRPatientService service,
            ConditionService conditionService,
            MedicationService medicationService,
            ObservationService observationService,
            ClinicalInsightService clinicalInsightService) {

        this.service = service;
        this.conditionService = conditionService;
        this.medicationService = medicationService;
        this.observationService = observationService;
        this.clinicalInsightService = clinicalInsightService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/search")
    public String searchPatient(
            @RequestParam String name,
            Model model) {

        List<PatientDashboardDTO> patients =
                service.searchPatients(name);

        model.addAttribute("patients", patients);

        return "index";
    }
    @GetMapping("/patient/{id}")
    public String patientDetails(
            @PathVariable String id,
            Model model) {
    	PatientDashboardDTO patient =
                new PatientDashboardDTO();


        patient.setId(id);


        List<String> conditions =
                conditionService.getConditions(id);


        patient.setConditions(conditions);

        List<String> medications =
                medicationService.getMedications(id);


        patient.setMedications(medications);
        
        List<String> observations =
                observationService.getObservations(id);


        patient.setObservations(observations);
        
        List<String> alerts = clinicalInsightService.generateAlerts(
                conditions,
                observations);

        patient.setAlerts(alerts);

        patient.setRiskLevel(
                clinicalInsightService.calculateRiskLevel(alerts));

        patient.setClinicalSummary(
                clinicalInsightService.generateSummary(
                        conditions,
                        alerts));

        model.addAttribute(
                "patient",
                patient
        );


        return "patient-dashboard";
    }

}