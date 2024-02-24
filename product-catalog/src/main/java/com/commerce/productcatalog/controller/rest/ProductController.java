package com.commerce.productcatalog.controller.rest;

import com.commerce.productcatalog.model.entity.Product;
import com.commerce.productcatalog.model.dto.ProductDto;
import com.commerce.productcatalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Mono<EntityModel<Product>> createProduct(@RequestBody ProductDto productDto) {
        return prepareHateoas(productService.createProduct(productDto),
                List.of(linkTo(methodOn(ProductController.class).createProduct(productDto)).withSelfRel().toMono()));
    }

    @PutMapping("/{id}")
    public Mono<EntityModel<Product>> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        return prepareHateoas(productService.updateProduct(id, productDto),
                List.of(linkTo(methodOn(ProductController.class).updateProduct(id, productDto)).withSelfRel().toMono()));
    }

    @DeleteMapping("/{id}")
    public Mono<Link> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id).then(linkTo(methodOn(ProductController.class).deleteProduct(id)).withSelfRel().toMono());
    }

    private Mono<EntityModel<Product>> prepareHateoas(Mono<Product> monoUser, List<Mono<Link>> monoLinks) {
        return monoUser.flatMap(user -> Flux.concat(monoLinks)
                .collectList()
                .map(link -> EntityModel.of(user, link)));
    }

}
