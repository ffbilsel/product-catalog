package com.commerce.productcatalog.model.entity;

import com.commerce.productcatalog.model.enums.CurrencySubset;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Product {

    @Id
    private String id;
    private String ownerName;
    private String ownerId;
    private double price;
    private CurrencySubset currency;
    private String description;
    private String name;
    private String discount;
    private double discountValue;
    private int quantity;
    private String categoryId;
    private String categoryName;
    private String reviewSetId;
    private int reviewCount;
    private String imageUrl;
    private Date date;
}