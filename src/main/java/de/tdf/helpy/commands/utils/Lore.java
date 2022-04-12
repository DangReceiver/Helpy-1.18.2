package de.tdf.helpy.commands.utils;

import de.tdf.helpy.methods.items.IB;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Lore implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
		if (sen instanceof Player) {
			if (sen.hasPermission("Helpy.manageLore")) {
				if (args.length >= 1) {
					final Player p = (Player) sen;
					final ItemStack it = p.getInventory().getItemInMainHand();
					Label_0206:
					{
						if (args[0].equalsIgnoreCase("set")) {
							IB.lore(it, args);
						} else if (args[0].equalsIgnoreCase("add")) {
							IB.addLore(it, Arrays.asList(args), IB.getLore(it));
						} else {
							if (args[0].equalsIgnoreCase("remove")) {
								try {
									final int l = Integer.parseInt(args[1]);
									final List<String> s = IB.getLore(it);
									IB.lore(it, s.remove(l));
									break Label_0206;
								} catch (NumberFormatException yrhdt) {
									p.sendMessage("This is §cnot §7a §avalid §6number§8.");
									return true;
								}
							}
							if (args[0].equalsIgnoreCase("clear"))
								IB.lore(it, "");
							else
								IB.lore(it, args);
						}
					}
					p.sendMessage(Eng.CMD_LORE_SUCCESSFULLY_UPDATED);
				} else
					sen.sendMessage(Eng.CMD_ARGS_LENGHT);
			} else
				Eng.permissionShow(sen, "Helpy.manageLore");
		} else
			sen.sendMessage(Eng.CMD_NOT_PLAYER);
		return false;
	}
}
