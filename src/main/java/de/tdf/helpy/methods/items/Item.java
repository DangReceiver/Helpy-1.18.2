package de.tdf.helpy.methods.items;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import de.tdf.helpy.methods.OldpCon;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Item
{
    public static int enchPlusOne(final ItemStack item, final Enchantment ench) {
        final ItemMeta itemM = item.getItemMeta();
        int level = 1;
        if (itemM.hasEnchant(ench)) {
            level = item.getEnchantmentLevel(ench) + 1;
            if (item.getEnchantmentLevel(ench) > ench.getMaxLevel() + 1) {
                level = 99;
            }
        }
        item.setItemMeta(itemM);
        return level;
    }
    
    public static void handleAchieve(final Inventory inv, final String colorcode, final String achvString, final String achvDisplayName, final int slot, final Player p, final boolean isBoolean, final ItemStack gra, final ItemStack red, final ItemStack o, final ItemStack y, final ItemStack lim, final ItemStack cy) {
        IB.name(gra, "§" + colorcode + achvDisplayName);
        IB.name(red, "§" + colorcode + achvDisplayName);
        IB.name(o, "§" + colorcode + achvDisplayName);
        IB.name(y, "§" + colorcode + achvDisplayName);
        IB.name(lim, "§" + colorcode + achvDisplayName);
        IB.name(cy, "§" + colorcode + achvDisplayName);
        final OldpCon pCon = OldpCon.loadConfig((OfflinePlayer)p, "FarmCountry");
        if (!isBoolean) {
            if (pCon.getInt("CustomStats." + achvString) == 0) {
                IB.lore(gra, "§9§oFortschritt§8:", "§8[§c||||||||||§8]");
                inv.setItem(slot, gra);
            }
            else if (pCon.getInt("CustomStats." + achvString) > 0 && pCon.getInt("CustomStats." + achvString) <= 24) {
                IB.lore(red, "§9§oFortschritt§8:", "§8[§a||§c||||||||§8]");
                inv.setItem(slot, red);
            }
            else if (pCon.getInt("CustomStats." + achvString) >= 25 && pCon.getInt("CustomStats." + achvString) <= 49) {
                IB.lore(o, "§9§oFortschritt§8:", "§8[§a|||||§c|||||§8]");
                inv.setItem(slot, o);
            }
            else if (pCon.getInt("CustomStats." + achvString) >= 50 && pCon.getInt("CustomStats." + achvString) <= 74) {
                IB.lore(y, "§9§oFortschritt§8:", "§8[§a||||||§c||||§8]");
                inv.setItem(slot, y);
            }
            else if (pCon.getInt("CustomStats." + achvString) >= 75 && pCon.getInt("CustomStats." + achvString) <= 99) {
                IB.lore(lim, "§9§oFortschritt§8:", "§8[§a||||||||§c||§8]");
                inv.setItem(slot, lim);
            }
            else if (pCon.getInt("CustomStats." + achvString) >= 100) {
                IB.lore(cy, "§9§oFortschritt§8:", "§8[§a||||||||||§8]");
                inv.setItem(slot, cy);
            }
            else {
                IB.lore(gra, "§9§oFortschritt§8:", "§8[§c||||||||||§8]");
                inv.setItem(slot, gra);
            }
        }
        else if (!pCon.getBoolean("CustomStats." + achvString)) {
            IB.lore(gra, "§9§oFortschritt§8:", "§8[§c|§8]");
            inv.setItem(slot, gra);
        }
        else {
            IB.lore(cy, "§9§oFortschritt§8:", "§8[§a|§8]");
            inv.setItem(slot, cy);
        }
    }
    
    public static void singleAdds(final ItemStack item, final Inventory inv, final int amount) {
        for (int times = 0; times < amount; ++times) {
            inv.addItem(new ItemStack[] { item });
        }
    }
    
    public static int getItemAmount(final Material mat, final Inventory inv) {
        final Inventory in = inv;
        int gesMat = 0;
        for (int t = 0; t < in.getSize(); ++t) {
            if (in.getItem(t) != null && in.getItem(t).getType() == mat) {
                gesMat += in.getItem(t).getAmount();
            }
        }
        return gesMat;
    }
    
    public static int getFreeSlots(final Inventory i, final boolean checkArmor) {
        int free = 0;
        if (checkArmor) {
            for (int t = 0; t < i.getSize(); ++t) {
                if (i.getItem(t) == null) {
                    ++free;
                }
            }
        }
        else {
            for (int t = 0; t < i.getSize() - 5; ++t) {
                if (i.getItem(t) == null) {
                    ++free;
                }
            }
        }
        return free;
    }
    
    public static int getItemAmountSpecial(final ItemStack item, final Inventory inv) {
        if(item == null || item.getItemMeta() == null) return 0;
        final ItemMeta itemM = item.getItemMeta();
        final Inventory in = inv;
        int gesMat = 0;
        itemM.addEnchant(Enchantment.DURABILITY, 0, true);
        itemM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        for (int times = 0; times < in.getSize(); ++times) {
            if (in.getItem(times) != null && in.getItem(times).equals((Object)item)) {
                gesMat += in.getItem(times).getAmount();
            }
        }
        return gesMat;
    }
    
    public static void removeItems(final Material mat, int amount, final Inventory inv) {
        final Inventory in = inv;
        final ItemStack item = new ItemStack(mat);
        final ItemStack air = new ItemStack(Material.AIR);
        for (int times = 0; times <= in.getSize(); ++times) {
            if (in.getItem(times) != null && in.getItem(times).getType() == mat) {
                if (in.getItem(times).getAmount() >= amount) {
                    final int amo = in.getItem(times).getAmount() - amount;
                    item.setAmount(amo);
                    in.setItem(times, item);
                    return;
                }
                if (in.getItem(times).getAmount() <= amount) {
                    amount -= in.getItem(times).getAmount();
                    in.setItem(times, air);
                }
            }
        }
    }
    
    public static void removeItemsSpecial(final ItemStack item, int amount, final Inventory inv) {
        final Inventory in = inv;
        final ItemStack air = new ItemStack(Material.AIR);
        for (int times = 0; times <= in.getSize(); ++times) {
            if (in.getItem(times) != null && in.getItem(times).equals((Object)item)) {
                if (in.getItem(times).getAmount() >= amount) {
                    final int amo = in.getItem(times).getAmount() - amount;
                    item.setAmount(amo);
                    in.setItem(times, item);
                }
                else if (in.getItem(times).getAmount() <= amount) {
                    amount -= in.getItem(times).getAmount();
                    in.setItem(times, air);
                }
            }
        }
    }
    
    public static void replaceInString(String filteredMessage, final String before, final String after) {
        if (filteredMessage.contains(before)) {
            filteredMessage = filteredMessage.replaceAll(before, after);
        }
    }
}
