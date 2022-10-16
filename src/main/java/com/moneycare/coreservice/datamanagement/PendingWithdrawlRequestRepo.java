package com.moneycare.coreservice.datamanagement;


import com.moneycare.coreservice.model.ApprovalWithdrawRequest;
import javafx.util.Pair;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PendingWithdrawlRequestRepo extends MongoRepository<Pair<String, ApprovalWithdrawRequest>,String> {
}
