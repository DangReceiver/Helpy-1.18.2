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
import de.tdf.helpy.methods.worldGenerator.UseVoid;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public final class Helpy extends JavaPlugin {
	private static Helpy helpy;

	public static String VERSION = "";

	public static boolean preStartDone = false, stable = true, growedPerm = false;

	public static boolean grownExp = false, kickAll = true, spawmPermission = false;

	public static List<String> listeners = new ArrayList<>(
			Arrays.asList("TreeCutDown", "Doors", "CreeperRemovePotion",
					"CreeperActivateCreeper", "ClickGrowedSeed")),
			broadcasts = new ArrayList<>(Arrays.asList("Example Broadcast", "continue this list!",
					"Keine Doppelpunkte verwenden!", "§aColor §6codes via Paragraphenzeichen"));


	File file;
	YamlConfiguration settings;

	public void onEnable() {
		helpy = this;
		VERSION = this.getVersion();

		ConsoleCommandSender cs = Bukkit.getConsoleSender();
		FileConfiguration c = getConfig();

		Bukkit.getScheduler().runTask(this, () -> {
			settingsProcess();
			for (String s : c.getStringList("Helpy.voidWorlds"))
				if (Bukkit.getWorld(s) == null) {
//				new WorldCreator(s).createWorld();
					UseVoid.createSteams(s);
					cs.sendMessage(Eng.PRE + String.format(Eng.LAODING_WORLD, s));
				} else
					cs.sendMessage(Eng.PRE + String.format(Eng.WORLD_EXISTS, s));

		});

		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new CM(), this);
		pm.registerEvents(new InvSpeed(), this);
		pm.registerEvents(new DisableEvent(), this);

		if (!c.isSet("cStrings.Prefix"))
			c.set("cStrings.Prefix", "§b§oHelpy§8: §7");
		Eng.PRE = c.getString("cStrings.Prefix").replaceAll(";", ":");

		if (c.isSet("override.listeners")) {
			if (c.getBoolean("override.listeners.TreeCutDown"))
				pm.registerEvents(new TreeCutDown(), this);
			if (c.getBoolean("override.listeners.Doors"))
				pm.registerEvents(new Doors(), this);
			if (c.getBoolean("override.listeners.CreeperRemovePotion"))
				pm.registerEvents(new CreeperExplode(), this);
			if (c.getBoolean("override.listeners.CreeperActivateCreeper"))
				pm.registerEvents(new CreeperActivateCreeper(), this);
			if (c.getBoolean("override.listeners.ClickGrowedSeed"))
				pm.registerEvents(new ClickGrowed(), this);
		} else
			for (String s : listeners)
				c.set("override.listeners." + s, true);

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
		getCommand("Broadcast").setExecutor(new Broadcast());
		getCommand("EcClear").setExecutor(new EcClear());
		getCommand("CreateWorld").setExecutor(new CreateWorld());
		getCommand("TpWorld").setExecutor(new TpWorld());

		if (!c.isSet("broadcast.broadcasts") )
			c.set("broadcast.broadcasts", broadcasts);
		broadcasts = c.getStringList("broadcast.broadcasts");
		if (!c.isSet("broadcast.inOrder"))
			c.set("broadcast.inOrder", true);
		if (!c.isSet("broadcast.delayInSeconds"))
			c.set("broadcast.delayInSeconds", 60);
		if (!c.isSet("broadcast.toggle"))
			c.set("broadcast.toggle", true);
		if (!c.isSet("broadcast.excludeConsole"))
			c.set("broadcast.excludeConsole", false);
		if (!c.isSet("broadcast.prefix"))
			c.set("broadcast.prefix", Eng.PRE);
		saveConfig();

//		if (c.getBoolean("broadcast.toggle"))
//			BroadcastLoop.startLoop();

		getCommand("Spawn").setExecutor(new Spawn());

		Bukkit.getScheduler().runTaskLater(this, () -> preStartDone = true, 30L);
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

	public void settingsProcess() {
		ConsoleCommandSender cs = Bukkit.getConsoleSender();
		file = new File("plugins/Helpy/Settings.yml");
		if (!file.exists()) {

			try {
				if (file.createNewFile()) {
					settings = YamlConfiguration.loadConfiguration(file);
					saveSettings(settings, file);

				} else {
					cs.sendMessage("§4The settings file could not be created, stopping the plugin.");
					Bukkit.getPluginManager().disablePlugin(this);
					return;

				}
			} catch (IOException e) {
				cs.sendMessage("§4The settings file could not be created, stopping the plugin.");
				Bukkit.getPluginManager().disablePlugin(this);
				return;
			}
		} else settings = YamlConfiguration.loadConfiguration(file);
	}

	public static void saveSettings(YamlConfiguration settings, File file) {
		try {
			settings.save(file);
		} catch (IOException ex) {
			Bukkit.getConsoleSender().sendMessage(Eng.PRE + "The settings file could not be saved.");
		}
	}

	public static Helpy getHelpy() {
		return helpy;
	}
}
