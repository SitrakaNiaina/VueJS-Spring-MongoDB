package madadev.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import madadev.back.models.Product;
import madadev.back.services.Services;


@CrossOrigin(origins = { "http://localhost:8080"})
@RestController
@RequestMapping(value = "/Products")
public class Controllers {
    
    @Autowired
    private Services services;

    @RequestMapping(value ="/Add", method = RequestMethod.POST)
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        try {
            this.services.createProduct(product);
            return new ResponseEntity<>("success",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/Update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody String body) {
        try {
            this.services.updateProduct(id,body);
            return new ResponseEntity<>("success",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/Delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        try {
            this.services.deleteProduct(id);
            return new ResponseEntity<>("success",HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts() {
        try {
            List<Product> products = this.services.getProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
