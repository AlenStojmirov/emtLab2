//package mk.ukim.finki.emt.OnlineShop.web;
//
//import mk.ukim.finki.emt.OnlineShop.Service.ManufacturerService;
//import mk.ukim.finki.emt.OnlineShop.Service.ProductService;
//import mk.ukim.finki.emt.OnlineShop.exceptions.CategoryNotFoundException;
//import mk.ukim.finki.emt.OnlineShop.exceptions.ManufacturerNotFoundException;
//import mk.ukim.finki.emt.OnlineShop.exceptions.ProductNotFoundException;
//import mk.ukim.finki.emt.OnlineShop.Domain.models.Category;
//import mk.ukim.finki.emt.OnlineShop.Domain.models.Manufacturer;
//import mk.ukim.finki.emt.OnlineShop.Domain.models.Product;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
////@Controller
//@RequestMapping("/products")
//public class ProductController {
//
//    private ProductService productService;
//    private ManufacturerService manufacturerService;
//
//    public ProductController(ProductService productService, ManufacturerService manufacturerService) {
//        this.productService = productService;
//        this.manufacturerService = manufacturerService;
//    }
//
//
//    @GetMapping
//    public String products(Model model) {
//        model.addAttribute("productList",productService.getAllProducts());
//        model.addAttribute("manufacturerList",manufacturerService.getAll());
//        model.addAttribute("product", new Product());
//        return "products";
//    }
//
//
//    @ExceptionHandler({ProductNotFoundException.class})
//    @GetMapping("product/{id}")
//    public String getProduct(@PathVariable("id") Long id, Model model) throws Exception {
//        Product product = productList.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(ProductNotFoundException::new);
//        model.addAttribute("product", product);
//        return "product";
//    }
//
//    @GetMapping("/add")
//    public String addProduct(Model model){
//        model.addAttribute("product", new Product());
//        model.addAttribute("manufacturerList", manufacturers);
//        model.addAttribute("categories", categories);
//        return "product.form";
//    }
//
//    @ExceptionHandler({ManufacturerNotFoundException.class})
//    @PostMapping("/add")
//    public String addProductWithModelAttribute(@ModelAttribute Product d, Model model ){
//        Optional<Manufacturer> man = manufacturers.stream().filter(v->{
//            return v.getId().equals(d.getManufacturer().getId());
//        }).findAny();
//
//        Optional<Category> category = categories.stream().filter( c -> {
//            return c.getId().equals(d.getCategory().getId());
//        }).findAny();
//
//        if(!category.isPresent()){
//            throw new CategoryNotFoundException();
//        }
//        if (!man.isPresent()) {
//            throw new ManufacturerNotFoundException();
//        }
//        d.setId(getNextId());
//        d.setName(d.getName());
//        d.setOpis(d.getOpis());
//        d.setCategory(category.get());
//        d.setManufacturer(man.get());
//        d.setSlika(d.getSlika());
//        productList.add(d);
//        return "redirect:/products";
//    }
//
//    @DeleteMapping
//    public String deleteProduct(HttpServletRequest request) {
//        Long productId = Long.parseLong(request.getParameter("productId"));
//        productList.removeIf(v-> {
//            return v.getId().equals(productId);
//        });
//        return "redirect:/products";
//    }
//
//}
