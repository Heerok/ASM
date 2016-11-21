package digitalaxom.asm.Repository;

import digitalaxom.asm.db.Article;
import digitalaxom.asm.db.SattrasList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Heerok on 11-10-2016.
 */
public interface SattraRepository extends JpaRepository<SattrasList,Long> {

    SattrasList findByArticlesIn(Article articleId);
}
