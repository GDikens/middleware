package servicedir;

import java.util.HashMap;
import java.util.HashMap;
import java.io.*;
import java.net.*;
import java.util.Collections; 
import java.util.*;  

public class directory{

    public Map<String, String[]> SERVICE_DIRECTORY;

    public directory() {
        SERVICE_DIRECTORY = new HashMap<String, String[]>();
        SERVICE_DIRECTORY.put("addservice", new String[]{"localhost", "3000"});
        SERVICE_DIRECTORY.put("subservice", new String[]{"localhost", "9999"});
    }
}