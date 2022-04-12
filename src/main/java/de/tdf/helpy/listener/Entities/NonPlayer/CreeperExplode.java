package de.tdf.helpy.listener.Entities.NonPlayer;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;

import java.util.stream.Stream;
import java.util.function.Consumer;
import java.util.Objects;
import java.util.function.Function;

import org.bukkit.potion.PotionEffect;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.Listener;

public class CreeperExplode implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void entityExplode(final EntityExplodeEvent e) {
        if (e.isCancelled())
            return;
        if (e.getEntityType() == EntityType.CREEPER) {
            Creeper c = (Creeper) e.getEntity();
            Objects.requireNonNull(c);
            c.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(c::removePotionEffect);
        }
    }
}
