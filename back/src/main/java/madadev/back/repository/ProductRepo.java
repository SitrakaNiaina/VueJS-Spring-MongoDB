package madadev.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import madadev.back.models.Product;

public interface ProductRepo extends MongoRepository<Product, Integer> {

    @Query(value = "{'id': ?0}", delete = true)
    void deleteProduct(String id);
    
}
