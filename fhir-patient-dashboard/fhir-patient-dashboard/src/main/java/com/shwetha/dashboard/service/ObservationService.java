package com.shwetha.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.rest.client.api.IGenericClient;

@Service
public class ObservationService {


    private final IGenericClient client;


    public ObservationService(IGenericClient client) {
        this.client = client;
    }



    public List<String> getObservations(String patientId) {


        List<String> observations = new ArrayList<>();


        Bundle bundle = client.search()
                .forResource(Observation.class)
                .where(Observation.PATIENT.hasId(patientId))
                .returnBundle(Bundle.class)
                .execute();



        for(Bundle.BundleEntryComponent entry : bundle.getEntry()) {


            Observation observation =
                    (Observation) entry.getResource();



            String display = "";


            if(observation.getCode().hasText()) {

                display = observation.getCode().getText();

            } else if(observation.getCode().hasCoding()) {

                display =
                observation.getCode()
                .getCodingFirstRep()
                .getDisplay();

            }



            if(observation.hasValueQuantity()) {

                display += " : "
                + observation.getValueQuantity()
                .getValue()
                + " "
                + observation.getValueQuantity()
                .getUnit();

            }


            observations.add(display);

        }


        return observations;
    }

}