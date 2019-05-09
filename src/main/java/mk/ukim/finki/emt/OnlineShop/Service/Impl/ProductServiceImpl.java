package mk.ukim.finki.emt.OnlineShop.Service.Impl;

import mk.ukim.finki.emt.OnlineShop.Domain.models.Manufacturer;
import mk.ukim.finki.emt.OnlineShop.Domain.models.Product;
import mk.ukim.finki.emt.OnlineShop.Repository.ProductRepository;
import mk.ukim.finki.emt.OnlineShop.Repository.ProductRepositoryInMemory;
import mk.ukim.finki.emt.OnlineShop.Service.ManufacturerService;
import mk.ukim.finki.emt.OnlineShop.Service.ProductService;
import mk.ukim.finki.emt.OnlineShop.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.emt.OnlineShop.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repo;
    private ManufacturerService manufacturerService;

    public ProductServiceImpl(ProductRepository repo, ManufacturerService manufacturerService) {
        this.repo = repo;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public Product addProduct(Product product, Long manufacturerId, String username) {
        Optional<Manufacturer> man = manufacturerService.getAll().stream().filter(v ->
                v.getId().equals(manufacturerId)).findAny();
        if(!man.isPresent()){
            throw new ManufacturerNotFoundException();
        }
        product.setManufacturer(man.get());
        return repo.save(product);
    }

    @Override
    public Product addProduct(String name, Double cena, Long manufacturerId) throws ManufacturerNotFoundException {
        Product p = new Product();
        p.setName(name);
        p.setCena(cena);
        Optional<Manufacturer> man = manufacturerService.getAll().stream().filter(
                v-> v.getId().equals(manufacturerId)).findAny();
        if(!man.isPresent()){
            throw new ManufacturerNotFoundException();
        }
        p.setManufacturer(man.get());
        repo.save(p);
        return p;
    }

    @Override
    public Product addProduct(String name, Double cena, Long manufacturerId, String slika) throws ManufacturerNotFoundException {
        Product p = new Product();
        p.setName(name);
        p.setCena(cena);
        Optional<Manufacturer> man = manufacturerService.getAll().stream().filter(
                v-> v.getId().equals(manufacturerId)).findAny();
        if(!man.isPresent()){
            throw new ManufacturerNotFoundException();
        }
        p.setManufacturer(man.get());
        p.setSlika(slika);
        repo.save(p);
        return p;
    }

    @Override
    public Product addProduct(Product product, String username) {
        return repo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Product update(Product product) throws ProductNotFoundException {
        return repo.save(product);
    }

    @Override
    public void delete(Long productId) {
        repo.deleteById(productId);
    }

    @Override
    public Product getById(Long productId) {
        Optional<Product> product = repo.findById(productId);
        if(!product.isPresent()){
            throw new ProductNotFoundException();
        }
        return product.get();
    }

    @Override
    public List<Product> getByCategory(Long categoryId) {
        return repo.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> getByCategoryAndManufacturer(Long categoryId, Long manufacturerId) {
        return repo.findByCategoryAndManufacturer(categoryId, manufacturerId);
    }

    @Override
    public Double sumOfPricesFromCategory(Long categoryId) {
        return repo.calculatePrice(categoryId);
    }
}
