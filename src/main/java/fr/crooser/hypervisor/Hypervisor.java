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

    private ArrayList<HyperCommand> commands;
    private ArrayList<HyperListener<? extends Event>> listeners;
    private ArrayList<HyperManager> managers;

    public Hypervisor(P plugin) {

        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    public void init() {

        if (commands != null && listeners != null && managers != null) {

            managers.forEach(HyperManager::init);
            commands.forEach(HyperCommand::register);
            listeners.forEach(HyperListener::register);
        }
        else throw new IllegalStateException();
    }

    public P getPlugin() {

        return plugin;
    }

    public Logger getLogger() {

        return logger;
    }

    public void setCommands(ArrayList<HyperCommand> commands) {

        this.commands = commands;
    }
    public void setListeners(ArrayList<HyperListener<? extends Event>> listeners) {

        this.listeners = listeners;
    }
    public void setManagers(ArrayList<HyperManager> managers) {

        this.managers = managers;
    }
}
