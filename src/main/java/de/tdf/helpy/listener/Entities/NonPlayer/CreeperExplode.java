package de.tdf.helpy.listener.Entities.NonPlayer;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;

public class CreeperExplode implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void entityExplode(final EntityExplodeEvent e) {
		if (e.isCancelled()) return;
		if (e.getEntityType() == EntityType.CREEPER) {
			Creeper c = (Creeper) e.getEntity();
			Objects.requireNonNull(c);
			c.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(c::removePotionEffect);
		}
	}
}
