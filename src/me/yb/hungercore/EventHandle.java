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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
                    meta.setDisplayName("§a« §2Crunchy Apple§a »");
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
                // Create inventory
                Inventory inv = Bukkit.createInventory(null, 54, "§b« §3Crafting§b »");
                List<String> lore = new ArrayList<>();
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
                item = new ItemStack(Material.STAINED_GLASS_PANE);
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
                // Consumables
                item = new ItemStack(Material.APPLE);
                lore.add("§7» §oConsumable items such as food and drinks.");
                meta = item.getItemMeta();
                meta.setDisplayName("§a« §2Consumables§a »");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(10, item);
                lore.clear();
                // Tools
                item = new ItemStack(Material.WOOD_PICKAXE);
                lore.add("§7» §oPickaxes, axes, shovels and more.");
                meta = item.getItemMeta();
                meta.setDisplayName("§6« §eTools§6 »");
                meta.setLore(lore);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                inv.setItem(11, item);
                lore.clear();
                // Weapons
                item = new ItemStack(Material.WOOD_SWORD);
                lore.add("§7» §oDeadly weapons to cut down animals and enemies.");
                meta = item.getItemMeta();
                meta.setDisplayName("§4« §cWeapons§4 »");
                meta.setLore(lore);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                inv.setItem(12, item);
                lore.clear();
                // Structures
                item = new ItemStack(Material.SMOOTH_BRICK);
                lore.add("§7» §oBlocks to build and decorate your base with.");
                meta = item.getItemMeta();
                meta.setDisplayName("§1« §9Structures§1 »");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(13, item);
                lore.clear();
                // Clothing
                item = new ItemStack(Material.LEATHER_CHESTPLATE);
                lore.add("§7» §oWearable items to protect you and change your stats.");
                meta = item.getItemMeta();
                meta.setDisplayName("§8« §7Clothing§8 »");
                meta.setLore(lore);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                inv.setItem(14, item);
                lore.clear();
                // Medical
                item = new ItemStack(Material.PAPER);
                lore.add("§7» §oConsumable items that heal you and give temporary boosts.");
                meta = item.getItemMeta();
                meta.setDisplayName("§c« §fMedical§c »");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(15, item);
                lore.clear();
                // Forging
                item = new ItemStack(Material.ANVIL);
                lore.add("§7» §oMaking materials out of materials.");
                meta = item.getItemMeta();
                meta.setDisplayName("§b« §3Forging§b »");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(16, item);
                lore.clear();
                // Display inventory to player
                e.getPlayer().openInventory(inv);
            }
        }
    }
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        GameMode gm = e.getWhoClicked().getGameMode();
        if (gm != GameMode.CREATIVE) {
            if (e.getClickedInventory() != null) {
                if (e.getClickedInventory().getTitle().equals("§b« §3Crafting§b »")) {
                    e.setCancelled(true);
                    if (e.getClickedInventory().getTitle().equals("§6« §eTools§6 »")) {
                        if (e.getClickedItem().getItemMeta().getDisplayName().equals("§6« §eMakeshift Pickaxe§6 »") {
                            ItemStack item = new ItemStack(Material.WOOD_PICKAXE);
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName("§6« §eMakeshift Pickaxe§6 »");
                            item.setItemMeta(meta);
                            craftItem(item, 1, 15000, e);
                            // TODO : Add lore to item
                        }
                    }
                }
            }
        }
    }
    public void craftItem(ItemStack item, int quantity, int time, InventoryClickEvent e) {
        e.getWhoClicked().playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        e.getWhoClicked.getLocation.dropItem(item, quantity);
    }
}
