package com.shwetha.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ClinicalInsightService {


    public List<String> generateAlerts(
            List<String> conditions,
            List<String> observations) {


        List<String> alerts = new ArrayList<>();


        for(String observation : observations) {


            if(observation.contains("HbA1c")) {

                if(observation.contains("8.2")) {

                    alerts.add(
                    "Diabetes control is poor. HbA1c is above target."
                    );
                }
            }


            if(observation.contains("Systolic Blood Pressure")) {

                if(observation.contains("150")) {

                    alerts.add(
                    "High blood pressure detected. Monitoring required."
                    );
                }
            }
        }


        if(conditions.size() >= 2) {

            alerts.add(
            "Patient has multiple chronic conditions."
            );

        }


        return alerts;
    }



    public String calculateRiskLevel(
            List<String> alerts) {


        if(alerts.size() >= 3) {

            return "HIGH";

        } else if(alerts.size() >= 1) {

            return "MODERATE";

        }

        return "LOW";

    }



    public String generateSummary(
            List<String> conditions,
            List<String> alerts) {


        return 
        "Patient has "
        + conditions.size()
        + " chronic conditions. "
        + alerts.size()
        + " clinical alerts identified.";

    }

}