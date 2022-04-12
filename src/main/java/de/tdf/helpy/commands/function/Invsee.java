package de.tdf.helpy.commands.function;

import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Invsee implements CommandExecutor
{
    public boolean onCommand(final CommandSender send, final Command command, final String label, final String[] args) {
        if (send instanceof Player) {
            final Player p = (Player)send;
            if (p.hasPermission("Helpy.invsee")) {
                if (args.length == 1) {
                    final OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                    final Player asOnline = target.getPlayer();
                    p.closeInventory();
                    if (target == null || asOnline == null) {
                        p.sendMessage(Eng.CMD_TARGET_NOT_EXI_SAFE);
                        return true;
                    }
                    final Inventory tarInv = (Inventory)asOnline.getInventory();
                    p.sendMessage(Eng.PRE + "You now see§8: §e" + args[0] + "§8'§es §9inventory§8.");
                    p.openInventory(tarInv);
                    return true;
                }
                else {
                    Eng.argsUsage(send, "§e/§6invsee §8<§eSpieler§8>", true);
                }
            }
            else {
                Eng.permissionShow(send, "Helpy.invsee");
            }
        }
        else {
            send.sendMessage(Eng.CMD_NOT_PLAYER);
        }
        return false;
    }
}
