package de.tdf.helpy.commands.function;

import de.tdf.helpy.methods.Other;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Day implements CommandExecutor
{
    public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
        if (sen.hasPermission("Helpy.Public.Day")) {
            World w = Bukkit.getWorld("world");
            if (sen instanceof Player) {
                final Player p = (Player)sen;
                w = p.getWorld();
            }
            else {
                sen.sendMessage("The world you are in could not be specified, Using ''world'' instead.");
                if (w == null) {
                    sen.sendMessage("The World named: ''world'' could not be found.");
                }
            }
            w.setTime(500L);
            w.setClearWeatherDuration(1000000);
            if (args.length == 0 || (args.length >= 1 && args[0].equalsIgnoreCase("true"))) {
                for (final Entity e : w.getEntities()) {
                    if (Other.isMonster(e)) {
                        e.remove();
                    }
                    else {
                        if (args.length < 2 || !args[1].equalsIgnoreCase("true") || (e.getType() != EntityType.ARMOR_STAND && e.getType() != EntityType.ITEM_FRAME && e.getType() != EntityType.DROPPED_ITEM)) {
                            continue;
                        }
                        e.remove();
                    }
                }
            }
            else if (args.length == 1 && !args[0].equalsIgnoreCase("help")) {
                sen.sendMessage(Eng.CMD_ARG_INVALID);
                Eng.tryHelp(sen, cmd, true);
            }
            else {
                Eng.argsUsage(sen, "/day [<Kill hostile, default: §atrue; Kill special entities, default: §cfalse>]", true);
            }
        }
        else {
            Eng.permissionShow(sen, "Helpy.Public.Day");
        }
        return false;
    }
}
