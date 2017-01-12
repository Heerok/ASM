package digitalaxom.asm.Repository;

import digitalaxom.asm.db.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Heerok on 10-12-2016.
 */
public interface GalleryRepository extends JpaRepository<Gallery,Long> {
}
