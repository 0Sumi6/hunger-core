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

