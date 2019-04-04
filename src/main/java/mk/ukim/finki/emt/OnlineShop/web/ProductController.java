package mk.ukim.finki.emt.OnlineShop.web;

import mk.ukim.finki.emt.OnlineShop.exceptions.CategoryNotFoundException;
import mk.ukim.finki.emt.OnlineShop.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.emt.OnlineShop.exceptions.ProductNotFoundException;
import mk.ukim.finki.emt.OnlineShop.models.Category;
import mk.ukim.finki.emt.OnlineShop.models.Manufacturer;
import mk.ukim.finki.emt.OnlineShop.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

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

    @GetMapping
    public String products(Model model) {
        model.addAttribute("productList",productList);
        return "products";
    }
    @ExceptionHandler({ProductNotFoundException.class})
    @GetMapping("product/{id}")
    public String getProduct(@PathVariable("id") Long id, Model model) throws Exception {
        Product product = productList.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(ProductNotFoundException::new);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("manufacturerList", manufacturers);
        model.addAttribute("categories", categories);
        return "product.form";
    }

    @ExceptionHandler({ManufacturerNotFoundException.class})
    @PostMapping("/add")
    public String addProductWithModelAttribute(@ModelAttribute Product d, Model model ){
        Optional<Manufacturer> man = manufacturers.stream().filter(v->{
            return v.getId().equals(d.getManufacturer().getId());
        }).findAny();

        Optional<Category> category = categories.stream().filter( c -> {
            return c.getId().equals(d.getCategory().getId());
        }).findAny();

        if(!category.isPresent()){
            throw new CategoryNotFoundException();
        }
        if (!man.isPresent()) {
            throw new ManufacturerNotFoundException();
        }
        d.setId(getNextId());
        d.setName(d.getName());
        d.setOpis(d.getOpis());
        d.setCategory(category.get());
        d.setManufacturer(man.get());
        d.setSlika(d.getSlika());
        productList.add(d);
        return "redirect:/products";
    }

    @DeleteMapping
    public String deleteProduct(HttpServletRequest request) {
        Long productId = Long.parseLong(request.getParameter("productId"));
        productList.removeIf(v-> {
            return v.getId().equals(productId);
        });
        return "redirect:/products";
    }

}
