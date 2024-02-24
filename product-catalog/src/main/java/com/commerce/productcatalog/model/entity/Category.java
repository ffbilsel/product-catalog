package com.commerce.productcatalog.model.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Link;

import java.util.List;

@Document
@Data
public class Category {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;
    private List<String> productIds;

}
