package com.moneycare.coreservice.datamanagement;

import com.moneycare.coreservice.datamanagement.wrapper.PendingRequestPair;
import com.moneycare.coreservice.model.ApprovalRequest;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PendingRequestRepo extends MongoRepository<PendingRequestPair,String> {
}
