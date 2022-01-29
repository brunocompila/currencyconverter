package com.bruno.currencyconverter.repository;

import com.bruno.currencyconverter.domain.Convertion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvertionRepository extends ReactiveMongoRepository<Convertion, Long> {
}
