package digitalaxom.asm.db;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Heerok on 10-12-2016.
 */
@Entity
public class GalleryImages extends BaseModel {

    @ManyToOne
    private Gallery gallery;

    private String imgPath;
    private String imageDate;
    private String caption;

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImageDate() {
        return imageDate;
    }

    public void setImageDate(String imageDate) {
        this.imageDate = imageDate;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
