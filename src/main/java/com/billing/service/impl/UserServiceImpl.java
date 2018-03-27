package com.billing.service.impl;

import com.billing.dao.UserDao;
import com.billing.helper.Password;
import com.billing.helper.Response;
import com.billing.helper.validator.ModelValidator;
import com.billing.model.User;
import com.billing.model.UserRole;
import com.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ModelValidator modelValidator;
    private final Password passwordHash;

    @Autowired
    public UserServiceImpl(UserDao userDao, ModelValidator modelValidator, Password passwordHash) {
        this.userDao = userDao;
        this.modelValidator = modelValidator;
        this.passwordHash = passwordHash;
    }

    @Override
    public Response<Integer> createUser(String firstName, String lastName,
                                        String userId, String password, UserRole userRole) throws Exception {
        String salt = passwordHash.getNextSalt();
        String password_hash = passwordHash.hash(password, salt);
        if (userDao.findByUserId(userId).isSuccessful())
            return Response.Failure("User id already in use.");
        User user = new User();
        user.setFirstName(firstName)
                .setLastName(lastName)
                .setUserId(userId)
                .setPassword(password)
                .setSalt(salt)
                .setPasswordHash(password_hash)
                .setUserRole(userRole);
        Response userValidationResponse = modelValidator.validate(user);
        if (!userValidationResponse.isSuccessful())
            return Response.Failure(userValidationResponse.errors());
        return userDao.save(user);
    }

    @Override
    public Response<Integer> loginUser(String userId, String password) throws Exception{
        Response response = userDao.findByUserId(userId);
        if(!response.isSuccessful() || response.data() == null)
            return Response.Failure("User not found.");
        User user = (User) response.data();
        Response validationResponse = modelValidator.validate(user.setPassword(password));
        if (!validationResponse.isSuccessful()) {
            return Response.Failure(validationResponse.errors());
        }
        return Response.Success(user.getId());
    }

    @Override
    public Response changePassword(int userId, String newPassword) throws Exception {
        Response response = userDao.findById(userId);
        if(!response.isSuccessful() || response.data() == null)
            return Response.Failure("User not found.");
        String salt = passwordHash.getNextSalt();
        String password_hash = passwordHash.hash(newPassword, salt);
        User user = (User) response.data();
        user.setPassword(newPassword)
            .setPasswordHash(password_hash)
            .setSalt(salt);
        Response modelValidation = modelValidator.validate(user);
        if (modelValidation.isSuccessful())
            return userDao.update(user);
        else
            return modelValidation;
    }

    @Override
    public Response<User> getUserById(int userId) throws Exception {
        return Response.Success(userDao.findById(userId).data());
    }
}
