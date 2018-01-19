package com.billing.dao;

import com.billing.helper.Response;
import com.billing.model.Patient;

import java.util.List;

public interface PatientDao {
    Response<Integer> save(Patient patient);
    Response<List<Patient>> findByPhoneNumber(String phoneNumber);
    Response<Patient> findById(int patientId);
}
