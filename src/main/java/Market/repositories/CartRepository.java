package Market.repositories;

import Market.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(value = "select * from cart where itemname like '%'||?1||'%'", nativeQuery = true)
    List<Cart> findAllPersonLikeUsername(String chars);
}
