package de.tdf.helpy.listener.Entities.Player;

import de.tdf.helpy.methods.Other;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvSpeed implements Listener {
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		final String inv = e.getView().getTitle();
		final Player p = (Player) e.getWhoClicked();
		if (inv == "§3Choose §eSpeed") {
			e.setCancelled(true);
			int s = e.getSlot();
			if (s == 9) {
				p.setWalkSpeed(0.1f);
				p.setFlySpeed(0.1f);
			} else if (s == 10) {
				p.setWalkSpeed(0.15f);
				p.setFlySpeed(0.15f);
			} else if (s == 11) {
				p.setWalkSpeed(0.2f);
				p.setFlySpeed(0.2f);
			} else if (s == 13) {
				p.setWalkSpeed(0.25f);
				p.setFlySpeed(0.25f);
			} else if (s == 15) {
				p.setWalkSpeed(0.4f);
				p.setFlySpeed(0.4f);
			} else if (s == 16) {
				p.setWalkSpeed(0.65f);
				p.setFlySpeed(0.65f);
			} else if (s == 17) {
				p.setWalkSpeed(1.0f);
				p.setFlySpeed(1.0f);
			}
			if (s == 9 || s == 10 || s == 11 || s == 13 || s == 15 || s == 16 || s == 17) {
				p.closeInventory();
				Other.actionBar(p, "succes", "Your §eSpeed §7was set to §b" + p.getWalkSpeed() + "§8.", 0.75f, 1.25f);
			}
		}
	}
}
