package com.moneycare.coreservice.datamanagement;

import com.moneycare.coreservice.model.User;
import javafx.util.Pair;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCredentialsRepo extends MongoRepository<Pair<String, User>,String> {
}
