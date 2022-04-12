package de.tdf.helpy.commands.control.personal;

import org.bukkit.Location;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.entity.LivingEntity;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class TpExact implements CommandExecutor
{
    public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
        if (sen instanceof LivingEntity) {
            final LivingEntity e = (LivingEntity)sen;
            if (e.hasPermission("Helpy.exactTp")) {
                Location loc = null;
                if (args.length != 0) {
                    sen.sendMessage(Eng.CMD_ARGS_LENGHT);
                    Eng.argsUsage(sen, "/TpExact [<Use Yaw & Pitch - boolean>]", true);
                    return true;
                }
                loc = new Location(e.getWorld(), (double)e.getLocation().getBlockX(), (double)e.getLocation().getBlockY(), (double)e.getLocation().getBlockZ());
                loc.setPitch((float)Math.round(loc.getPitch()));
                loc.setYaw((float)Math.round(loc.getYaw()));
                loc.add(0.5, 0.0, 0.5);
                e.teleport(loc);
                sen.sendMessage(Eng.PRE + "§7You were §asuccesfully §9teleported§8.");
            }
            else {
                sen.sendMessage(Eng.CMD_NOT_PLAYER);
            }
        }
        else {
            Eng.permissionShow(sen, "Helpy.exactTp");
        }
        return false;
    }
}
