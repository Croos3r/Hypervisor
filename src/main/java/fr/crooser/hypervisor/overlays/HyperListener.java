package fr.crooser.hypervisor.overlays;

import fr.crooser.hypervisor.Hypervisor;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class HyperListener<E extends Event> implements Listener {

    protected final Hypervisor<? extends JavaPlugin> hypervisor;

    public HyperListener(Hypervisor<? extends JavaPlugin> hypervisor) {

        this.hypervisor = hypervisor;
    }

    public void register() {

        final JavaPlugin plugin = hypervisor.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    protected abstract void onEvent(E event);
}
