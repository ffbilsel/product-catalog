package com.commerce.productcatalog.service.impl;

import com.commerce.productcatalog.repository.blocking.ReviewSetRepository;
import com.commerce.productcatalog.service.ReviewSetService;
import com.commerce.productcatalog.model.entity.ReviewSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewSetServiceImpl implements ReviewSetService {

    private final ReviewSetRepository reviewSetRepository;

    @Override
    public String createAndReturnId(String productId, String ownerId) {
        ReviewSet reviewSet = new ReviewSet();
        reviewSet.setProductId(productId);
        reviewSet.setOwnerId(ownerId);
        reviewSetRepository.save(reviewSet);
        return reviewSet.getId();
    }
}
