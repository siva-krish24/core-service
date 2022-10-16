package com.moneycare.coreservice.datamanagement;

import com.moneycare.coreservice.model.Rating;
import javafx.util.Pair;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepo extends MongoRepository<Pair<String, Rating>,String> {
}
