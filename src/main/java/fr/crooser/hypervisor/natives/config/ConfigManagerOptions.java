package fr.crooser.hypervisor.natives.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConfigManagerOptions {

    Map<String, Map<String, ?>> defaultFiles = new HashMap<>();

    public ConfigManagerOptions addDefault(String fileName, Map<String, ?> values) {

        if (values == null) values = Collections.emptyMap();
        defaultFiles.put(fileName, values);
        return this;
    }


}
