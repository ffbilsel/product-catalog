package com.commerce.productcatalog.repository.blocking;

import com.commerce.productcatalog.model.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
