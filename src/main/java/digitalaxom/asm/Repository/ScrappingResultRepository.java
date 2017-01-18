package digitalaxom.asm.Repository;

import digitalaxom.asm.db.ScrappingResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Heerok on 16-01-2017.
 */
public interface ScrappingResultRepository extends JpaRepository<ScrappingResult,Long> {
}
