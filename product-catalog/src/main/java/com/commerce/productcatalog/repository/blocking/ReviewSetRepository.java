package com.commerce.productcatalog.repository.blocking;

import com.commerce.productcatalog.model.entity.ReviewSet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewSetRepository extends MongoRepository<ReviewSet, String> {
}
