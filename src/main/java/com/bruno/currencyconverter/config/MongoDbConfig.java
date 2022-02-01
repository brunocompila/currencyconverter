package com.bruno.currencyconverter.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
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

}
