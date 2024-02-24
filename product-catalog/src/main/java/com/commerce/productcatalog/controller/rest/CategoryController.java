package com.commerce.productcatalog.controller.rest;

import com.commerce.productcatalog.model.dto.CategoryDto;
import com.commerce.productcatalog.model.entity.Category;
import com.commerce.productcatalog.model.query.PageQuery;
import com.commerce.productcatalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;


@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public Mono<EntityModel<Category>> create(@RequestBody CategoryDto categoryDto) {
        return prepareHateoas(Mono.just(categoryService.createCategory(categoryDto)),
                List.of(linkTo(methodOn(CategoryController.class).create(categoryDto)).withSelfRel().toMono()));
    }

    @PutMapping("/{id}")
    public Mono<EntityModel<Category>> update(@PathVariable String id, @RequestBody CategoryDto categoryDto) {
        return prepareHateoas(Mono.just(categoryService.updateCategory(id, categoryDto)),
                List.of(linkTo(methodOn(CategoryController.class).update(id, categoryDto)).withSelfRel().toMono()));
    }

    @DeleteMapping("/{id}")
    public Mono<Link> delete(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return linkTo(methodOn(ProductController.class).deleteProduct(id)).withSelfRel().toMono();
    }

    @GetMapping("/{id}")
    public Mono<EntityModel<Category>> get(@PathVariable String id) {
        return prepareHateoas(Mono.just(categoryService.getCategory(id)),
                List.of(linkTo(methodOn(CategoryController.class).get(id)).withSelfRel().toMono()));
    }


    @PostMapping("/page")
    public Flux<EntityModel<Category>> getAllByPage(@RequestBody PageQuery pageQuery) {
        return prepareHateoas(Flux.fromIterable(categoryService.getCategories(pageQuery)),
                List.of(linkTo(methodOn(CategoryController.class).getAllByPage(pageQuery)).withSelfRel().toMono()));
    }

    @PostMapping("/sort/{sortBy}/{sortOrder}")
    public Flux<EntityModel<Category>> getAllSorted(@PathVariable String sortBy, @PathVariable String sortOrder) {
        return prepareHateoas(Flux.fromIterable(categoryService.getCategoriesSorted(sortBy, sortOrder)),
                List.of(linkTo(methodOn(CategoryController.class).getAllSorted(sortBy, sortOrder)).withSelfRel().toMono()));
    }

    private Flux<EntityModel<Category>> prepareHateoas(Flux<Category> monoUser, List<Mono<Link>> monoLinks) {
        return monoUser.flatMap(user -> Flux.concat(monoLinks)
                .collectList()
                .map(link -> EntityModel.of(user, link)));
    }

    private Mono<EntityModel<Category>> prepareHateoas(Mono<Category> monoUser, List<Mono<Link>> monoLinks) {
        return monoUser.flatMap(user -> Flux.concat(monoLinks)
                .collectList()
                .map(link -> EntityModel.of(user, link)));
    }

}
