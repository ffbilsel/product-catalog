package com.commerce.productcatalog.service.impl;

import com.commerce.productcatalog.model.entity.Product;
import com.commerce.productcatalog.repository.reactive.ProductRepository;
import com.commerce.productcatalog.model.dto.ProductDto;
import com.commerce.productcatalog.service.CategoryService;
import com.commerce.productcatalog.service.OwnerService;
import com.commerce.productcatalog.service.ProductService;
import com.commerce.productcatalog.service.ReviewSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;
    private final ReviewSetService reviewSetService;
    private final OwnerService ownerService;

    @Override
    public Mono<Product> createProduct(ProductDto productDto) {

        Product product = new Product();
        setCommon(productDto, product);
        product.setName(productDto.getName());
        product.setOwnerId(productDto.getOwnerId());
        product.setOwnerName(ownerService.getOwnerName(productDto.getOwnerId()));

        return productRepository.save(product).map(productT -> {
            productT.setReviewSetId(reviewSetService.createAndReturnId(productT.getId(), product.getOwnerId()));
            return productT;
        })
        .flatMap(productRepository::save);
    }

    @Override
    public Mono<Product> updateProduct(String id, ProductDto productDto) {
        return productRepository.findById(id)
                .map(product -> {
                    setCommon(productDto, product);
                    return product;
                })
                .flatMap(productRepository::save);
    }

    @Override
    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }

    private void setCommon(ProductDto productDto, Product product) {
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategoryId(productDto.getCategoryId());
        product.setCategoryName(categoryService.getCategoryName(productDto.getCategoryId()));
        product.setQuantity(productDto.getQuantity());
        product.setCurrency(productDto.getCurrency());
        product.setDiscount(productDto.getDiscount());
        product.setDiscountValue(productDto.getDiscountValue());
        product.setImageUrl(productDto.getImageUrl());
        product.setDate(new Date());
    }
}
