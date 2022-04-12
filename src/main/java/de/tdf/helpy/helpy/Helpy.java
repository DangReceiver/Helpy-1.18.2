package de.tdf.helpy.helpy;

import de.tdf.helpy.commands.control.personal.ArrowsInBody;
import de.tdf.helpy.commands.control.personal.EcClear;
import de.tdf.helpy.commands.control.personal.Ench;
import de.tdf.helpy.commands.control.personal.Fly;
import de.tdf.helpy.commands.control.personal.Gm;
import de.tdf.helpy.commands.control.personal.God;
import de.tdf.helpy.commands.control.personal.Heal;
import de.tdf.helpy.commands.control.personal.Health;
import de.tdf.helpy.commands.control.personal.Invsee;
import de.tdf.helpy.commands.control.personal.Lore;
import de.tdf.helpy.commands.control.personal.Speed;
import de.tdf.helpy.commands.control.personal.TpExact;
import de.tdf.helpy.commands.control.publ.Broadcast;
import de.tdf.helpy.commands.control.publ.ClearChat;
import de.tdf.helpy.commands.control.publ.Day;
import de.tdf.helpy.commands.control.publ.Maintenance;
import de.tdf.helpy.commands.ffa.HelpyHelp;
import de.tdf.helpy.commands.ffa.Hunger;
import de.tdf.helpy.commands.ffa.Spawn;
import de.tdf.helpy.listener.Entities.NonPlayer.CreeperActivateCreeper;
import de.tdf.helpy.listener.Entities.NonPlayer.CreeperExplode;
import de.tdf.helpy.listener.Entities.Player.ClickGrowed;
import de.tdf.helpy.listener.Entities.Player.Doors;
import de.tdf.helpy.listener.Entities.Player.TreeCutDown;
import de.tdf.helpy.listener.background.Inventory.InvSpeed;
import de.tdf.helpy.listener.background.mechanics.CM;
import de.tdf.helpy.methods.lang.Eng;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Helpy extends JavaPlugin {
    private static Helpy helpy;

    public static String VERSION = "";

    public static List<Material> consumable = null;

    public static List<String> listeners = new ArrayList<>(
            Arrays.asList("TreeCutDown", "Doors", "CreeperRemovePotion",
                    "CreeperActivateCreeper", "ClickGrowedSeed", "JukeBox"));

    public static World defWorld = Bukkit.getWorld("world");

    public static boolean preStartDone = false, stable = true, survivalMode = false, growedPerm = false;

    public static boolean grownExp = false, sWlWorld = false, disKickAll = true;

    public void onEnable() {
        helpy = this;
        VERSION = helpy.getVersion();
        FileConfiguration con = getConfig();
        Bukkit.getPluginManager().registerEvents(new CM(), this);
        Bukkit.getPluginManager().registerEvents(new InvSpeed(), this);
        Bukkit.getPluginManager().registerEvents(new DisableEvent(), this);
        if (!getPlugin().getConfig().isSet("cStrings.Prefix"))
            getPlugin().getConfig().set("cStrings.Prefix", "");
        Eng.PRE = getPlugin().getConfig().getString("cStrings.Prefix").replaceAll(";", ":");
        if (con.isSet("override.listeners")) {
            if (con.getBoolean("override.listeners.TreeCutDown"))
                Bukkit.getPluginManager().registerEvents(new TreeCutDown(), this);
            if (con.getBoolean("override.listeners.Doors"))
                Bukkit.getPluginManager().registerEvents(new Doors(), this);
            if (con.getBoolean("override.listeners.CreeperRemovePotion"))
                Bukkit.getPluginManager().registerEvents(new CreeperExplode(), this);
            if (con.getBoolean("override.listeners.CreeperActivateCreeper"))
                Bukkit.getPluginManager().registerEvents(new CreeperActivateCreeper(), this);
            if (con.getBoolean("override.listeners.ClickGrowedSeed"))
                Bukkit.getPluginManager().registerEvents(new ClickGrowed(), this);
            if (con.isSet("override.World.defaultWorld") &&
                    !con.getString("override.World.defaultWorld").equals("world"))
                defWorld = Bukkit.getWorld(con.getString("override.World.defaultWorld"));
            if (con.isSet("override.World.spawnWlWorld"))
                sWlWorld = true;
        } else {
            for (String s : listeners)
                con.set("override.listeners." + s, true);
            con.set("override.World.defaultWorld", "world");
            con.set("override.World.spawnWlWorld", false);
            con.set("override.World.spawnWlWorlds", Arrays.asList(new String[]{"world", "FarmCountrySpawn"}));
            System.out.println("\n " + Eng.PRE + "All modules have been enabled, since they was no config.yml override.\n ");
        }
        if (!con.isSet("Settings.SurvivalMode"))
            con.set("Settings.SurvivalMode", false);
        survivalMode = con.getBoolean("Settings.SurvivalMode");
        if (defWorld == null)
            defWorld = Bukkit.getWorld("world");
        getCommand("gm").setExecutor(new Gm());
        getCommand("Maintenance").setExecutor(new Maintenance());
        getCommand("Day").setExecutor(new Day());
        getCommand("fly").setExecutor(new Fly());
        getCommand("lore").setExecutor(new Lore());
        getCommand("health").setExecutor(new Health());
        getCommand("HelpyHelp").setExecutor(new HelpyHelp());
        getCommand("hheal").setExecutor(new Heal());
        getCommand("ClearChat").setExecutor(new ClearChat());
        getCommand("Invsee").setExecutor(new Invsee());
        getCommand("Ench").setExecutor(new Ench());
        getCommand("God").setExecutor(new God());
        getCommand("Speed").setExecutor(new Speed());
        getCommand("ArrowsInBody").setExecutor(new ArrowsInBody());
        getCommand("Hunger").setExecutor(new Hunger());
        getCommand("TpExact").setExecutor(new TpExact());
        getCommand("Spawn").setExecutor(new Spawn());
        getCommand("Broadcast").setExecutor(new Broadcast());
        getCommand("EcClear").setExecutor(new EcClear());
        if (consumable == null)
            Arrays.asList(Material.APPLE, Material.GOLDEN_APPLE, Material.ENCHANTED_GOLDEN_APPLE, Material.MUSHROOM_STEW, Material.BREAD, Material.PORKCHOP, Material.COOKED_PORKCHOP, Material.COD, Material.SALMON, Material.CARROTS,
                    Material.POTATOES, Material.TROPICAL_FISH, Material.PUFFERFISH, Material.COOKED_COD, Material.COOKED_SALMON, Material.COOKIE, Material.MELON_SLICE, Material.DRIED_KELP, Material.BEEF, Material.COOKED_CHICKEN,
                    Material.COOKED_BEEF, Material.CHICKEN, Material.ROTTEN_FLESH, Material.SPIDER_EYE, Material.CARROT, Material.POTATO, Material.BAKED_POTATO, Material.POISONOUS_POTATO, Material.PUMPKIN_PIE, Material.RABBIT,
                    Material.COOKED_RABBIT, Material.RABBIT_STEW, Material.MUTTON, Material.COOKED_MUTTON, Material.BEETROOT, Material.BEETROOT_SOUP, Material.SWEET_BERRIES);
        if (!con.isSet("Settings.GrowedClick.Permission"))
            con.set("Settings.Permission.GrowedClick", false);
        growedPerm = con.getBoolean("Settings.GrowedClick.Permission");
        if (!con.isSet("Settings.grankXp.GrowedClick"))
            con.set("Settings.grankXp.GrowedClick", false);
        grownExp = con.getBoolean("Settings.grankXp.GrowedClick");
        if (!con.isSet("Settings.Permission.DoorClick"))
            con.set("Settings.Permission.DoorClick", false);
        growedPerm = con.getBoolean("Settings.Permission.DoorClick");
        if (!con.isSet("Settings.DisableEvent.KickAll"))
            con.set("Settings.DisableEvent.KickAll", true);
        disKickAll = con.getBoolean("Settings.DisableEvent.KickAll");
        if (!con.isSet("Settings.SilentJoin"))
            con.set("Settings.SilentJoin", false);
        if (!con.isSet("Settings.SilentQuit"))
            con.set("Settings.SilentQuit", false);
        if (!con.isSet("Settings.CustomJoinMessage"))
            con.set("Settings.CustomJoinMessage", true);
        if (!con.isSet("Settings.CustomQuitMessage"))
            con.set("Settings.CustomQuitMessage", true);
        getPlugin().saveConfig();
        Bukkit.getScheduler().runTaskLater(this, () -> {
            if (!con.isSet("preStart.operation.disableWayds"))
                con.set("preStart.operation.disableWayds", false);
            if (con.getBoolean("preStart.operation.disableWayds"))
                Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getServer().getConsoleSender(), "plugman:plugman unload WhatAreYouDoingStepbro");
            getPlugin().saveConfig();
            preStartDone = true;
        }, 45L);
    }

    public synchronized String getVersion() {
        String v = null;
        try {
            Properties p = new Properties();
            InputStream is = getClass().getResourceAsStream("/META-INF/maven/de.tdf/Helpy/pom.properties");
            if (is != null) {
                p.load(is);
                v = p.getProperty("version", "");
            }
        } catch (Exception ignored) {
        }
        if (v == null) {
            Package ap = getClass().getPackage();
            if (ap != null) {
                v = ap.getImplementationVersion();
                if (v == null)
                    v = ap.getSpecificationVersion();
            }
        }
        return v;
    }

    public static Helpy getPlugin() {
        return helpy;
    }
}
