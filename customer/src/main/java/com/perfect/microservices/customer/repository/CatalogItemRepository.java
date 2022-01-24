package com.perfect.microservices.customer.repository;

import com.perfect.microservices.customer.model.CatalogItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "items", collectionResourceRel = "items")
public interface CatalogItemRepository extends PagingAndSortingRepository<CatalogItem, Integer> {

    CatalogItem findByCatalogName(@Param("catalogName") String catalogName);
}
