package digitalaxom.asm.Repository;

import digitalaxom.asm.db.Product;
import digitalaxom.asm.db.ScrappingResponse;
import digitalaxom.asm.db.WebScraping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Heerok on 13-01-2017.
 */
public interface ScrappingResponseRepository extends JpaRepository<ScrappingResponse,Long> {

    List<ScrappingResponse> findByWebScrapingProduct(Product product);

    int countByWebScraping(WebScraping scraping);

    ScrappingResponse findTopByWebScrapingOrderByCreatedDateDesc(WebScraping scraping);

}
