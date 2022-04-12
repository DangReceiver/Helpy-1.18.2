package de.tdf.helpy.listener.background.Inventory;

import org.bukkit.event.EventHandler;
import de.tdf.helpy.methods.Other;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;

public class InvSpeed implements Listener
{
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        final String inv = e.getView().getTitle();
        final Player p = (Player)e.getWhoClicked();
        if (inv == "§3Choose §eSpeed") {
            e.setCancelled(true);
            if (e.getSlot() == 9) {
                p.setWalkSpeed(0.1f);
                p.setFlySpeed(0.1f);
            }
            else if (e.getSlot() == 10) {
                p.setWalkSpeed(0.15f);
                p.setFlySpeed(0.15f);
            }
            else if (e.getSlot() == 11) {
                p.setWalkSpeed(0.2f);
                p.setFlySpeed(0.2f);
            }
            else if (e.getSlot() == 13) {
                p.setWalkSpeed(0.25f);
                p.setFlySpeed(0.25f);
            }
            else if (e.getSlot() == 15) {
                p.setWalkSpeed(0.4f);
                p.setFlySpeed(0.4f);
            }
            else if (e.getSlot() == 16) {
                p.setWalkSpeed(0.65f);
                p.setFlySpeed(0.65f);
            }
            else if (e.getSlot() == 17) {
                p.setWalkSpeed(1.0f);
                p.setFlySpeed(1.0f);
            }
            if (e.getSlot() == 9 || e.getSlot() == 10 || e.getSlot() == 11 || e.getSlot() == 13 || e.getSlot() == 15 || e.getSlot() == 16 || e.getSlot() == 17) {
                p.closeInventory();
                Other.actionBar(p, "succes", "Your §eSpeed §7was set to §b" + p.getWalkSpeed() + "§8.", 0.75f, 1.25f);
            }
        }
    }
}
