package com.commerce.productcatalog.model.entity;

import com.commerce.productcatalog.model.dao.Review;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Link;

import java.util.List;

@Document
@Data
public class ReviewSet {

    @Id
    private String id;
    private String productId;
    private String ownerId;
    private List<Review> review;

    @Transient
    private Link link;

    public ReviewSet add(Link link) {
        this.link = link;
        return this;
    }
}
