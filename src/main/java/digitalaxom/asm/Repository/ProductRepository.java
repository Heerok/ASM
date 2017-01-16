package digitalaxom.asm.Repository;

import digitalaxom.asm.db.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Heerok on 13-01-2017.
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findOneByCode(String code);
}
