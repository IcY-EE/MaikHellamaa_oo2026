package ee.maik.veebipood.controller;
import ee.maik.veebipood.entity.Product;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import respository.ProductRespository;

import java.util.List;

@RestController
public class ProductController {

//    @GetMapping("/products")
//    public String getProducts() {
//        return "Hello World";
//    }
//}
    @Autowired
    private ProductRespository productRespository;

@GetMapping("/products")
public List<product> getProducts() {
    return productRespository.findAll();
}

@DeleteMapping ("/products/{id}")
public List<product> deleteProduct(@PathVariable long id) {
    productRespository.deleteById(id);
    return productRespository.findAll();
}

    @PostMapping ("/products/{id}")
    public Product addProduct(@RequestBody Product product) {
        productRespository.save(product);
        return productRespository.findAll();
    }

}


