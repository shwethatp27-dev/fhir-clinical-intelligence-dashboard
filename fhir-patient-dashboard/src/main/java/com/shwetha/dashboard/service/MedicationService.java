package com.shwetha.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.rest.client.api.IGenericClient;

@Service
public class MedicationService {

    private final IGenericClient client;


    public MedicationService(IGenericClient client) {
        this.client = client;
    }


    public List<String> getMedications(String patientId) {

        List<String> medications = new ArrayList<>();


        Bundle bundle = client.search()
                .forResource(MedicationRequest.class)
                .where(MedicationRequest.PATIENT.hasId(patientId))
                .returnBundle(Bundle.class)
                .execute();



        for(Bundle.BundleEntryComponent entry : bundle.getEntry()) {

            MedicationRequest medication =
                    (MedicationRequest) entry.getResource();


            if(medication.getMedicationCodeableConcept()
                    .hasText()) {


                medications.add(
                    medication.getMedicationCodeableConcept()
                    .getText()
                );


            } else if(medication
                    .getMedicationCodeableConcept()
                    .hasCoding()) {


                medications.add(
                    medication.getMedicationCodeableConcept()
                    .getCodingFirstRep()
                    .getDisplay()
                );

            }

        }


        return medications;
    }
}