package digitalaxom.asm.Repository;

import digitalaxom.asm.db.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Heerok on 15-10-2016.
 */
public interface ArticleRepository extends JpaRepository<Article,Long> {

    List<Article> findByActiveTrue();
    List<Article> findByActiveTrueAndLanguage(String lang);

}
