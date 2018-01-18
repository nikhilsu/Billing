package com.billing.service;

import com.billing.helper.Response;
import com.billing.model.Patient;

import java.util.List;

public interface PatientService {
    Response<Integer> createNewPatient(String name, int age, String phoneNumber, String email);
    Response<List<Patient>> getByPhoneNumber(String phoneNumber);
    Response<Patient> getById(int patientId);
}
