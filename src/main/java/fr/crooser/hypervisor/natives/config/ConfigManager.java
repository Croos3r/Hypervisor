package fr.crooser.hypervisor.natives.config;

import fr.crooser.hypervisor.Hypervisor;
import fr.crooser.hypervisor.overlays.HyperManager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConfigManager extends HyperManager {

    private final List<ConfigFile> configFiles;

    public ConfigManager(Hypervisor<? extends JavaPlugin> hypervisor, ConfigFile... configFiles) {
        super(hypervisor);

        this.configFiles = Arrays.asList(configFiles);
    }

    @Override
    public void init() {

        configFiles.forEach(this::updateConfig);
    }

    public void makeConfig(ConfigFile file) {

        final File              pluginDataFolder    = hypervisor.getPlugin().getDataFolder();
        final YamlConfiguration configuration       = new YamlConfiguration();

        file.getConfigObjects().forEach(object -> configuration.set(object.getPath(), object.getValue()));

        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();

        if (!file.exists()) {

            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateConfig(ConfigFile file) {

        final File pluginDataFolder = hypervisor.getPlugin().getDataFolder();

        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();

        if (file.exists()) {

            final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            file.getConfigObjects().forEach(object -> {

                if (!object.isOptional() && !configuration.isSet(object.getPath())) {

                    if (!object.isSection()) configuration.set(object.getPath(), object.getValue());
                    else configuration.createSection(object.getPath());
                }
            });

            try {
                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else makeConfig(file);
    }

    public YamlConfiguration getConfig(File file) throws FileNotFoundException {

        if (file.exists()) return YamlConfiguration.loadConfiguration(file);
        else throw new FileNotFoundException("Can't find " + file.getName());
    }
}
