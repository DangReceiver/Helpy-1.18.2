package de.tdf.helpy.methods.items;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Arrays;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class IB {

	public static ItemStack getFiller(final Material m, final boolean def, final boolean glint, final String name, final String lore) {
		if (!m.toString().contains("_PANE"))
			System.out.println("Helpy: The material type ''GLASS_PANE'' is suggested; You will need to change it to avoid this message.");
		final ItemStack i = new ItemStack(m);
		if (!def) lore(name(i, name), lore);
		else lore(name(i, " §0 "), " §0 ");
		if (glint) flag(ench(i, Enchantment.DURABILITY, 0), ItemFlag.HIDE_ENCHANTS);
		return i;
	}

	public static ItemStack pricing(final ItemStack i, final String dName, final float price, final boolean ver, final boolean si, final boolean eng) {
		String k,
				fo,
				sing;
		if (!eng) {
			k = "§6Kaufen§8:";
			fo = "f\u00fcr §e";
			sing = "§8§oKann nur einzeln gekauft werden.";
		} else {
			k = "§6Buy§8:";
			fo = "for §e";
			sing = "§8§oYou can only buy one at a time.";
		}
		float f = 0.98f,
				s = 0.95f,
				u = 0.92f;
		if (ver) {
			if (eng)
				k = "§eVerkaufen§8:";
			else
				k = "§eSell§8:";
			f = 1.02f;
			s = 1.05f;
			u = 1.08f;
		}
		name(i, dName);
		if (!si)
			lore(i, k, "§71 " + fo + price + " §6Coins", "§74 " + fo + Math.round(price * 4.0f * f) + " §6Coins", "§716 " + fo + "§e" + Math.round(price * 16.0f * s) + " §6Coins", "§764 " + fo + "§e" + Math.round(price * 64.0f * u) + " §6Coins");
		else lore(i, k, "§71 " + f + price + " §6Coins", sing);
		return i;
	}

	public static ItemStack lore(final ItemStack item, final String... lore) {
		final ItemMeta itemM = item.getItemMeta();
		itemM.setLore(Arrays.asList(lore));
		item.setItemMeta(itemM);
		return item;
	}

	public static List<String> getlore(final ItemStack item) {
		return item.getItemMeta().getLore();
	}

	public static ItemStack lore(final ItemStack item, final List<String> lore) {
		final ItemMeta itemM = item.getItemMeta();
		itemM.setLore(lore);
		item.setItemMeta(itemM);
		return item;
	}

	public static ItemStack name(final ItemStack item, final String name) {
		final ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(name);
		item.setItemMeta(itemM);
		return item;
	}

	public static ItemStack addLore(final ItemStack item, final List<String> addedLore, List<String> loreBefore) {
		final ItemMeta itemM = item.getItemMeta();
		if (loreBefore == null) {
			loreBefore = getlore(item);
		}
		loreBefore.addAll(addedLore);
		itemM.setLore(addedLore);
		item.setItemMeta(itemM);
		return item;
	}

	public static ItemStack ench(final ItemStack item, final Enchantment ench, final int level) {
		final ItemMeta itemM = item.getItemMeta();
		itemM.addEnchant(ench, level, true);
		item.setItemMeta(itemM);
		return item;
	}

	public static ItemStack flag(final ItemStack item, final ItemFlag flag) {
		final ItemMeta itemM = item.getItemMeta();
		itemM.addItemFlags(flag);
		item.setItemMeta(itemM);
		return item;
	}

	public static void invFiller(final Inventory in, final ItemStack filler, final int inventoryRows) {
		for (int inventorySize = inventoryRows * 9 - 1, times = 0; times <= inventorySize; ++times) {
			in.setItem(times, filler);
		}
	}
}
