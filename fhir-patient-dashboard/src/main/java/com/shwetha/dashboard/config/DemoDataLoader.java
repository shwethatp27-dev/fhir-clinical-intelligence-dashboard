package com.shwetha.dashboard.config;

import org.hl7.fhir.r4.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.uhn.fhir.rest.client.api.IGenericClient;

@Component
public class DemoDataLoader implements CommandLineRunner {


    private final IGenericClient client;


    public DemoDataLoader(IGenericClient client) {
        this.client = client;
    }


    @Override
    public void run(String... args) throws Exception {

    	 Bundle existingPatients = client.search()
    	            .forResource(Patient.class)
    	            .where(Patient.FAMILY.matches().value("Anderson"))
    	            .returnBundle(Bundle.class)
    	            .execute();


    	    if (!existingPatients.getEntry().isEmpty()) {

    	        System.out.println("Demo patient already exists. Skipping creation.");

    	        return;
    	    }

        System.out.println("Creating Demo Patient Data...");


        Patient patient = new Patient();

        patient.addName()
                .setFamily("Anderson")
                .addGiven("James");


        patient.setGender(
                Enumerations.AdministrativeGender.MALE
        );


        patient.setBirthDateElement(
                new DateType("1970-05-15")
        );


        Patient savedPatient =
                (Patient) client.create()
		.resource(patient)
		.execute()
		.getResource();


        String patientId =
                savedPatient.getIdElement()
                .getIdPart();


        System.out.println(
            "Created Patient ID : " + patientId
        );


        createCondition(
                patientId,
                "Type 2 Diabetes Mellitus"
        );


        createCondition(
                patientId,
                "Hypertension"
        );


        createObservation(
                patientId,
                "HbA1c",
                8.2,
                "%"
        );


        createObservation(
                patientId,
                "Systolic Blood Pressure",
                150,
                "mmHg"
        );


        createObservation(
                patientId,
                "Heart Rate",
                78,
                "beats/min"
        );


        createMedication(
                patientId,
                "Metformin 500mg"
        );


        createMedication(
                patientId,
                "Lisinopril 10mg"
        );


        System.out.println(
            "Demo Patient Creation Completed"
        );

    }



    private void createCondition(
            String patientId,
            String conditionName) {


        Condition condition =
                new Condition();


        condition.setSubject(
                new Reference(
                    "Patient/" + patientId
                )
        );


        condition.getCode()
                .setText(conditionName);


        client.create()
        .resource(condition)
        .execute();

    }



    private void createObservation(
            String patientId,
            String name,
            double value,
            String unit) {


        Observation observation =
                new Observation();


        observation.setSubject(
            new Reference(
                "Patient/" + patientId
            )
        );


        observation.getCode()
        .setText(name);


        observation.setValue(
            new Quantity()
            .setValue(value)
            .setUnit(unit)
        );


        client.create()
        .resource(observation)
        .execute();

    }



    private void createMedication(
            String patientId,
            String medicationName) {


        MedicationRequest medication =
                new MedicationRequest();


        medication.setSubject(
            new Reference(
                "Patient/" + patientId
            )
        );


        medication
        .getMedicationCodeableConcept()
        .setText(medicationName);


        medication.setStatus(
            MedicationRequest.MedicationRequestStatus.ACTIVE
        );


        medication.setIntent(
            MedicationRequest.MedicationRequestIntent.ORDER
        );


        client.create()
        .resource(medication)
        .execute();

    }

}