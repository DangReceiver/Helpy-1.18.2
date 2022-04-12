package de.tdf.helpy.commands.function;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maintenance implements CommandExecutor
{
    public boolean onCommand(final CommandSender sen, final Command cmd, final String label, final String[] args) {
        if (!sen.hasPermission("Helpy.Maintenance.use")) {
            Eng.permissionShow(sen, "Helpy.Maintenance.use");
            return true;
        }
        final FileConfiguration con = Helpy.getPlugin().getConfig();
        if (!con.isSet("Maintenance")) {
            resetMaintenanceSettings(true);
            System.out.println(" §0 \n §0 \n §0 " + Eng.PRE + "Maintenance basic settings were automatically set, make sure to double check them!" + " §0 \n §0 \n §0 ");
            Helpy.getPlugin().saveConfig();
        }
        if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("Toggle"))) {
            if (con.getBoolean("Maintenance.Toggled")) {
                con.set("Maintenance.Toggled", (Object)false);
            }
            else {
                con.set("Maintenance.Toggled", (Object)true);
            }
            Helpy.getPlugin().saveConfig();
            Bukkit.broadcastMessage(Eng.CMD_MAINTENANCE_NOW + con.getBoolean("Maintenance.Toggled"));
            final String[] s = Eng.CMD_MAINTENANCE_TITLE.split("°°");
            Sound o = Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE;
            if (con.getBoolean("Maintenance.Toggled")) {
                o = Sound.ENTITY_WITHER_SPAWN;
            }
            for (final Player ap : Bukkit.getOnlinePlayers()) {
                ap.sendTitle(s[0], s[1], 6, 40, 50);
                ap.playSound(ap.getLocation(), o, 1.0f, 1.1f);
            }
        }
        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("ConnectLists")) {
                if (con.getBoolean("Maintenance.MaintenanceListEqualsWhitelist")) {
                    con.set("Maintenance.MaintenanceListEqualsWhitelist", (Object)false);
                }
                else {
                    con.set("Maintenance.MaintenanceListEqualsWhitelist", (Object)true);
                }
                Eng.booleanValueUpdated(sen, args[0], con.getBoolean("Maintenance.MaintenanceListEqualsWhitelist"));
            }
            else if (args[0].equalsIgnoreCase("Misconnect")) {
                if (con.getBoolean("Maintenance.AnnounceMisconnect")) {
                    con.set("Maintenance.AnnounceMisconnect", (Object)false);
                }
                else {
                    con.set("Maintenance.AnnounceMisconnect", (Object)true);
                }
                Eng.booleanValueUpdated(sen, args[0], con.getBoolean("Maintenance.AnnounceMisconnect"));
            }
            else {
                if (!args[0].equalsIgnoreCase("List")) {
                    Eng.argsUsage(sen, "/Maintenance <Toggle; ConnectLists; List; Misconnect; ConnectLists; remove; add; reset> [<name; boolean>]", true);
                    return true;
                }
                sen.sendMessage(Eng.CMD_MAINTENANCE_LIST_FOOTER);
                sen.sendMessage(" §0 " + Eng.CMD_MAINTENANCE_LIST_HEADER);
                for (final String s2 : con.getStringList("Maintenance.TrustedPlayers")) {
                    sen.sendMessage(Eng.PRE + " §8- §e" + s2);
                }
                sen.sendMessage(Eng.CMD_MAINTENANCE_LIST_FOOTER);
            }
            Helpy.getPlugin().saveConfig();
        }
        else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("remove") && args[0].equalsIgnoreCase("add")) {
                Eng.argsUsage(sen, "/Maintenance <Toggle; ConnectLists; List; Misconnect; ConnectLists; remove; add; reset> [<name; boolean>]", true);
                final OfflinePlayer t = Bukkit.getOfflinePlayer(args[1]);
                if (t.getName() == null) {
                    sen.sendMessage(Eng.CMD_TARGET_NOT_EXI_SAFE);
                    return true;
                }
                final ArrayList<String> tp = new ArrayList<String>(con.getStringList("Maintenance.TrustedPlayers"));
                if (args[0].equalsIgnoreCase("add")) {
                    if (tp.contains(t.getName())) {
                        sen.sendMessage(Eng.CMD_MAINTENANCE_TARGET_ALREADY_LISTED);
                        return true;
                    }
                    tp.add(t.getName());
                }
                else if (args[0].equalsIgnoreCase("remove")) {
                    if (!tp.contains(t.getName())) {
                        sen.sendMessage(Eng.CMD_MAINTENANCE_TARGET_ALREADY_UNLISTED);
                        return true;
                    }
                    tp.remove(t.getName());
                }
                sen.sendMessage(Eng.CMD_ACTION_CONFIRMED);
                con.set("Maintenance.TrustedPlayers", (Object)tp);
                Helpy.getPlugin().saveConfig();
            }
            else if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("resetSettings")) {
                final boolean msg = Boolean.parseBoolean(args[1]);
                resetMaintenanceSettings(msg);
                sen.sendMessage(Eng.CMD_ACTION_CONFIRMED);
            }
        }
        else {
            Eng.argsUsage(sen, "/Maintenance <Toggle; ConnectLists; List; Misconnect; ConnectLists; remove; add; reset> [<name; boolean>]", true);
        }
        return false;
    }
    
    public static void tryJoin(final OfflinePlayer p, final String name, final boolean broadcast, final boolean sysOut) {
        final FileConfiguration con = Helpy.getPlugin().getConfig();
        boolean trusted = false;
        if (con.getBoolean("Maintenance.AnnounceMisconnect")) {
            if (con.getBoolean("Maintenance.MaintenanceListEqualsWhitelist")) {
                final List<String> s1 = new ArrayList<String>(con.getStringList("Maintenance.TrustedPlayers"));
                if (s1 != null && s1.toString().contains(name) && !con.getBoolean("Maintenance.MaintenanceListEqualsWhitelist")) {
                    trusted = true;
                }
            }
            if (trusted) {
                if (broadcast) {
                    Bukkit.broadcastMessage(Eng.PRE + "The §3trusted §6player§e " + name + " §7tried to §bconnect§8.");
                }
                if (sysOut) {
                    System.out.println(Eng.PRE + "The §3trusted §6player§e " + name + " §7tried to §bconnect§8.");
                }
            }
            else {
                if (broadcast) {
                    Bukkit.broadcastMessage(Eng.PRE + "The §6player§e " + name + " §7tried to §bconnect§8.");
                }
                if (sysOut) {
                    System.out.println(Eng.PRE + "The §6player§e " + name + " §7tried to §bconnect§8.");
                }
            }
        }
    }
    
    public static void resetMaintenanceSettings(final boolean resetMessages) {
        final FileConfiguration con = Helpy.getPlugin().getConfig();
        if (resetMessages) {
            con.set("Maintenance.Message", (Object)(Eng.PRE + "This §6server §7is §ocurrently §7under §3Maintenance §7break§8."));
            con.set("Maintenance.TrustedMessage", (Object)(Eng.PRE + "This §6server §7is §ocurrently §7under §3Maintenance §7break§8."));
        }
        con.set("Maintenance.Toggled", (Object)false);
        con.set("Maintenance.MaintenanceListEqualsWhitelist", (Object)true);
        con.set("Maintenance.AnnounceMisconnect", (Object)true);
        con.set("Maintenance.ignoreOp", (Object)false);
        con.set("Maintenance.TrustedPlayers", (Object)Arrays.asList("tdf", "realtdf"));
        Helpy.getPlugin().saveConfig();
    }
}
