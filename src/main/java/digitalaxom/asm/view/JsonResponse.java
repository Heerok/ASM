package digitalaxom.asm.view;

/**
 * Created by Heerok on 18-11-2016.
 */
public class JsonResponse {

    public Boolean status;
    public String message;

    public JsonResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
