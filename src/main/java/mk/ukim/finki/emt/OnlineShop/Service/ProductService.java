package mk.ukim.finki.emt.OnlineShop.Service;

import mk.ukim.finki.emt.OnlineShop.Domain.models.Product;
import mk.ukim.finki.emt.OnlineShop.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.emt.OnlineShop.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product, Long manufacturerId, String username);
    Product addProduct(String name, Double cena, Long manufacturerId) throws ManufacturerNotFoundException;
    Product addProduct(String name, Double cena, Long manufacturerId, String slika) throws ManufacturerNotFoundException;
    Product addProduct(Product product, String username);

    List<Product> getAllProducts();

    Product update(Product product) throws ProductNotFoundException;

    void delete(Long deviceId);

    Product getById(Long deviceId);

    List<Product> getByCategory(Long categoryId);

    List<Product> getByCategoryAndManufacturer(Long categoryId, Long manufacturerId);

    Double sumOfPricesFromCategory(Long categoryId);
}
