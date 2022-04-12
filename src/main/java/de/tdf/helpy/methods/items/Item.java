package de.tdf.helpy.methods.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

	public static int enchPlusOne(final ItemStack item, final Enchantment ench) {
		final ItemMeta itemM = item.getItemMeta();
		int level = 1;
		if (itemM.hasEnchant(ench)) {
			level = item.getEnchantmentLevel(ench) + 1;
			if (item.getEnchantmentLevel(ench) > ench.getMaxLevel() + 1)
				level = 99;
		}
		item.setItemMeta(itemM);
		return level;
	}

	public static void singleAdds(final ItemStack item, final Inventory inv, final int amount) {
		for (int times = 0; times < amount; ++times)
			inv.addItem(item);
	}

	public static int getItemAmount(final Material mat, final Inventory inv) {
		final Inventory in = inv;
		int gesMat = 0;
		for (int t = 0; t < in.getSize(); ++t)
			if (in.getItem(t) != null && in.getItem(t).getType() == mat)
				gesMat += in.getItem(t).getAmount();
		return gesMat;
	}

	public static int getFreeSlots(final Inventory i, final boolean checkArmor) {
		int free = 0;
		if (checkArmor) {
			for (int t = 0; t < i.getSize(); ++t)
				if (i.getItem(t) == null)
					++free;
		} else
			for (int t = 0; t < i.getSize() - 5; ++t)
				if (i.getItem(t) == null)
					++free;
		return free;
	}

	public static int getItemAmountSpecial(final ItemStack item, final Inventory inv) {
		if (item == null || item.getItemMeta() == null) return 0;
		final ItemMeta itemM = item.getItemMeta();
		int gesMat = 0;
		itemM.addEnchant(Enchantment.DURABILITY, 0, true);
		itemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		for (int times = 0; times < inv.getSize(); ++times) {
			if (inv.getItem(times) != null && inv.getItem(times).equals(item)) {
				gesMat += inv.getItem(times).getAmount();
			}
		}
		return gesMat;
	}

	public static void removeItems(final Material mat, int amount, final Inventory inv) {
		final ItemStack item = new ItemStack(mat), air = new ItemStack(Material.AIR);
		for (int times = 0; times <= inv.getSize(); ++times) {
			if (inv.getItem(times) != null && inv.getItem(times).getType() == mat) {
				if (inv.getItem(times).getAmount() >= amount) {
					final int amo = inv.getItem(times).getAmount() - amount;
					item.setAmount(amo);
					inv.setItem(times, item);
					return;
				}
				if (inv.getItem(times).getAmount() <= amount) {
					amount -= inv.getItem(times).getAmount();
					inv.setItem(times, air);
				}
			}
		}
	}

	public static void removeItemsSpecial(final ItemStack item, int amount, final Inventory inv) {
		final Inventory in = inv;
		final ItemStack air = new ItemStack(Material.AIR);
		for (int times = 0; times <= in.getSize(); ++times) {
			if (in.getItem(times) != null && in.getItem(times).equals(item)) {
				if (in.getItem(times).getAmount() >= amount) {
					final int amo = in.getItem(times).getAmount() - amount;
					item.setAmount(amo);
					in.setItem(times, item);
				} else if (in.getItem(times).getAmount() <= amount) {
					amount -= in.getItem(times).getAmount();
					in.setItem(times, air);
				}
			}
		}
	}

	public static void replaceInString(String filteredMessage, final String before, final String after) {
		if (filteredMessage.contains(before))
			filteredMessage = filteredMessage.replaceAll(before, after);
	}
}
