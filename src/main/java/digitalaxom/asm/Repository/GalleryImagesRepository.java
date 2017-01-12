package digitalaxom.asm.Repository;

import digitalaxom.asm.db.GalleryImages;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Heerok on 10-12-2016.
 */
public interface GalleryImagesRepository extends JpaRepository<GalleryImages,Long> {
}
