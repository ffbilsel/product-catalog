package com.commerce.productcatalog.model.dto;

import com.commerce.productcatalog.model.enums.CurrencySubset;
import lombok.Data;

@Data
public class ProductDto {

    private String ownerId;
    private double price;
    private CurrencySubset currency;
    private String description;
    private String name;
    private String discount;
    private double discountValue;
    private int quantity;
    private String categoryId;
    private String imageUrl;
}
