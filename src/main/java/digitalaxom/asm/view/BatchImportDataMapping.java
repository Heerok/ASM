package digitalaxom.asm.view;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Heerok on 16-01-2017.
 */
public class BatchImportDataMapping {
    private static Map<String, String> fieldmap = new HashMap<>();

    static {

        fieldmap.put("vendor", "vendor");

        fieldmap.put("bids", "bids");

        fieldmap.put("url", "url");

    }

    public static Map<String, String> getFieldmap() {
        return fieldmap;
    }

}
