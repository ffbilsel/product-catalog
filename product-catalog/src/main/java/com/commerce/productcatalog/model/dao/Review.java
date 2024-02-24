package com.commerce.productcatalog.model.dao;

import lombok.Data;

@Data
public class Review {

    private double rating;
    private String review;
    private String title;
    private String date;

}
