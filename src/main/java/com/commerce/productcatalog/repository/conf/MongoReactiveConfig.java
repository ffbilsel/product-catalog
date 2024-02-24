package com.commerce.productcatalog.repository.conf;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.commerce.productcatalog.repository.reactive")
public class MongoReactiveConfig {

    @Value("${spring.data.mongodb.reactive.conn-string}")
    private String connString;
    @Value("${spring.data.mongodb.reactive.database}")
    private String database;

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(ReactiveMongoDatabaseFactory reactiveMongoDbFactory) {
        return new ReactiveMongoTemplate(reactiveMongoDbFactory);
    }

    // Define the ReactiveMongoDatabaseFactory bean
    @Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDbFactory(MongoClient reactiveMongoClient) {
        return new SimpleReactiveMongoDatabaseFactory(reactiveMongoClient, database);
    }

    // Define the MongoClient bean
    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(connString);
    }
}