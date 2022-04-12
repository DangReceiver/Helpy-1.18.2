package de.tdf.helpy.listener.Entities.Player;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.Other;
import de.tdf.helpy.methods.items.Item;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClickGrowed implements Listener {
	public static List<String> lis_GrowedDelay;

	public static final String ERROR = "You §cdon§8'§ct §7have this §aseed§8!";
	
	static {
		ClickGrowed.lis_GrowedDelay = new ArrayList<String>();
	}

	@EventHandler
	public void interactGrowed(final PlayerInteractEvent e) {
		if (e.isCancelled()) {
			return;
		}
		final FileConfiguration con = Helpy.getPlugin().getConfig();
		final Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getGameMode() != GameMode.SPECTATOR) {
			if (e.getClickedBlock().getBlockData().toString().contains("[age=7]")) {
				if (con.getBoolean("Settings.Permission.GrowedClick") && !p.hasPermission("Helpy.growedClick")) {
					if (!ClickGrowed.lis_GrowedDelay.contains(p.getName())) {
						ClickGrowed.lis_GrowedDelay.add(p.getName());
						p.sendMessage(Eng.LIS_GROWED_PERM);
						Bukkit.getScheduler().runTaskLater(Helpy.getPlugin(), () -> ClickGrowed.lis_GrowedDelay.remove(p.getName()), 40L);
					}
					return;
				}
				if (e.getClickedBlock().getType() == Material.WHEAT) {
					e.setCancelled(true);
					if (Item.getItemAmount(Material.WHEAT_SEEDS, p.getInventory()) < 1) {
						Other.actionBar(p, "none", ERROR, 1, 0.5f);
						return;
					}
					for (final ItemStack i : e.getClickedBlock().getDrops())
						p.getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), i);
					e.getClickedBlock().setType(Material.WHEAT);
				} else if (e.getClickedBlock().getType() == Material.CARROTS) {
					e.setCancelled(true);
					if (Item.getItemAmount(Material.CARROT, p.getInventory()) < 1) {
						Other.actionBar(p, "none", ERROR, 1, 0.5f);
						return;
					}
					for (final ItemStack i : e.getClickedBlock().getDrops())
						p.getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), i);
					e.getClickedBlock().setType(Material.CARROTS);
				} else {
					if (e.getClickedBlock().getType() != Material.POTATOES) return;
					e.setCancelled(true);
					if (Item.getItemAmount(Material.POTATO, p.getInventory()) < 1) {
						Other.actionBar(p, "none", ERROR, 1, 0.5f);
						return;
					}
					for (final ItemStack i : e.getClickedBlock().getDrops())
						p.getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), i);
					e.getClickedBlock().setType(Material.POTATOES);
				}
				p.playSound(p.getLocation(), Sound.ITEM_CROP_PLANT, 1, 0.75f);
			} else {
				if (!e.getClickedBlock().getBlockData().toString().contains("[age=3]")) return;
				if (con.getBoolean("Settings.Permission.GrowedClick") && !p.hasPermission("Helpy.growedClick")) {
					if (!ClickGrowed.lis_GrowedDelay.contains(p.getName())) {
						ClickGrowed.lis_GrowedDelay.add(p.getName());
						p.sendMessage(Eng.LIS_GROWED_PERM);
						Bukkit.getScheduler().runTaskLater(Helpy.getPlugin(), () -> ClickGrowed.lis_GrowedDelay.remove(p.getName()), 40L);
					}
					return;
				}
				if (e.getClickedBlock().getType() == Material.NETHER_WART) {
					e.setCancelled(true);
					if (Item.getItemAmount(Material.NETHER_WART, p.getInventory()) < 1) {
						Other.actionBar(p, "none", ERROR, 1, 0.5f);
						return;
					}
					for (final ItemStack i : e.getClickedBlock().getDrops())
						p.getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), i);
					e.getClickedBlock().setType(Material.NETHER_WART);
				} else {
					if (e.getClickedBlock().getType() != Material.BEETROOTS) return;
					e.setCancelled(true);
					if (Item.getItemAmount(Material.BEETROOT_SEEDS, p.getInventory()) < 1) {
						Other.actionBar(p, "none", ERROR, 1, 0.5f);
						return;
					}
					for (final ItemStack i : e.getClickedBlock().getDrops())
						p.getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), i);
					e.getClickedBlock().setType(Material.BEETROOTS);
				}
				p.playSound(p.getLocation(), Sound.ITEM_CROP_PLANT, 1, 0.65f);
			}
			if (Helpy.grownExp && new Random().nextInt(35) == 1)
				p.getWorld().spawnEntity(p.getLocation(), EntityType.THROWN_EXP_BOTTLE);
		}
	}
}
