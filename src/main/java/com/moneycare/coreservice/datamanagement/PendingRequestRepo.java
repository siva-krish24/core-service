package com.moneycare.coreservice.datamanagement;

import com.moneycare.coreservice.model.ApprovalRequest;
import javafx.util.Pair;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PendingRequestRepo extends MongoRepository<Pair<String, ApprovalRequest>,String> {
}
