package mk.ukim.finki.emt.OnlineShop.Repository;

import mk.ukim.finki.emt.OnlineShop.Domain.models.Category;
import mk.ukim.finki.emt.OnlineShop.Domain.models.Manufacturer;
import mk.ukim.finki.emt.OnlineShop.Domain.models.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryInMemory {

    private static Long counter = 0L;
    private List<Product> productList = null;
    private static List<Manufacturer> manufacturers = Arrays.asList(
            new Manufacturer(1L, "Nike"),
            new Manufacturer(2L, "Adidas"),
            new Manufacturer(3L, "Puma"),
            new Manufacturer(4L, "Reebok")
    );
    private static List<Category> categories = Arrays.asList(
            new Category(1L, "Shoes"),
            new Category(2L, "T-shirt"),
            new Category(3L, "Cap")
    );

    @PostConstruct
    public void init(){
        productList = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(getNextId());
        p1.setName("Nike Air Max 1");
        p1.setOpis("Good shoes");
        p1.setSlika("https://i1.adis.ws/i/jpl/jd_065721_b?qlt=80&w=600&h=425&v=1");
        p1.setCategory(categories.get(0));
        p1.setManufacturer(manufacturers.get(0));
        productList.add(p1);

        Product p2 = new Product();
        p2.setId(getNextId());
        p2.setName("Air Force 1 Mid");
        p2.setOpis("Good shoes");
        p2.setSlika("https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803517_01.jpg");
        p2.setCategory(categories.get(0));
        p2.setManufacturer(manufacturers.get(0));
        productList.add(p2);

    }
    public Long getNextId() {
        return counter++;
    }

    public List<Product> findAllProducts() {
        return productList;
    }

    public Product save(Product product){
        product.setId(getNextId());
        productList.add(product);
        return product;
    }

    public void delete(Long productId){
        productList.removeIf(v -> {
            return v.getId().equals(productId);
        });
    }

    public Optional<Product> findById(Long productId){
        return productList.stream().filter(v -> v.getId().equals(productId)).findAny();
    }
}
