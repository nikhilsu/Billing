package com.billing.service;

import com.billing.helper.Response;
import com.billing.model.Patient;

public interface PatientService {
    Response createNewPatient(String name, int age, String phoneNumber, String email);
    Response<Patient> getByPhoneNumber(String phoneNumber);
    Response<Patient> getById(int patientId);
}
