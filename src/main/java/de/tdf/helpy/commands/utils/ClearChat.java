package de.tdf.helpy.commands.utils;

import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChat implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("Helpy.chatclear")) {
                if (args.length == 0) {
                    for (int times = 0; times <= 500; ++times) {
                        Bukkit.broadcastMessage(" \n §0» \n ");
                    }
                    Bukkit.broadcastMessage(Eng.CHAT_CLEARED);
                }
                else {
                    p.sendMessage(Eng.CMD_ARGS_LENGHT);
                }
            }
            else {
                Eng.permissionShow(sender, "Helpy.chatclear");
            }
        }
        else if (args.length == 0) {
            for (int times2 = 0; times2 <= 200; ++times2) {
                Bukkit.broadcastMessage("\n §7» \n");
            }
            Bukkit.broadcastMessage(Eng.CHAT_CLEARED);
        }
        else {
            sender.sendMessage(Eng.CMD_ARGS_LENGHT);
        }
        return false;
    }
}
