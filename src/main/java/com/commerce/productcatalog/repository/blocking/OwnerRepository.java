package com.commerce.productcatalog.repository.blocking;

import com.commerce.productcatalog.model.entity.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OwnerRepository extends MongoRepository<Owner, String> {
}
