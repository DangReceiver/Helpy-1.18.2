package de.tdf.helpy.commands.utils;

import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.StringUtil;

import java.util.*;

public class Ench implements CommandExecutor, TabCompleter {

	private static final List<String> CMD;

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		if (sender instanceof Player) {
			final Player p = (Player) sender;
			if (p.hasPermission("Helpy.Ench")) {
				if (args.length == 2) {
					final ItemMeta m = p.getInventory().getItemInMainHand().getItemMeta();
					int level;
					try {
						level = Integer.parseInt(args[1]);
						if (level < 1 || level >= 16001) {
							if (level != 0) {
								Eng.numbHighLow(sender, "1", "integer", "16000", false);
								return true;
							}
							m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							p.sendMessage(Eng.CMD_ENCH_CAUSE_ZERO);
						}
					} catch (NumberFormatException e) {
						sender.sendMessage("This is §cnot §7a §avalid §6number§8.");
						return true;
					}
					final String s = args[0].toUpperCase();
					if (Enchantment.getByName(s) != null) {
						final Enchantment ench = Enchantment.getByName(s);
						final ItemStack item = p.getInventory().getItemInMainHand();
						m.addEnchant(ench, level, true);
						p.sendMessage(Eng.PRE + "Enchantment §§2" + args[0] + "§§8, §§bLevel §§9" + level + " §§7was §§aadded§§8.");
						item.setItemMeta(m);
						p.getInventory().setItemInMainHand(item);
						return true;
					}
					p.sendMessage(Eng.CMD_ENCHANT_INVALID);
				} else
					p.sendMessage(Eng.CMD_ARGS_LENGHT);
			} else
				Eng.permissionShow(sender, "Helpy.Ench");
		} else
			sender.sendMessage(Eng.CMD_NOT_PLAYER);
		return false;
	}

	public List<String> onTabComplete(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
		final List<String> com = new ArrayList<String>(), cmd2 = new ArrayList<String>();
		if (args.length == 1) {
			com.clear();
			for (final Enchantment e : Enchantment.values())
				if (e.getName().contains(args[0]))
					com.add(e.getName());
			StringUtil.copyPartialMatches(args[0], cmd2, (Collection) com);
		} else if (args.length == 2) {

			for (final String s : CMD)
				if (s.contains(args[1]))
					com.add(s);
			StringUtil.copyPartialMatches(args[0], Ench.CMD, (Collection) com);
		}
		Collections.sort(com);
		return com;
	}

	static {
		CMD = Arrays.asList("0", "1", "2", "3", "4", "5", "8", "10", "12", "15", "20", "25",
				"30", "40", "50", "75", "85", "100", "120", "150", "200", "250", "400", "500",
				"650", "1000", "1500", "2500", "4000", "8000", "12000", "16000");
	}
}
