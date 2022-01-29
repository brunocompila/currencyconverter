package com.bruno.currencyconverter.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.bruno.currencyconverter.repository")
public class MongoDbConfig extends AbstractReactiveMongoConfiguration {

    @Value("${mongo.dbname}")
    private String dbName;

    @Value("${mongo.urlconection}")
    private String urlConection;

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(urlConection);
    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

//    @Bean
//    public ReactiveMongoTemplate reactiveMongoTemplate() {
//        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
//    }


}
