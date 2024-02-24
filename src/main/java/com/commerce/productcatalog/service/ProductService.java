package com.commerce.productcatalog.service;

import com.commerce.productcatalog.model.dto.ProductDto;
import com.commerce.productcatalog.model.entity.Product;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> createProduct(ProductDto productDto);
    Mono<Product> updateProduct(String id, ProductDto productDto);
    Mono<Void> deleteProduct(String id);

}
