package de.tdf.helpy.commands.control.personal;

import org.bukkit.Bukkit;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class ArrowsInBody implements CommandExecutor
{
    public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
        if (sen.hasPermission("helpy.ArrowsInBody")) {
            if (sen instanceof Player) {
                final Player p = (Player)sen;
                if (args.length == 0) {
                    Eng.atChange((CommandSender)p, "action", "§eArrows §7in §eBody", false, "" + p.getArrowsInBody(), "0", true, false);
                    p.setArrowsInBody(0);
                }
                else if (args.length == 1) {
                    try {
                        final int a = Integer.parseInt(args[0]);
                        if (!(sen instanceof Player)) {
                            sen.sendMessage(Eng.CMD_NOT_PLAYER);
                            return true;
                        }
                        Eng.atChange((CommandSender)p, "action", "§eArrows §7in §eBody", false, "" + p.getArrowsInBody(), "" + a, true, false);
                        p.setArrowsInBody(a);
                    }
                    catch (NumberFormatException e) {
                        final Player t = Bukkit.getPlayer(args[0]);
                        if (t == null) {
                            sen.sendMessage(Eng.CMD_TARGET_NOT_EXI_SAFE);
                            Eng.argsUsage(sen, "/ArrowsInBody [<Integer; Player>] [<Integer>]", true);
                            return true;
                        }
                        Eng.atChange((CommandSender)t, "action", "§eArrows §7in §eBody", false, "" + t.getArrowsInBody(), "0", true, false);
                        Eng.atChange(sen, "action", "§eArrows §7in §eBody", false, "" + t.getArrowsInBody(), "0", true, true);
                        t.setArrowsInBody(0);
                    }
                }
            }
            else if (args.length == 2) {
                final Player t2 = Bukkit.getPlayer(args[0]);
                if (t2 != null) {
                    try {
                        final int a = Integer.parseInt(args[1]);
                        Eng.atChange((CommandSender)t2, "chat", "§eArrows §7in §eBody", false, "" + t2.getArrowsInBody(), "" + a, true, false);
                        Eng.atChange(sen, "action", "§eArrows §7in §eBody", false, "" + t2.getArrowsInBody(), "" + a, true, true);
                        t2.setArrowsInBody(a);
                        return false;
                    }
                    catch (NumberFormatException e) {
                        Eng.entryType(sen, "1", "integer");
                        return true;
                    }
                }
                sen.sendMessage(Eng.CMD_TARGET_NOT_EXI_SAFE);
            }
            else {
                sen.sendMessage(Eng.CMD_NOT_PLAYER);
            }
        }
        else {
            Eng.permissionShow(sen, "helpy.ArrowsInBody");
        }
        return false;
    }
}
