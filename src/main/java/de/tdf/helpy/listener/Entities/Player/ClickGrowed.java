package de.tdf.helpy.listener.Entities.Player;

import java.util.ArrayList;
import org.bukkit.event.EventHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import java.util.Random;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import de.tdf.helpy.methods.Other;
import org.bukkit.inventory.Inventory;
import de.tdf.helpy.methods.items.Item;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.GameMode;
import org.bukkit.event.block.Action;
import org.bukkit.OfflinePlayer;
import de.tdf.helpy.methods.pConfig;
import de.tdf.helpy.helpy.Helpy;
import org.bukkit.event.player.PlayerInteractEvent;
import java.util.List;
import org.bukkit.event.Listener;

public class ClickGrowed implements Listener
{
    public static List<String> lis_GrowedDelay;
    
    @EventHandler
    public void interactGrowed(final PlayerInteractEvent e) {
        if (e.isCancelled()) {
            return;
        }
        final FileConfiguration con = Helpy.getPlugin().getConfig();
        final Player p = e.getPlayer();
        if (((Helpy.survivalMode && p.getWorld() == Helpy.defWorld) || !Helpy.survivalMode || p.getWorld().getName().contains("w" + pConfig.loadConfig((OfflinePlayer)p, "FarmCountry").getWorldID())) && e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getGameMode() != GameMode.SPECTATOR) {
            if (e.getClickedBlock().getBlockData().toString().contains("[age=7]")) {
                if (con.getBoolean("Settings.Permission.GrowedClick") && !p.hasPermission("nte.premium")) {
                    if (!ClickGrowed.lis_GrowedDelay.contains(p.getName())) {
                        ClickGrowed.lis_GrowedDelay.add(p.getName());
                        p.sendMessage(Eng.LIS_GROWED_PERM);
                        Bukkit.getScheduler().runTaskLater((Plugin)Helpy.getPlugin(), (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                ClickGrowed.lis_GrowedDelay.remove(p.getName());
                            }
                        }, 40L);
                    }
                    return;
                }
                if (e.getClickedBlock().getType() == Material.WHEAT) {
                    e.setCancelled(true);
                    if (Item.getItemAmount(Material.WHEAT_SEEDS, (Inventory)p.getInventory()) < 1) {
                        Other.actionBar(p, "none", "You §cdon§8'§ct §7have this §aseed§8!", 1.0f, 0.5f);
                        return;
                    }
                    for (final ItemStack i : e.getClickedBlock().getDrops()) {
                        p.getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), i);
                    }
                    e.getClickedBlock().setType(Material.WHEAT);
                }
                else if (e.getClickedBlock().getType() == Material.CARROTS) {
                    e.setCancelled(true);
                    if (Item.getItemAmount(Material.CARROT, (Inventory)p.getInventory()) < 1) {
                        Other.actionBar(p, "none", "You §cdon§8'§ct §7have this §aseed§8!", 1.0f, 0.5f);
                        return;
                    }
                    for (final ItemStack i : e.getClickedBlock().getDrops()) {
                        p.getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), i);
                    }
                    e.getClickedBlock().setType(Material.CARROTS);
                }
                else {
                    if (e.getClickedBlock().getType() != Material.POTATOES) {
                        return;
                    }
                    e.setCancelled(true);
                    if (Item.getItemAmount(Material.POTATO, (Inventory)p.getInventory()) < 1) {
                        Other.actionBar(p, "none", "You §cdon§8'§ct §7have this §aseed§8!", 1.0f, 0.5f);
                        return;
                    }
                    for (final ItemStack i : e.getClickedBlock().getDrops()) {
                        p.getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), i);
                    }
                    e.getClickedBlock().setType(Material.POTATOES);
                }
                p.playSound(p.getLocation(), Sound.ITEM_CROP_PLANT, 1.0f, 0.75f);
            }
            else {
                if (!e.getClickedBlock().getBlockData().toString().contains("[age=3]")) {
                    return;
                }
                if (con.getBoolean("Settings.Permission.GrowedClick") && !p.hasPermission("nte.premium")) {
                    if (!ClickGrowed.lis_GrowedDelay.contains(p.getName())) {
                        ClickGrowed.lis_GrowedDelay.add(p.getName());
                        p.sendMessage(Eng.LIS_GROWED_PERM);
                        Bukkit.getScheduler().runTaskLater((Plugin)Helpy.getPlugin(), (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                ClickGrowed.lis_GrowedDelay.remove(p.getName());
                            }
                        }, 40L);
                    }
                    return;
                }
                if (e.getClickedBlock().getType() == Material.NETHER_WART) {
                    e.setCancelled(true);
                    if (Item.getItemAmount(Material.NETHER_WART, (Inventory)p.getInventory()) < 1) {
                        Other.actionBar(p, "none", "You §cdon§8'§ct §7have this §aseed§8!", 1.0f, 0.5f);
                        return;
                    }
                    for (final ItemStack i : e.getClickedBlock().getDrops()) {
                        p.getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), i);
                    }
                    e.getClickedBlock().setType(Material.NETHER_WART);
                }
                else {
                    if (e.getClickedBlock().getType() != Material.BEETROOTS) {
                        return;
                    }
                    e.setCancelled(true);
                    if (Item.getItemAmount(Material.BEETROOT_SEEDS, (Inventory)p.getInventory()) < 1) {
                        Other.actionBar(p, "none", "You §cdon§8'§ct §7have this §aseed§8!", 1.0f, 0.5f);
                        return;
                    }
                    for (final ItemStack i : e.getClickedBlock().getDrops()) {
                        p.getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), i);
                    }
                    e.getClickedBlock().setType(Material.BEETROOTS);
                }
                p.playSound(p.getLocation(), Sound.ITEM_CROP_PLANT, 1.0f, 0.65f);
            }
            if (Helpy.grownExp && new Random().nextInt(32) == 1) {
                p.getWorld().spawnEntity(p.getLocation(), EntityType.THROWN_EXP_BOTTLE);
            }
        }
    }
    
    static {
        ClickGrowed.lis_GrowedDelay = new ArrayList<String>();
    }
}
