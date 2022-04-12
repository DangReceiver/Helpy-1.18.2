// 
// Decompiled by Procyon v0.5.36
// 

package de.tdf.helpy.commands.control.personal;

import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;
import org.bukkit.util.StringUtil;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import de.tdf.helpy.methods.lang.Eng;
import de.tdf.helpy.helpy.Helpy;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.List;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.CommandExecutor;

public class Ench implements CommandExecutor, TabCompleter
{
    private static final List<String> CMD;
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (!Helpy.survivalMode) {
                if (p.hasPermission("Helpy.Ench")) {
                    if (args.length == 2) {
                        final ItemMeta m = p.getInventory().getItemInMainHand().getItemMeta();
                        int level;
                        try {
                            level = Integer.parseInt(args[1]);
                            if (level < 1 || level >= 16001) {
                                if (level != 0) {
                                    Eng.numbHighLow(sender, "1", "integer", "16000", false);
                                    return true;
                                }
                                m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                                p.sendMessage(Eng.CMD_ENCH_CAUSE_ZERO);
                            }
                        }
                        catch (NumberFormatException e) {
                            sender.sendMessage("This is §cnot §7a §avalid §6number§8.");
                            return true;
                        }
                        final String s = args[0].toUpperCase();
                        if (Enchantment.getByName(s) != null) {
                            final Enchantment ench = Enchantment.getByName(s);
                            final ItemStack item = p.getInventory().getItemInMainHand();
                            m.addEnchant(ench, level, true);
                            p.sendMessage(Eng.PRE + "Enchantment §§2" + args[0] + "§§8, §§bLevel §§9" + level + " §§7was §§aadded§§8.");
                            item.setItemMeta(m);
                            p.getInventory().setItemInMainHand(item);
                            return true;
                        }
                        p.sendMessage(Eng.CMD_ENCHANT_INVALID);
                    }
                    else {
                        p.sendMessage(Eng.CMD_ARGS_LENGHT);
                    }
                }
                else {
                    Eng.permissionShow(sender, "Helpy.Ench");
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
    
    public List<String> onTabComplete(final CommandSender sen, final Command cmd, final String lab, final String[] args) {
        final List<String> com = new ArrayList<String>();
        final List<String> cmd2 = new ArrayList<String>();
        if (args.length == 1) {
            com.clear();
            for (final Enchantment e : Enchantment.values()) {
                com.add(e.getName());
            }
            StringUtil.copyPartialMatches(args[0], (Iterable)cmd2, (Collection)com);
        }
        else if (args.length == 2) {
            StringUtil.copyPartialMatches(args[0], (Iterable)Ench.CMD, (Collection)com);
        }
        Collections.sort(com);
        return com;
    }
    
    static {
        CMD = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "8", "10", "12", "15", "20", "25", "30", "40", "50", "65", "75", "85", "100", "120", "150", "200", "250", "300", "350", "400", "450", "500", "650", "800", "1000", "1500", "2500", "4000", "8000", "12000", "16000");
    }
}
