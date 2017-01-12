package digitalaxom.asm.service;

import digitalaxom.asm.Repository.GalleryImagesRepository;
import digitalaxom.asm.Repository.GalleryRepository;
import digitalaxom.asm.db.Gallery;
import digitalaxom.asm.db.GalleryImages;
import digitalaxom.asm.view.GalleryDTO;
import digitalaxom.asm.view.GalleryImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Heerok on 10-12-2016.
 */
@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private GalleryImagesRepository galleryImagesRepository;

    public void createGallery(GalleryDTO dto, HttpServletRequest request){
        Gallery gallery;
        if(dto.id!=null)
            gallery = galleryRepository.findOne(dto.id);
        else
            gallery = new Gallery();

        gallery.setName(dto.name);

        if(!CollectionUtils.isEmpty(dto.imageDTOS)){
            for(GalleryImageDTO imageDTO:dto.imageDTOS){
                GalleryImages images;
                if(imageDTO.id!=null)
                    images = galleryImagesRepository.findOne(imageDTO.id);
                else
                    images = new GalleryImages();
                images.setCaption(imageDTO.caption);
                if(!imageDTO.file.isEmpty()){
                    String[] fileName = imageDTO.file.getOriginalFilename().split("\\.");
                    if(fileName.length>1){
                        try {
                            String filename=request.getServletContext().getRealPath("/articles/")+imageDTO.file.getOriginalFilename();

                            byte[] bytes = imageDTO.file.getBytes();
                            BufferedOutputStream stream =
                                    new BufferedOutputStream(new FileOutputStream(new File(filename)));
                            stream.write(bytes);
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        images.setImgPath(imageDTO.file.getOriginalFilename());
                    }

                }

            }
        }


    }
}
