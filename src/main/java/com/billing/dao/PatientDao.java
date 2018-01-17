package com.billing.dao;

import com.billing.helper.Response;
import com.billing.model.Patient;

public interface PatientDao {
    Response<Integer> save(Patient patient);
    Response update(Patient patient);
    Response<Patient> findByPhoneNumber(String phoneNumber);
}
