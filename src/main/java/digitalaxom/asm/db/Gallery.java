package digitalaxom.asm.db;

import javax.persistence.Entity;

/**
 * Created by Heerok on 10-12-2016.
 */
@Entity
public class Gallery extends BaseModel{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
