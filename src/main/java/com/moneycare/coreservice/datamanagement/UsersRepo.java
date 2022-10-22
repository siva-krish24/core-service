package com.moneycare.coreservice.datamanagement;

import com.moneycare.coreservice.datamanagement.wrapper.UsersPair;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepo extends MongoRepository<UsersPair,String> {
}
