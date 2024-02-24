package com.commerce.productcatalog.repository.conf;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.commerce.productcatalog.repository.blocking", mongoTemplateRef = "blockingRepositoryTemplate")
public class MongoBlockingConfig {

    @Value("${spring.data.mongodb.blocking.conn-string}")
    private String connString;

    @Value("${spring.data.mongodb.blocking.database}")
    private String database;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(connString);
    }

    // Define the MongoDbFactory bean
    @Bean
    public MongoDatabaseFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, database);
    }

    // Define the MongoTemplate bean
    @Bean
    public MongoTemplate blockingRepositoryTemplate(MongoDatabaseFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }

}