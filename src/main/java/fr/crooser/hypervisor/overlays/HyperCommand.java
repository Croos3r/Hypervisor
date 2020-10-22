package fr.crooser.hypervisor.overlays;

import org.jetbrains.annotations.NotNull;
import fr.crooser.hypervisor.Hypervisor;

import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class HyperCommand implements CommandExecutor, TabCompleter {

    protected final String label;
    protected final Hypervisor<? extends JavaPlugin> hypervisor;

    public HyperCommand(String label, Hypervisor<? extends JavaPlugin> hypervisor) {

        this.label = label;
        this.hypervisor = hypervisor;
    }

    public void register() {

        PluginCommand command = hypervisor.getPlugin().getCommand(label);

        if (command != null) {

            command.setExecutor(this);
            command.setTabCompleter(this);
        }
        else hypervisor.getLogger().warning("Unable to register command " + label + " please check plugin.yml");
    }

    @Override
    public abstract boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String message, @NotNull String[] arguments);

    @Override
    public abstract List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String message, @NotNull String[] arguments);

    public String getLabel() {

        return label;
    }
}
