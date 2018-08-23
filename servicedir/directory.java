package servicedir;

import java.util.HashMap; 

public class directory{

    Map<String, String[]> SERVICE_DIRECTORY;

    public directory() {
        SERVICE_DIRECTORY = new HashMap<String, String[]>();
        SERVICE_DIRECTORY.put("addservice", new String[]{"localhost", "3000"});
        SERVICE_DIRECTORY.put("subservice", new String[]{"localhost", "9999"});
    }
}