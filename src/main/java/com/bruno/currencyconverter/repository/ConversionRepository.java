package com.bruno.currencyconverter.repository;

import com.bruno.currencyconverter.domain.ConversionTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionRepository extends ReactiveMongoRepository<ConversionTransaction, Long> {
}
