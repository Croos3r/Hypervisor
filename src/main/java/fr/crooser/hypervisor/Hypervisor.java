package fr.crooser.hypervisor;

import fr.crooser.hypervisor.overlays.HyperCommand;
import fr.crooser.hypervisor.overlays.HyperListener;
import fr.crooser.hypervisor.overlays.HyperManager;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Hypervisor<P extends JavaPlugin> {

    private final P plugin;
    private final Logger logger;

    private final ArrayList<HyperCommand> commands;
    private final ArrayList<HyperListener<? extends Event>> listeners;
    private final ArrayList<HyperManager> managers;

    public Hypervisor(P plugin, ArrayList<HyperCommand> commands, ArrayList<HyperListener<? extends Event>> listeners, ArrayList<HyperManager> managers) {

        this.plugin = plugin;
        this.logger = plugin.getLogger();

        this.commands = commands;
        this.listeners = listeners;
        this.managers = managers;
    }

    public void init() {

        managers.forEach(HyperManager::init);
        commands.forEach(HyperCommand::register);
        listeners.forEach(HyperListener::register);
    }

    public P getPlugin() {

        return plugin;
    }

    public Logger getLogger() {

        return logger;
    }
}
