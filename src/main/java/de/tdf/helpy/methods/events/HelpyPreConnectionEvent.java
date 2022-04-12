package de.tdf.helpy.methods.events;

import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class HelpyPreConnectionEvent extends Event
{
    private static final HandlerList handlers;
    OfflinePlayer safeConnecter;
    AsyncPlayerPreLoginEvent.Result Result;
    
    public HelpyPreConnectionEvent(final OfflinePlayer p, final AsyncPlayerPreLoginEvent.Result loginResult) {
        this.safeConnecter = p;
        this.Result = loginResult;
    }
    
    public OfflinePlayer getSafeConnecter() {
        return this.safeConnecter;
    }
    
    public AsyncPlayerPreLoginEvent.Result getResult() {
        return this.Result;
    }
    
    public void setResult(final AsyncPlayerPreLoginEvent.Result Result) {
        this.Result = Result;
    }
    
    public HandlerList getHandlers() {
        return HelpyPreConnectionEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return HelpyPreConnectionEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
