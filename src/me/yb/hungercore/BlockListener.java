// TODO: Add item data

package me.yb.hungercore;

import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class BlockListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.setCancelled(true);
        Location l = e.getBlock().getLocation();
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
                e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.MUSHROOM));
            }
            if (loot > 15 && loot <= 25) {
                e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.MUSHROOM));
            }
        }
        if (e.getBlock().getType() == Material.WOOD) {
            if (loot <= 50) {
                e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.WOOD));
            }
            if (loot > 50 && loot <= 80) {
                e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.PLANKS));
            }
            if (loot > 80) {
                e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.PLANKS));
            }
        }
        if (e.getBlock().getType() == Material.MAGMABLOCK) {
            if (loot <= 33) {
                e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.MAGMACREAM));
            }
            if (loot > 33 && loot <== 75) {
                e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.SUNFLWOER));
            }
            if (loot > 75) {
                e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.SUNFLWOER));
            }
        }
        if (e.getBlock().getType() == Material.WEB) {
            e.getPlayer().getWorld().dropItemNaturally(l, new ItemStack(Material.NETHERQUARTZ));
        }
    }
    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent e) {
        e.setCancelled(true);
    }
}

