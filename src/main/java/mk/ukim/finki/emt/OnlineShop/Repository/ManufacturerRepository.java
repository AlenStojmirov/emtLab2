package mk.ukim.finki.emt.OnlineShop.Repository;

import mk.ukim.finki.emt.OnlineShop.Domain.models.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ManufacturerRepository {

    private Long counter = 0L;
    private static List<Manufacturer> manufacturers = Arrays.asList(
            new Manufacturer(1L, "Nike"),
            new Manufacturer(2L, "Adidas"),
            new Manufacturer(3L, "Puma"),
            new Manufacturer(4L, "Reebok")
    );

    @PostConstruct
    public void postConstruct(){
        manufacturers = new ArrayList<>();
        Manufacturer m = new Manufacturer();
        m.setId(getNextId());
        m.setMname("Converse");
        manufacturers.add(m);
    }

    public List<Manufacturer> findAll(){
        return manufacturers;
    }

    private Long getNextId(){
        return counter++;
    }
}
