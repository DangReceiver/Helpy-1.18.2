package de.tdf.helpy.commands.control.personal;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import de.tdf.helpy.methods.lang.Eng;
import de.tdf.helpy.helpy.Helpy;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.List;
import org.bukkit.command.CommandExecutor;

public class God implements CommandExecutor
{
    public static List<String> iva;
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (!Helpy.survivalMode) {
                if (p.hasPermission("Helpy.god.self")) {
                    if (args.length == 0) {
                        if (!God.iva.contains(p.getName())) {
                            God.iva.add(p.getName());
                            Eng.atChange((CommandSender)p, "action", "§bInvulnerability", true, "false", "true", true, false);
                            p.setInvulnerable(true);
                        }
                        else if (God.iva.contains(p.getName())) {
                            God.iva.remove(p.getName());
                            Eng.atChange((CommandSender)p, "action", "§bInvulnerability", true, "true", "false", true, false);
                            p.setInvulnerable(false);
                        }
                    }
                    else if (args.length == 1) {
                        if (p.hasPermission("Helpy.god.others")) {
                            final Player t = Bukkit.getPlayer(args[0]);
                            if (t != null && t != p) {
                                if (!God.iva.contains(t.getName())) {
                                    God.iva.add(t.getName());
                                    t.setInvulnerable(true);
                                    Eng.atChange((CommandSender)p, "action", "§bInvulnerability", true, "false", "true", true, true);
                                    Eng.atChange((CommandSender)t, "chat", "§bInvulnerability", true, "false", "ture", true, false);
                                }
                                else if (God.iva.contains(t.getName())) {
                                    God.iva.remove(t.getName());
                                    t.setInvulnerable(false);
                                    Eng.atChange((CommandSender)p, "action", "§bInvulnerability", true, "true", "false", true, true);
                                    Eng.atChange((CommandSender)t, "chat", "§bInvulnerability", true, "true", "false", true, false);
                                }
                            }
                            else {
                                sender.sendMessage(Eng.CMD_TARGET_NOT_EXI_POSSIBLY);
                            }
                        }
                    }
                    else {
                        sender.sendMessage(Eng.CMD_ARGS_LENGHT);
                        Eng.argsUsage(sender, "/god <player name>", true);
                    }
                }
                else {
                    Eng.permissionShow(sender, "Helpy.god.self");
                }
            }
            else {
                p.sendMessage(Eng.MECHANIC_SURVIVAL_MODE_TRUE);
            }
        }
        else {
            sender.sendMessage(Eng.CMD_NOT_PLAYER);
        }
        return false;
    }
    
    static {
        God.iva = new ArrayList<String>();
    }
}
