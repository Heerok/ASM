package digitalaxom.asm.db;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by Heerok on 11-10-2016.
 */
@MappedSuperclass
public class BaseModel {

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    private Date createdDate;

    private Date updatedDate;

    public Long getId() {
        return Id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    private void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    private void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setId(Long id) {
        Id = id;
    }

    @PrePersist
    void onCreate(){
        this.setCreatedDate(new Date());
    }

    @PreUpdate
    void onPersist(){
        this.setUpdatedDate(new Date());
    }
}
