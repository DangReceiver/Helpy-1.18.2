package de.tdf.helpy.commands.ffa;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Sound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Spawn implements CommandExecutor
{
    public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
        if (sen instanceof Player) {
            final Player p = (Player)sen;
            if (!sen.hasPermission("Helpy.Spawn")) {
                Eng.permissionShow(sen, "Helpy.Spawn");
                return false;
            }
            final FileConfiguration con = Helpy.getPlugin().getConfig();
            if (con.isSet("override.World.spawnWlWorld") && con.getBoolean("override.World.spawnWlWorld") && !con.getStringList("override.World.spawnWlWorlds").contains(p.getWorld().getName())) {
                p.sendMessage(Eng.CMD_SPAWN_UNWHITELISTED_WORLD);
                return true;
            }
            Location spawn = (Location)con.get("server.spawn");
            if (args.length == 0) {
                if (spawn == null) {
                    spawn = new Location(Bukkit.getWorld("world"), 0.5, 80.02, 0.5);
                }
                p.teleport(spawn);
                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.25f, 1.25f);
                p.sendTitle(Eng.PRE, "§7You were §asuccesfully §9teleported§8.", 5, 16, 8);
            }
            else if (args.length == 1) {
                if (p.hasPermission("Helpy.spawn.set")) {
                    if (args[0].equals("set")) {
                        con.set("server.spawn", (Object)p.getLocation());
                        Helpy.getPlugin().saveConfig();
                        Eng.newLocation(sen, p.getLocation());
                    }
                    else {
                        p.sendMessage(" §d#Args§8: §5");
                    }
                }
                else {
                    Eng.permissionShow(sen, "Helpy.spawn.set");
                }
            }
            else {
                Eng.argsUsage(sen, "/spawn", true);
            }
        }
        else {
            sen.sendMessage(Eng.CMD_NOT_PLAYER);
        }
        return false;
    }
}
