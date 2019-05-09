package mk.ukim.finki.emt.OnlineShop.web.Rest;

import mk.ukim.finki.emt.OnlineShop.Domain.models.Product;
import mk.ukim.finki.emt.OnlineShop.Service.ProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping("/products/{id}")
    public Product getById(@PathVariable(value = "id") Long id){
        return productService.getById(id);
    }

    @RequestMapping("/product/category/{categoryId}")
    public List<Product> getByCategory(@PathVariable(value = "categoryId") Long categoryId){
        return productService.getByCategory(categoryId);
    }

    @RequestMapping("/product/category/{categoryId}/manufacturer/{manufacturerId}")
    public List<Product> getByCatAndMan(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "manufacturerId") Long id){
        return productService.getByCategoryAndManufacturer(categoryId, id);
    }

    @RequestMapping("/product/category/{categoryId}/price")
    public Double getPrice(@PathVariable(value = "categoryId") Long categoryId){
        return productService.sumOfPricesFromCategory(categoryId);
    }
}
