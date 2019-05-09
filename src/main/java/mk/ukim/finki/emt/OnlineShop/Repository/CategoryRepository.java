package mk.ukim.finki.emt.OnlineShop.Repository;

import mk.ukim.finki.emt.OnlineShop.Domain.models.Category;
import mk.ukim.finki.emt.OnlineShop.Domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
