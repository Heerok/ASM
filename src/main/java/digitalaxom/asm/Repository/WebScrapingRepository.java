package digitalaxom.asm.Repository;

import digitalaxom.asm.db.WebScraping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Heerok on 13-01-2017.
 */
public interface WebScrapingRepository extends JpaRepository<WebScraping,Long> {

    WebScraping findByProductCodeAndVendorAndUrl(String code, String vendor, String url);
    List<WebScraping> findByProductCode(String code);

}
