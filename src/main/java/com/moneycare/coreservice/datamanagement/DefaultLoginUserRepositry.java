package com.moneycare.coreservice.datamanagement;

import com.moneycare.coreservice.datamanagement.wrapper.DefaulLoginPair;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DefaultLoginUserRepositry extends MongoRepository<DefaulLoginPair,String> {
}
