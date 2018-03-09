package me.yb.hungercore;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class EventHandle extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        String prefix = "[HC] ";
        System.out.println("HungerCore successfully started!");
        getServer().getPluginManager().registerEvents(this, this);
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            System.out.println(prefix + "Creating new config.yml file");
            saveDefaultConfig();
        } else {
            System.out.println(prefix + "Loading config.yml file");
        }
        // Initialize variables
        // TODO : Set "baseTemp" to val from config.yml
        // TODO : Find how to store user variables
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    // Main loop
                }

            }
        }, 0, 1);
    }
    @Override
    public void onDisable() {

    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        GameMode gm = e.getPlayer().getGameMode();
        if (!gm.equals(GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        GameMode gm = e.getPlayer().getGameMode();
        if (!gm.equals(GameMode.CREATIVE)) {
            e.setCancelled(true);
            Location l = e.getBlock().getLocation().add(0.5, 0.5, 0.5);
            // TODO : Get material type
            // TODO : Get world
            // TODO : Play sound on successful drop
            int loot = ThreadLocalRandom.current().nextInt(1, 100 + 1);
            if (e.getBlock().getType() == Material.LEAVES) {
                if (loot <= 5) {
                    e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.APPLE));
                }
                if (loot > 5 && loot <= 25) {
                    e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.STICK));
                }
            }
            if (e.getBlock().getType() == Material.CARPET) {
                if (loot <= 15) {
                    e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.BROWN_MUSHROOM));
                }
                if (loot > 15 && loot <= 25) {
                    e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.RED_MUSHROOM));
                }
            }
            if (e.getBlock().getType() == Material.MAGMA) {
                if (loot <= 33) {
                    e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.MAGMA_CREAM));
                }
                if (loot > 33 && loot <= 75) {
                    e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.DOUBLE_PLANT));
                }
                if (loot > 75) {
                    e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.DOUBLE_PLANT, 2));
                }
            }
            if (e.getBlock().getType() == Material.GRASS || e.getBlock().getType() == Material.DIRT) {
                if (loot <= 75) {
                    e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.DYE, 2 /* TODO : Get damage value */));
                }
                if (loot > 75) {
                    e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.DYE, 3));
                }
            }
            if (e.getBlock().getType() == Material.STONE)) {
                e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.BUTTON, 2));
            }
            if (e.getBlock().getType() == Material.WEB) {
                e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.QUARTZ));
            }
            if (/* TODO : Get item in player's hand */) {
                if (e.getBlock().getType() == Material.LOG) {
                    if (loot <= 50) {
                        e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.LOG));
                    }
                    if (loot > 50 && loot <= 80) {
                        e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.WOOD));
                    }
                    if (loot > 80) {
                        e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.WOOD, 2));
                    }
                }
            }
        }
    }
    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e) {
        GameMode gm = e.getPlayer().getGameMode();
        if (!gm.equals(GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent e) {
        GameMode gm = e.getPlayer().getGameMode();
        if (!gm.equals(GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }
    // TODO : Hand switch event, open crafting if sneaking
}
