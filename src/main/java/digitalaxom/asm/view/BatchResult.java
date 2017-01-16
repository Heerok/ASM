package digitalaxom.asm.view;

/**
 * Created by Heerok on 16-01-2017.
 */
public class BatchResult {

    public String key;
    public String value;
    public String status;

    public BatchResult(String key, String value, String status) {
        this.key = key;
        this.value = value;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
