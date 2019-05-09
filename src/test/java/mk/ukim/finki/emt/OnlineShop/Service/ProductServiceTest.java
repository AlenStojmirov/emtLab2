package mk.ukim.finki.emt.OnlineShop.Service;

import mk.ukim.finki.emt.OnlineShop.Domain.models.Product;
import mk.ukim.finki.emt.OnlineShop.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    Product product;

    @BeforeEach
    public void init(){
        product = new Product();
        product.setSlika("asddsa");
    }

    @Test
    @Transactional
    void addProduct() {
        productRepository.save(product);
        List<Product> productList = productRepository.findAll();
        assertEquals(1L, productList.size());
    }

    @Test
    @Transactional
    void getAllProducts() {
        productRepository.save(product);
        productRepository.save(new Product());

        assertEquals(productRepository.findAll().size(), 2L);
    }

    @Test
    void update() {

    }

    @org.junit.jupiter.api.Test
    void delete() {
    }

    @org.junit.jupiter.api.Test
    void getById() {
    }

    @org.junit.jupiter.api.Test
    void getByCategory() {
    }

    @org.junit.jupiter.api.Test
    void getByCategoryAndManufacturer() {
    }

    @org.junit.jupiter.api.Test
    void sumOfPricesFromCategory() {
    }

}