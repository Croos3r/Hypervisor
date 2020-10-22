package fr.crooser.hypervisor.overlays;

import fr.crooser.hypervisor.Hypervisor;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class HyperManager {

    protected final Hypervisor<? extends JavaPlugin> hypervisor;

    public HyperManager(Hypervisor<? extends JavaPlugin> hypervisor) {

        this.hypervisor = hypervisor;
    }

    public abstract void init();
}
