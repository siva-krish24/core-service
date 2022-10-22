package com.moneycare.coreservice.datamanagement;


import com.moneycare.coreservice.datamanagement.wrapper.PendingWithdrawlRequestPair;
import com.moneycare.coreservice.model.ApprovalWithdrawRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PendingWithdrawlRequestRepo extends MongoRepository<PendingWithdrawlRequestPair,String> {
}
