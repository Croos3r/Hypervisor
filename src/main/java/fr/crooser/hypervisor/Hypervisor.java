package fr.crooser.hypervisor;

import fr.crooser.hypervisor.overlays.HyperCommand;
import fr.crooser.hypervisor.overlays.HyperListener;
import fr.crooser.hypervisor.overlays.HyperManager;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class Hypervisor<P extends JavaPlugin> {

    private final P plugin;
    private final Logger logger;

    private HyperCommand[] commands;
    private HyperListener<? extends Event>[] listeners;
    private HyperManager[] managers;

    public Hypervisor(P plugin) {

        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    public void init() {

        Arrays.stream(commands).forEach(HyperCommand::register);
        Arrays.stream(listeners).forEach(HyperListener::register);
        Arrays.stream(managers).forEach(HyperManager::init);
    }

    public P getPlugin() {

        return plugin;
    }

    public Logger getLogger() {

        return logger;
    }

    public void setCommands(HyperCommand... commands) {

        this.commands = commands;
    }

    public final void setListeners(HyperListener<?>... listeners) {

        this.listeners = listeners;
    }
    public void setManagers(HyperManager... managers) {

        this.managers = managers;
    }
}
