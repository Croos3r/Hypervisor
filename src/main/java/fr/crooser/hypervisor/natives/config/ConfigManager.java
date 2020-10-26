package fr.crooser.hypervisor.natives.config;

import fr.crooser.hypervisor.Hypervisor;
import fr.crooser.hypervisor.overlays.HyperManager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigManager extends HyperManager {

    private final ConfigManagerOptions options;

    public ConfigManager(Hypervisor<? extends JavaPlugin> hypervisor, ConfigManagerOptions options) {
        super(hypervisor);
        this.options = options;
    }

    @Override
    public void init() {

        options.defaultFiles.forEach(this::updateConfig);
    }

    public void makeConfig(String fileName, Map<String, ?> values) {

        final File              pluginDataFolder    = hypervisor.getPlugin().getDataFolder();
        final YamlConfiguration configuration       = new YamlConfiguration();

        values.forEach(configuration::set);

        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();

        final File file = new File(pluginDataFolder, fileName + ".yml");

        if (!file.exists()) {

            try {
                file.createNewFile();
                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateConfig(String fileName, Map<String, ?> values) {

        final File pluginDataFolder = hypervisor.getPlugin().getDataFolder();

        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();

        final File file = new File(pluginDataFolder, fileName + ".yml");


        if (file.exists()) {

            final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            values.entrySet().stream().
                    filter((entry) -> configuration.isSet(entry.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).
                    forEach(configuration::set);

            try {
                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else makeConfig(fileName, values);
    }

    public YamlConfiguration getConfig(String fileName) throws FileNotFoundException {

        final File pluginDataFolder = hypervisor.getPlugin().getDataFolder();

        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();

        final File file = new File(pluginDataFolder, fileName + ".yml");

        if (file.exists()) return YamlConfiguration.loadConfiguration(file);
        else throw new FileNotFoundException("Can't find " + fileName + ".yml in plugin's data folder.");
    }
}
