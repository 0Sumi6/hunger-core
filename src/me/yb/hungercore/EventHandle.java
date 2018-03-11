package me.yb.hungercore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class EventHandle extends JavaPlugin implements Listener {
    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                System.out.println("[HC] Creating config.yml");
                saveDefaultConfig();
            } else {
                System.out.println("[HC] Loading config.yml");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    @Override
    public void onEnable() {
        System.out.println("HungerCore successfully started!");
        getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getWorlds().get(0).setGameRuleValue("reducedDebugInfo", "true");
        Bukkit.getServer().getWorlds().get(0).setGameRuleValue("doLimitedCrafting", "true");
        Bukkit.getServer().getWorlds().get(0).setGameRuleValue("showDeathMessages", "false");
        Bukkit.getServer().getWorlds().get(0).setGameRuleValue("announceAdvancements", "false");
        Bukkit.getServer().getWorlds().get(0).setGameRuleValue("doDaylightCycle", "false");
        Bukkit.getServer().getWorlds().get(0).setGameRuleValue("doWeatherCycle", "false");
        Bukkit.getServer().getWorlds().get(0).setGameRuleValue("doMobLoot", "false");
        Bukkit.getServer().getWorlds().get(0).setGameRuleValue("doTileDrops", "false");
        Bukkit.getServer().getWorlds().get(0).setGameRuleValue("doMobSpawning", "false");
        Bukkit.getServer().getWorlds().get(0).setGameRuleValue("doMobGriefing", "false");
        Bukkit.getServer().getWorlds().get(0).setGameRuleValue("naturalRegeneration", "false");
        // Initialize variables
        // TODO : Set "baseTemp" to val from data.yml
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
    public void onJoin(PlayerJoinEvent e) {

    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        GameMode gm = e.getPlayer().getGameMode();
        if (gm != (GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        GameMode gm = e.getPlayer().getGameMode();
        if (gm != (GameMode.CREATIVE)) {
            e.setCancelled(true);
            ItemStack item = null;
            ItemMeta meta;
            // Get tool level
            byte pickLvl = 0;
            if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WOOD_PICKAXE) {
                pickLvl = 1;
            }
            if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.STONE_PICKAXE) {
                pickLvl = 2;
            }
            Location l = e.getBlock().getLocation().add(0.5, 0.5, 0.5);
            Player p = e.getPlayer();
            Material mat = e.getBlock().getType();
            World world = p.getWorld();
            int loot = ThreadLocalRandom.current().nextInt(1, 100 + 1);
            if (mat == Material.LEAVES) {
                if (loot <= 5) {
                    item = new ItemStack(Material.APPLE);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§a« §2Crunchy Apple§a »");;
                    item.setItemMeta(meta);
                    p.playSound(l, Sound.ENTITY_CHICKEN_EGG, 1F, 1F);
                }
                if (loot > 5 && loot <= 25) {
                    world.dropItemNaturally(l, new ItemStack(Material.STICK));
                    p.playSound(l, Sound.BLOCK_METAL_BREAK, 1F, 1F);
                }
            }
            if (mat == Material.CARPET) {
                if (loot <= 15) {
                    world.dropItemNaturally(l, new ItemStack(Material.BROWN_MUSHROOM));
                    p.playSound(l, Sound.BLOCK_GRASS_BREAK, 1F, 1F);
                }
                if (loot > 15 && loot <= 25) {
                    world.dropItemNaturally(l, new ItemStack(Material.RED_MUSHROOM));
                    p.playSound(l, Sound.BLOCK_GRASS_BREAK, 1F, 1F);
                }
            }
            if (mat == Material.MAGMA) {
                if (loot <= 33) {
                    world.dropItemNaturally(l, new ItemStack(Material.MAGMA_CREAM));
                    p.playSound(l, Sound.ITEM_BUCKET_EMPTY, 1F, 1F);
                }
                if (loot > 33 && loot <= 75) {
                    world.dropItemNaturally(l, new ItemStack(Material.DOUBLE_PLANT));
                    p.playSound(l, Sound.ITEM_BUCKET_EMPTY, 1F, 1F);
                }
                if (loot > 75) {
                    world.dropItemNaturally(l, new ItemStack(Material.DOUBLE_PLANT, 2));
                    p.playSound(l, Sound.ITEM_BUCKET_EMPTY, 1F, 1F);
                }
            }
            if (mat == Material.GRASS || mat == Material.DIRT) {
                if (loot <= 75) {
                    world.dropItemNaturally(l, new ItemStack(Material.COCOA, 2));
                }
                if (loot > 75) {
                    world.dropItemNaturally(l, new ItemStack(Material.COCOA, 3));
                }
            }
            if (mat == Material.STONE) {
                world.dropItemNaturally(l, new ItemStack(Material.STONE_BUTTON, 2));
            }
            if (mat == Material.WEB) {
                world.dropItemNaturally(l, new ItemStack(Material.QUARTZ));
            }
            if (pickLvl >= 1) {
                if (mat == Material.LOG) {
                    if (loot <= 50) {
                        world.dropItemNaturally(l, new ItemStack(Material.LOG));
                        p.playSound(l, Sound.ITEM_SHIELD_BREAK, 1F, 1F);
                    }
                    if (loot > 50 && loot <= 80) {
                        world.dropItemNaturally(l, new ItemStack(Material.WOOD));
                        p.playSound(l, Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1F, 1F);
                    }
                    if (loot > 80) {
                        world.dropItemNaturally(l, new ItemStack(Material.WOOD, 2));
                        p.playSound(l, Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1F, 1F);
                    }
                }
            }
            if (item != null) {
                world.dropItemNaturally(l, item);
            }
        }
    }
    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e) {
        GameMode gm = e.getPlayer().getGameMode();
        if (gm != (GameMode.CREATIVE)) {
            e.setCancelled(true);
            if (e.getBlockClicked().getType() == Material.WATER) {
                e.getBlockClicked().setType(Material.WATER);
            } else {
                e.getBlockClicked().setType(Material.LAVA);
            }
        }
    }
    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent e) {
        GameMode gm = e.getPlayer().getGameMode();
        if (gm != (GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onSwapHand(PlayerSwapHandItemsEvent e) {
        GameMode gm = e.getPlayer().getGameMode();
        if (gm != GameMode.CREATIVE) {
            e.setCancelled(true);
            if (e.getPlayer().isSneaking()) {
                Inventory inv = Bukkit.createInventory(null, 54, "§b« §3Crafting§b »");
                ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§a");
                item.setItemMeta(meta);
                for (int i = 0; i < 54; i += 0) {
                    inv.setItem(i, item);
                    if (i % 9 == 0) {
                        i += 8;
                    } else {
                        i++;
                    }
                }
                item = new ItemStack(Material.STAINED_GLASS_PANE, 1);
                meta = item.getItemMeta();
                meta.setDisplayName("§a");
                item.setItemMeta(meta);
                for (int i = 1; i < 27; i += 0) {
                    if (i > 9 && i < 18) {
                        i += 9;
                    } else {
                        inv.setItem(i, item);
                        if ((i + 2) % 9 == 0) {
                            i += 3;
                        } else {
                            i++;
                        }
                    }
                }
                for (int i = 28; i < 54; i += 0) {
                    inv.setItem(i, item);
                    if ((i + 2) % 9 == 0) {
                        i += 3;
                    } else {
                        i++;
                    }
                }
                e.getPlayer().openInventory(inv);
            }
        }
    }
}
