package com.shwetha.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.HumanName;
import org.springframework.stereotype.Service;

import com.shwetha.dashboard.dto.PatientDashboardDTO;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;

@Service
public class FHIRPatientService {

    private final IGenericClient client;

    public FHIRPatientService(IGenericClient client) {
        this.client = client;
    }

    public List<PatientDashboardDTO> searchPatients(String name) {

        Bundle bundle = client.search()
                .forResource(Patient.class)
                .where(new StringClientParam("name").matches().value(name))
                .returnBundle(Bundle.class)
                .execute();

        List<PatientDashboardDTO> patients = new ArrayList<>();

        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {

            Patient patient = (Patient) entry.getResource();

            PatientDashboardDTO dto = new PatientDashboardDTO();

            dto.setId(patient.getIdElement().getIdPart());

            if (!patient.getName().isEmpty()) {

                HumanName humanName = patient.getNameFirstRep();

                dto.setName(humanName.getNameAsSingleString());
            }

            if (patient.hasGender()) {
                dto.setGender(patient.getGender().toCode());
            }

            if (patient.hasBirthDate()) {
                dto.setBirthDate(patient.getBirthDate().toString());
            }

            patients.add(dto);
        }

        return patients;
    }
}