package madadev.back.services;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import madadev.back.models.Product;
import madadev.back.repository.ProductRepo;

@Service
public class Services {
    
    @Autowired
    private ProductRepo productRepo;

    public void createProduct(Product product) {
        this.productRepo.save(product);
    }
    
    public void updateProduct(String id, String body) {
        JSONObject json = new JSONObject(body);
        Product p = new Product(id,json.getString("name"),json.getDouble("price"),json.getString("description"));
        this.productRepo.save(p);
    }
    
    public void deleteProduct(String id) {
        this.productRepo.deleteProduct(id);
    }
    
    public List<Product> getProducts() {
        return this.productRepo.findAll();
    }
}
