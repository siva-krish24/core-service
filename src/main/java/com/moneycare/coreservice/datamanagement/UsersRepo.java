package com.moneycare.coreservice.datamanagement;

import javafx.util.Pair;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepo extends MongoRepository<Pair<String, String>,String> {
}