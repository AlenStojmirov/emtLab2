package mk.ukim.finki.emt.OnlineShop.web;

import mk.ukim.finki.emt.OnlineShop.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.emt.OnlineShop.models.Category;
import mk.ukim.finki.emt.OnlineShop.models.Manufacturer;
import mk.ukim.finki.emt.OnlineShop.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private static Long counter = 0L;
    private List<Product> productList = null;
    private static List<Manufacturer> manufacturers = Arrays.asList(new Manufacturer(1L, "Nike"), new Manufacturer(2L, "Adidas"));
    private static List<Category> categories = Arrays.asList(new Category(1L, "patiki"), new Category(2L, "vlecki"));

    @PostConstruct
    public void init(){
        productList = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(getNextId());
        p1.setName("Nike 18");
        p1.setOpis("Model 2018");
        p1.setSlika("https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/m5g458pdbmvl0rubcsj0/free-rn-2018-running-shoe-n0NTrb.jpg");
        productList.add(p1);

        Product p2 = new Product();
        p2.setId(getNextId());
        p2.setName("Nike 19");
        p2.setOpis("Model 2019");
        p2.setSlika("https://fm.cnbc.com/applications/cnbc.com/resources/img/editorial/2019/01/15/105680013-1547583426762nike1.530x298.jpg?v=1547583682");
        productList.add(p2);

    }

    public Long getNextId() {
        return counter++;
    }

    @GetMapping
    public String devices(Model model) {
        model.addAttribute("productList",productList);
        return "products";
    }

    @GetMapping("product/{id}")
    public String getProduct(@PathVariable("id") Integer id, Model model){
        model.addAttribute("product", productList.get(id));
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
            throw new ManufacturerNotFoundException();
        }
        if (!man.isPresent()) {
            throw new ManufacturerNotFoundException();
        }
        d.setId(getNextId());
        d.setCategory(category.get());
        d.setManufacturer(man.get());
        productList.add(d);
        return "redirect:/products";
    }
}
