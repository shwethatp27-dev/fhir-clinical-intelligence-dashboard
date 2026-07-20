package com.shwetha.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Condition;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.rest.client.api.IGenericClient;

@Service
public class ConditionService {

    private final IGenericClient client;

    public ConditionService(IGenericClient client) {
        this.client = client;
    }


    public List<String> getConditions(String patientId) {

        List<String> conditions = new ArrayList<>();

        Bundle bundle = client.search()
                .forResource(Condition.class)
                .where(Condition.PATIENT.hasId(patientId))
                .returnBundle(Bundle.class)
                .execute();


        for(Bundle.BundleEntryComponent entry : bundle.getEntry()) {

            Condition condition =
                    (Condition) entry.getResource();


            if(condition.getCode().hasText()) {

                conditions.add(
                    condition.getCode().getText()
                );

            } else if(condition.getCode().hasCoding()) {

                conditions.add(
                    condition.getCode()
                    .getCodingFirstRep()
                    .getDisplay()
                );
            }
        }


        return conditions;
    }
}