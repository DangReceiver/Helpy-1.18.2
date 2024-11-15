package de.tdf.helpy.listener.Entities.NonPlayer;

import de.tdf.helpy.helpy.Helpy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class CreeperActivateCreeper implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityDamage(final EntityDamageByEntityEvent ev) {
		if (ev.isCancelled()) return;
		if (ev.getDamager().getType() == ev.getEntityType() && ev.getEntityType() == EntityType.CREEPER) {
			final Creeper c = (Creeper) ev.getEntity();
			final Vector vec = new Vector(0.0, 0.4, 0.0);
			c.setVelocity(vec);
			ev.setCancelled(true);
			Bukkit.getScheduler().runTaskLater(Helpy.getHelpy(), c::explode, 10L);
		}
	}
}
