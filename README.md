# FHIR Clinical Intelligence Platform

## Overview

The **FHIR Clinical Intelligence Platform** is a Java Spring Boot application that integrates with a **HAPI FHIR R4 Server** to retrieve and aggregate patient clinical data into a unified **Patient 360 Dashboard**. The application demonstrates healthcare interoperability by consuming multiple FHIR resources and generating simple rule-based clinical insights.

## Features

* Search patients by name
* Retrieve FHIR Patient resources
* Display active Conditions
* Display current Medication Requests
* Display Clinical Observations
* Generate rule-based Clinical Alerts
* Calculate Patient Risk Level
* Display a Clinical Summary
* Responsive web interface using Thymeleaf and Bootstrap

## FHIR Resources Used

* Patient
* Condition
* MedicationRequest
* Observation

## Technology Stack

* Java 17
* Spring Boot
* Maven
* HL7 FHIR R4
* HAPI FHIR Client
* Thymeleaf
* Bootstrap 5
* Eclipse IDE

## Architecture

```text
User
   │
   ▼
Spring Boot Application
   │
   ├── Patient Service
   ├── Condition Service
   ├── Medication Service
   ├── Observation Service
   └── Clinical Insight Service
   │
   ▼
HAPI FHIR R4 Server
```

## Application Workflow

1. Search for a patient by name.
2. Retrieve Patient demographics.
3. Fetch associated Condition, MedicationRequest, and Observation resources.
4. Aggregate clinical data into a Patient 360 Dashboard.
5. Generate rule-based clinical alerts, risk level, and summary.

## Sample Dashboard

> Add your dashboard screenshot here.

## Learning Outcomes

* Built a healthcare interoperability application using HL7 FHIR R4.
* Integrated Spring Boot with the HAPI FHIR Server.
* Consumed multiple FHIR resources using RESTful APIs.
* Implemented a modular service-based architecture for FHIR resource retrieval.
* Developed a rule-based clinical intelligence layer for patient insights.

## Future Enhancements

* Support additional FHIR resources such as Encounter, AllergyIntolerance, and DiagnosticReport.
* Implement SMART on FHIR authentication.
* Add trend visualization for clinical observations.
* Integrate AI-powered clinical summarization.
