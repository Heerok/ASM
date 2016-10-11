package digitalaxom.asm.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Heerok on 04-10-2016.
 */
@Entity
public class Events extends BaseModel{

    private String eventName;

    private String eventDetails;

    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }
}
