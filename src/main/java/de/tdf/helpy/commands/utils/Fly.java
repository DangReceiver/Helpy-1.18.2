package de.tdf.helpy.commands.utils;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Fly implements CommandExecutor
{
    public static List<String> fly;
    
    public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
        if (sen instanceof Player) {
            final Player p = (Player)sen;
            if (!Helpy.survivalMode) {
                if (p.hasPermission("Helpy.fly.self")) {
                    if (args.length == 0) {
                        if (!Fly.fly.contains(p.getName())) {
                            Fly.fly.add(p.getName());
                            Eng.atChange(sen, "action", "§9Fly mode", true, "false", "true", true, false);
                            p.setAllowFlight(true);
                            p.setFlying(true);
                        }
                        else if (Fly.fly.contains(p.getName())) {
                            Fly.fly.remove(p.getName());
                            Eng.atChange(sen, "action", "§9Fly mode", true, "true", "false", true, false);
                            p.setAllowFlight(false);
                            p.setFlying(false);
                        }
                    }
                    else if (args.length == 1) {
                        if (p.hasPermission("Helpy.fly.others")) {
                            final Player t = Bukkit.getPlayer(args[0]);
                            if (t != null) {
                                if (!Fly.fly.contains(t.getName())) {
                                    Fly.fly.add(t.getName());
                                    t.setAllowFlight(true);
                                    t.setFlying(true);
                                    Eng.atChange((CommandSender)t, "chat", "§9Fly mode", true, "false", "true", true, false);
                                    Eng.atChange(sen, "action", "§9Fly", true, "false", "true", true, true);
                                }
                                else if (Fly.fly.contains(p.getName())) {
                                    Fly.fly.remove(t.getName());
                                    Eng.atChange((CommandSender)t, "chat", "§9Fly mode", true, "true", "false", true, false);
                                    Eng.atChange(sen, "action", "§9Fly", true, "true", "false", true, true);
                                    t.setAllowFlight(false);
                                    t.setFlying(false);
                                }
                            }
                            else {
                                sen.sendMessage(Eng.CMD_TARGET_NOT_EXI_POSSIBLY);
                            }
                        }
                    }
                    else {
                        sen.sendMessage(Eng.CMD_ARGS_LENGHT);
                        Eng.argsUsage(sen, "/fly <player name>", true);
                    }
                }
                else {
                    Eng.permissionShow(sen, "Helpy.fly.self");
                }
            }
            else {
                p.sendMessage(Eng.MECHANIC_SURVIVAL_MODE_TRUE);
            }
        }
        else {
            sen.sendMessage(Eng.CMD_NOT_PLAYER);
        }
        return false;
    }
    
    static {
        Fly.fly = new ArrayList<String>();
    }
}
