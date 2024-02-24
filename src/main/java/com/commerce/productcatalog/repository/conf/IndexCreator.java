package com.commerce.productcatalog.repository.conf;

import com.commerce.productcatalog.model.entity.Category;
import com.commerce.productcatalog.model.entity.Owner;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Component;

@Component
public class IndexCreator {

    private final MongoTemplate mongoTemplate;

    public IndexCreator(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        createIndexes();
    }

    private void createIndexes() {
        mongoTemplate.indexOps(Category.class)
                .ensureIndex(new Index().on("name", Sort.Direction.ASC).unique());
        mongoTemplate.indexOps(Owner.class)
                .ensureIndex(new Index().on("name", Sort.Direction.ASC).unique());
    }
}

