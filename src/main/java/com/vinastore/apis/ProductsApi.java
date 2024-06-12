package com.vinastore.apis;

import com.vinastore.entities.Products;
import com.vinastore.service.ProductsService;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("public/api/v1/products")
public class ProductsApi {

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        ResponseEntity<?> result = productsService.getAllProduct();
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/pageable-sort")
    public Page<Products> getProductBySort(
            @RequestParam(name = "page",defaultValue = "1") int page,
            @RequestParam(name = "limit",defaultValue = "20") int limit,
            @RequestParam(name = "sort_by",defaultValue = "createdAt")String sortBy,
            @RequestParam(name = "order",defaultValue = "desc") String order,
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "category",required = false)String category,
            @RequestParam(name = "price_max",required = false)Integer priceMax,
            @RequestParam(name = "priceMin",required = false)Integer priceMin){
        return productsService.getFilteredProducts(page,limit,sortBy,name,category,priceMax,priceMin,order);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Integer id){
        ResponseEntity<?> products = productsService.getProductById(id);
        return ResponseEntity.status(200).body(products);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getProductBycategory(@RequestParam("id-category") Integer id){
        ResponseEntity<?> result = productsService.getProductByCatetory(id);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/name")
    public ResponseEntity<?> getProductByName(String name){
        ResponseEntity<?> result = productsService.getProductByName(name);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/price")
    public ResponseEntity<?> getProductByPrice(@RequestParam("priceMin")Double priceMin,
                                               @RequestParam("priceMax")Double priceMax){
        ResponseEntity<?> result = productsService.getProductByPrice(priceMin, priceMax);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/page")
    public Page<Products> getProductForPage(@RequestParam(name = "pageNumber", defaultValue = "1")Integer pageNumber,
                                            @RequestParam(name = "pageSize", defaultValue = "10")Integer pageSize){
        Page<Products> result = productsService.pageProduct(pageNumber,pageSize);
        return result;
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody Products productform)
    {
        ResponseEntity<?> products = productsService.saveProduct(productform);
        return ResponseEntity.status(200).body(products);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id")Integer id,
                                           @RequestBody Products formProduct) throws Exception {
        ResponseEntity<?> products = productsService.updateProduct(formProduct,id);
            return ResponseEntity.status(200).body(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id")Integer id)throws Exception{
        ResponseEntity<?> products = productsService.deleteProductById(id);
        return ResponseEntity.status(200).body(products);
    }
}
