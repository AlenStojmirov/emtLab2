package mk.ukim.finki.emt.OnlineShop.Repository;

import mk.ukim.finki.emt.OnlineShop.Domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p from Product p")
    List<Product> findAll();

    @Query(value = "select p from Product p where p.id = :id")
    Optional<Product> findById(@Param(value = "id") Long id);

    @Query(value = "select p from Product p where p.category.id = :categoryId")
    List<Product> findByCategoryId(@Param(value = "categoryId") Long id);

    @Query(value = "select p from Product p where p.category.id = :cat and p.manufacturer.id = :man")
    List<Product> findByCategoryAndManufacturer(@Param(value = "cat") Long catId, @Param(value = "man") Long manId);

    @Query(value = "select sum (p.cena) from Product p where p.category.id = :catId")
    Double calculatePrice(@Param(value = "catId") Long catId);

    void deleteById(Long id);
}
