package de.tdf.helpy.helpy;

import de.tdf.helpy.commands.ffa.HelpyHelp;
import de.tdf.helpy.commands.ffa.Hunger;
import de.tdf.helpy.commands.ffa.Spawn;
import de.tdf.helpy.commands.function.*;
import de.tdf.helpy.commands.utils.*;
import de.tdf.helpy.listener.Entities.NonPlayer.CreeperActivateCreeper;
import de.tdf.helpy.listener.Entities.NonPlayer.CreeperExplode;
import de.tdf.helpy.listener.Entities.Player.*;
import de.tdf.helpy.methods.BroadcastLoop;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public final class Helpy extends JavaPlugin {
	private static Helpy helpy;

	public static String VERSION = "";

	public static List<Material> consumable = null;

	public static boolean preStartDone = false, stable = true, growedPerm = false;

	public static boolean grownExp = false, kickAll = true, spawmPermission = false;

	public static List<String> listeners = new ArrayList<>(
			Arrays.asList("TreeCutDown", "Doors", "CreeperRemovePotion",
					"CreeperActivateCreeper", "ClickGrowedSeed")),
			broadcasts = new ArrayList<>(Arrays.asList("Example Broadcast", "Another Example broadcast", "continue this list!"));


	public void onEnable() {
		helpy = this;
		VERSION = this.getVersion();
		FileConfiguration con = getConfig();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new CM(), this);
		pm.registerEvents(new InvSpeed(), this);
		pm.registerEvents(new DisableEvent(), this);
		if (!getPlugin().getConfig().isSet("cStrings.Prefix"))
			getPlugin().getConfig().set("cStrings.Prefix", "§b§oHelpy§8: §7");
		Eng.PRE = getPlugin().getConfig().getString("cStrings.Prefix").replaceAll(";", ":");
		if (con.isSet("override.listeners")) {
			if (con.getBoolean("override.listeners.TreeCutDown"))
				pm.registerEvents(new TreeCutDown(), this);
			if (con.getBoolean("override.listeners.Doors"))
				pm.registerEvents(new Doors(), this);
			if (con.getBoolean("override.listeners.CreeperRemovePotion"))
				pm.registerEvents(new CreeperExplode(), this);
			if (con.getBoolean("override.listeners.CreeperActivateCreeper"))
				pm.registerEvents(new CreeperActivateCreeper(), this);
			if (con.getBoolean("override.listeners.ClickGrowedSeed"))
				pm.registerEvents(new ClickGrowed(), this);
		} else
			for (String s : listeners)
				con.set("override.listeners." + s, true);
		getCommand("gm").setExecutor(new Gm());
		getCommand("Maintenance").setExecutor(new Maintenance());
		getCommand("Day").setExecutor(new Day());
		getCommand("fly").setExecutor(new Fly());
		getCommand("lore").setExecutor(new Lore());
		getCommand("health").setExecutor(new Health());
		getCommand("HelpyHelp").setExecutor(new HelpyHelp());
		getCommand("heal").setExecutor(new Heal());
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
			Arrays.asList(Material.APPLE, Material.GOLDEN_APPLE, Material.ENCHANTED_GOLDEN_APPLE, Material.MUSHROOM_STEW,
					Material.BREAD, Material.PORKCHOP, Material.COOKED_PORKCHOP, Material.COD, Material.SALMON, Material.CARROTS,
					Material.POTATOES, Material.TROPICAL_FISH, Material.PUFFERFISH, Material.COOKED_COD, Material.COOKED_SALMON,
					Material.COOKIE, Material.MELON_SLICE, Material.DRIED_KELP, Material.BEEF, Material.COOKED_CHICKEN,
					Material.COOKED_BEEF, Material.CHICKEN, Material.ROTTEN_FLESH, Material.SPIDER_EYE, Material.CARROT,
					Material.POTATO, Material.BAKED_POTATO, Material.POISONOUS_POTATO, Material.PUMPKIN_PIE, Material.RABBIT,
					Material.COOKED_RABBIT, Material.RABBIT_STEW, Material.MUTTON, Material.COOKED_MUTTON, Material.BEETROOT,
					Material.BEETROOT_SOUP, Material.SWEET_BERRIES);
		if (!con.isSet("Settings.GrowedClick.Permission"))
			con.set("Settings.Permission.GrowedClick", false);
		growedPerm = con.getBoolean("Settings.GrowedClick.Permission");
		if (!con.isSet("Settings.grankXp.GrowedClick"))
			con.set("Settings.grankXp.GrowedClick", false);
		grownExp = con.getBoolean("Settings.grankXp.GrowedClick");
		if (!con.isSet("Settings.Permission.DoubleDoors"))
			con.set("Settings.Permission.DoubleDoors", false);
		growedPerm = con.getBoolean("Settings.Permission.DoubleDoors");
		if (!con.isSet("Settings.DisableEvent.KickAll"))
			con.set("Settings.DisableEvent.KickAll", true);
		kickAll = con.getBoolean("Settings.DisableEvent.KickAll");
		if (!con.isSet("Settings.SilentJoin"))
			con.set("Settings.SilentJoin", false);
		if (!con.isSet("Settings.SilentQuit"))
			con.set("Settings.SilentQuit", false);
		if (!con.isSet("Settings.CustomJoinMessage"))
			con.set("Settings.CustomJoinMessage", false);
		if (!con.isSet("Settings.CustomQuitMessage"))
			con.set("Settings.CustomQuitMessage", false);
		if (!con.isSet("Settings.Spawn.Permission"))
			con.set("Settings.Spawn.Permission", false);
		if (!con.isSet("Settings.Spawn.Title"))
			con.set("Settings.Spawn.Title", true);
		if (!con.isSet("Settings.Spawn.Location")) {
			World w = Bukkit.getWorld("world");
			if (w == null) w = Bukkit.getWorld("spawn");
			con.set("Settings.Spawn.Permission", new Location(w, 0, 64.01, 0));
			if (w == null)
				System.out.println(Eng.PRE + "§cPlease check the spawn's location, the world may not have been found!");
		}

		if (!con.isSet("broadcast.broadcasts") || con.getStringList("broadcast.broadcasts").isEmpty())
			con.set("broadcast.broadcasts", broadcasts);
		broadcasts = con.getStringList("broadcast.broadcasts");
		if (!con.isSet("broadcast.inOrder"))
			con.set("broadcast.inOrder", true);
		if (!con.isSet("broadcast.delayInSeconds"))
			con.set("broadcast.delayInSeconds", 40);
		if (!con.isSet("Broadcast.toggle"))
			con.set("broadcast.toggle", true);
		if (con.getBoolean("broadcast.toggle"))
			BroadcastLoop.startLoop();
		getPlugin().saveConfig();
		Bukkit.getScheduler().runTaskLater(this, () -> preStartDone = true, 50L);
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
