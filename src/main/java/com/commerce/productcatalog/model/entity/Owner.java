package com.commerce.productcatalog.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Link;

import java.util.List;

@Document
@Data
public class Owner {

    private String id;
    @Indexed(unique = true)
    private String name;
    private String email;
    private String phone;
    private String address;
    private double rating;
    private List<String> productIds;

    @Transient
    private Link link;

    public Owner add(Link link) {
        this.link = link;
        return this;
    }
}
