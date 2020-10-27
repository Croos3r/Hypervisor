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

        file.getConfigObjects().forEach(object -> configuration.set(object.getKey(), object.getValue()));

        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();

        if (!file.exists()) {

            try {
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

            file.getConfigObjects().stream().filter(object -> !object.isOptional()).forEach(object -> configuration.set(object.getKey(), object.getValue()));

            try {
                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else makeConfig(file);
    }

    public YamlConfiguration getConfig(String fileName) throws FileNotFoundException {

        final File pluginDataFolder = hypervisor.getPlugin().getDataFolder();

        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();

        final File file = new File(pluginDataFolder, fileName + ".yml");

        if (file.exists()) return YamlConfiguration.loadConfiguration(file);
        else throw new FileNotFoundException("Can't find " + fileName + ".yml in plugin's data folder.");
    }
}
