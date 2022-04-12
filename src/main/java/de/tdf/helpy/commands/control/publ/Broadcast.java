package de.tdf.helpy.commands.control.publ;

import java.util.Iterator;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Broadcast implements CommandExecutor
{
    public boolean onCommand(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
        if (args.length >= 2) {
            final String[] m = args;
            boolean space = true;
            final boolean mul = false;
            String s = "";
            for (int i = 1; i < args.length; ++i) {
                s = s + " " + args[i];
            }
            if (args[0].equalsIgnoreCase("true")) {
                s.replace("true", "");
                space = true;
            }
            else if (args[0].equalsIgnoreCase("false")) {
                s.replace("false", "");
                space = false;
            }
            s = s.replaceAll("&", "§").replaceAll("%%", "%").replaceAll("null", " ").replaceAll("  ", "");
            for (final Player ap : Bukkit.getOnlinePlayers()) {
                if (space) {
                    ap.sendMessage(" §0 »");
                    ap.sendMessage("§8§m§l------------------------§8§m§l------------------------");
                    ap.sendMessage(" §0»");
                }
                ap.sendMessage(s);
                if (space) {
                    ap.sendMessage(" §0» §0 ");
                    ap.sendMessage("§8§m§l------------------------§8§m§l------------------------");
                    ap.sendMessage("  §0» §0 ");
                }
            }
            System.out.println("Broadcast ''" + s + "'' was sent succesfully. [Sender: " + sen.getName() + "]");
        }
        else {
            Eng.argsUsage(sen, "/broadcast <Spacer: true/false> <Message>", true);
        }
        return false;
    }
}
