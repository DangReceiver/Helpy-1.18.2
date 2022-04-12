package de.tdf.helpy.listener.Entities.NonPlayer;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import de.tdf.helpy.helpy.Helpy;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;

public class CreeperActivateCreeper implements Listener
{
    @EventHandler(priority = EventPriority.LOW)
    public void onEntityDamage(final EntityDamageByEntityEvent ev) {
        if (ev.isCancelled()) {
            return;
        }
        if (ev.getDamager().getType() == ev.getEntityType() && ev.getEntityType() == EntityType.CREEPER) {
            final Creeper c = (Creeper)ev.getEntity();
            final Vector vec = new Vector(0.0, 0.5, 0.0);
            c.setVelocity(vec);
            ev.setCancelled(true);
            Bukkit.getScheduler().runTaskLater((Plugin)Helpy.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    c.explode();
                }
            }, 10L);
        }
    }
}
