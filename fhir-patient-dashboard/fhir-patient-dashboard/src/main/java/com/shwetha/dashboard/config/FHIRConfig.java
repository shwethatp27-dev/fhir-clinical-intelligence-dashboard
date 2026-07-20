package com.shwetha.dashboard.config;

import org.hl7.fhir.r4.model.Patient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;

@Configuration
public class FHIRConfig {

    private static final String FHIR_SERVER =
            "https://hapi.fhir.org/baseR4";

    @Bean
    public FhirContext fhirContext() {
        return FhirContext.forR4();
    }

    @Bean
    public IGenericClient fhirClient(FhirContext context) {
        return context.newRestfulGenericClient(FHIR_SERVER);
    }

}