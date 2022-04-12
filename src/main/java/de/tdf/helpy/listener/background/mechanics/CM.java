package de.tdf.helpy.listener.background.mechanics;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Event;
import de.tdf.helpy.methods.events.HelpyPreConnectionEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.configuration.file.FileConfiguration;
import de.tdf.helpy.methods.lang.Eng;
import de.tdf.helpy.helpy.Helpy;
import org.bukkit.OfflinePlayer;
import de.tdf.helpy.methods.pConfig;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class CM implements Listener
{
    @EventHandler
    public void cmJoin(final PlayerJoinEvent e) {
        final pConfig pc = pConfig.loadConfig((OfflinePlayer)e.getPlayer(), "Helpy");
        pc.set("Name", e.getPlayer().getName());
        pc.savePCon();
        final FileConfiguration con = Helpy.getPlugin().getConfig();
        if (con.getBoolean("Settings.CustomJoinMessage")) {
            e.setJoinMessage(Eng.PRE + "The §6player §e" + e.getPlayer().getName() + " §aconnected§8.");
        }
        if (con.getBoolean("Settings.SilentJoin")) {
            e.setJoinMessage("");
        }
    }
    
    @EventHandler
    public void cmPreJoin(final AsyncPlayerPreLoginEvent e) {
        final OfflinePlayer p = Bukkit.getOfflinePlayer(e.getUniqueId());
        if (!Helpy.preStartDone) {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Eng.PRE_JOIN_STARTING);
        }
        final FileConfiguration con = Helpy.getPlugin().getConfig();
        if (!p.isOp() && !con.getBoolean("Maintenance.ignoreOp") && con.getBoolean("Maintenance.Toggled")) {
            if (con.getBoolean("Maintenance.MaintenanceListEqualsWhitelist")) {
                if (!p.isWhitelisted()) {
                    Maintenance.tryJoin(p, e.getName(), true, false);
                    e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                    e.setKickMessage(con.getString("Maintenance.Message"));
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, con.getString("Maintenance.Message"));
                }
            }
            else if (!con.getStringList("Maintenance.TrustedPlayers").contains(p.getName())) {
                Maintenance.tryJoin(p, e.getName(), true, false);
                e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                e.setKickMessage(con.getString("Maintenance.TrustedMessage"));
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, con.getString("Maintenance.TrustedMessage"));
            }
        }
        Bukkit.getScheduler().runTaskLater((Plugin)Helpy.getPlugin(), () -> Bukkit.getPluginManager().callEvent((Event)new HelpyPreConnectionEvent(p, e.getLoginResult())), 0L);
    }
    
    @EventHandler
    public void cmQuit(final PlayerQuitEvent e) {
        final FileConfiguration con = Helpy.getPlugin().getConfig();
        if (con.getBoolean("Settings.CustomQuitMessage")) {
            e.setQuitMessage(Eng.PRE + "The §6player §e" + e.getPlayer().getName() + " §cdisconnected§8.");
        }
        if (con.getBoolean("Settings.SilentQuit")) {
            e.setQuitMessage("");
        }
        final Player p = e.getPlayer();
        if (God.iva.contains(p.getName())) {
            God.iva.remove(p.getName());
            p.setInvulnerable(false);
        }
        if (Fly.fly.contains(p.getName())) {
            Fly.fly.remove(p.getName());
            p.setFlying(false);
            p.setAllowFlight(false);
        }
    }
}
