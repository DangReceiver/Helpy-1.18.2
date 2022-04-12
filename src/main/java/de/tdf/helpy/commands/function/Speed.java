package de.tdf.helpy.commands.function;

import de.tdf.helpy.methods.items.IB;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Speed implements CommandExecutor {
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		if (sender instanceof Player) {
			final Player p = (Player) sender;
			if (p.hasPermission("helpy.speed.prepared")) {
				if (args.length == 0) {
					final Inventory in = Bukkit.createInventory(null, 27, "§3Choose §eSpeed");
					final ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
					IB.lore(filler, "§8§oClick to continue");
					IB.name(filler, " §0 ");
					final ItemStack compare = new ItemStack(Material.CLOCK);
					IB.invFiller(in, filler);
					IB.lore(compare, "Set §espeed to §60.1");
					in.setItem(9, compare);
					IB.lore(compare, "Set §espeed to §60.15");
					in.setItem(10, compare);
					IB.lore(compare, "Set §espeed to §60.2");
					in.setItem(11, compare);
					IB.lore(compare, "Set §espeed to §60.25");
					in.setItem(13, compare);
					IB.lore(compare, "Set §espeed to §60.4");
					in.setItem(15, compare);
					IB.lore(compare, "Set §espeed to §60.65");
					in.setItem(16, compare);
					IB.lore(compare, "Set §espeed to §61");
					in.setItem(17, compare);
					p.openInventory(in);
					return true;
				}
				if (args.length == 1) {
					if (!p.hasPermission("helpy.speed.self")) {
						Eng.permissionShow(sender, "helpy.speed.self");
						return true;
					}
					try {
						final float speedNow = Float.parseFloat(args[0]);
						if (speedNow < 0.0f) {
							Eng.numbHighLow(sender, "0", "Float", "0", true);
							return false;
						}
						if (speedNow > 1.0f) {
							Eng.numbHighLow(sender, "0", "Float", "1", false);
							return false;
						}
						final String fs = p.getFlySpeed() + "",
								ws = p.getWalkSpeed() + "";
						if (p.isFlying()) {
							p.setFlySpeed(speedNow);
							Eng.atChange(sender, "action", "§bfly speed", true, fs, p.getFlySpeed() + "", false, false);
						} else {
							p.setWalkSpeed(speedNow);
							Eng.atChange(sender, "action", "§bWalk speed", true, ws, p.getWalkSpeed() + "", false, false);
						}
						return false;
					} catch (NumberFormatException e) {
						Eng.entryType(sender, "0", "Float");
						return true;
					}
				}
				p.sendMessage(Eng.CMD_ARGS_LENGHT);
			} else
				Eng.permissionShow(sender, "helpy.speed.prepared");
		} else
			sender.sendMessage(Eng.CMD_NOT_PLAYER);
		return false;
	}
}
