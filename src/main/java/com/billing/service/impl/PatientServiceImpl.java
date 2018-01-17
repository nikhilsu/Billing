package com.billing.service.impl;

import com.billing.dao.PatientDao;
import com.billing.helper.Response;
import com.billing.helper.validator.ModelValidator;
import com.billing.model.Patient;
import com.billing.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientDao patientDao;
    private final ModelValidator modelValidator;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao, ModelValidator modelValidator) {
        this.patientDao = patientDao;
        this.modelValidator = modelValidator;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response createNewPatient(String name, int age, String phoneNumber, String email) {
        Patient patient = new Patient().setName(name)
                                        .setAge(age)
                                        .setPhoneNumber(phoneNumber)
                                        .setEmail(email);

        Response patientValidation = modelValidator.validate(patient);
        if(!patientValidation.isSuccessful())
            return Response.Failure(patientValidation.errors());
        return patientDao.save(patient);
    }

    @Override
    public Response<Patient> getByPhoneNumber(String phoneNumber) {
        return patientDao.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Response<Patient> getById(int patientId) {
        return patientDao.findById(patientId);
    }
}
