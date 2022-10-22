package com.moneycare.coreservice.datamanagement;

import com.moneycare.coreservice.datamanagement.wrapper.UserCredentialsPair;
import com.moneycare.coreservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCredentialsRepo extends MongoRepository<UserCredentialsPair,String> {
}
