package com.commerce.productcatalog.repository.reactive;

import com.commerce.productcatalog.model.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, String> {

}
