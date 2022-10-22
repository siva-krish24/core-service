package com.moneycare.coreservice.datamanagement;

import com.moneycare.coreservice.datamanagement.wrapper.RatingPair;
import com.moneycare.coreservice.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepo extends MongoRepository<RatingPair,String> {
}
