package fr.crooser.hypervisor.natives.config;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ConfigFile extends File {

    private final List<ConfigObject> configObjects;

    public ConfigFile(@NotNull String pathname, ConfigObject... configObjects) {
        super(pathname + ".yml");

        this.configObjects = Arrays.asList(configObjects);
    }

    public ConfigFile(String parent, @NotNull String child, ConfigObject... configObjects) {
        super(parent, child + ".yml");

        this.configObjects = Arrays.asList(configObjects);
    }

    public ConfigFile(File parent, @NotNull String child, ConfigObject... configObjects) {
        super(parent, child + ".yml");

        this.configObjects = Arrays.asList(configObjects);
    }

    public List<ConfigObject> getConfigObjects() {

        return configObjects;
    }
}
