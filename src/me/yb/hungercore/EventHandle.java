package me.yb.hungercore;

import org.bukkit.plugin.java.JavaPlugin;

public class EventHandle extends JavaPlugin {
    @Override
    public void onEnable() {
        new PlayerListener(this);
        System.out.println("» HungerCore successfully started!");
    }
    @Override
    public void onDisable() {

    }
}
