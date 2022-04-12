package de.tdf.helpy.methods;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;
import java.io.IOException;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.OfflinePlayer;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class pConfig {

	YamlConfiguration config;
	File file;

	public pConfig(final OfflinePlayer p, final String str) {
		final String uuid = p.getUniqueId().toString();
		this.file = new File("plugins/" + str + "/players/" + uuid + ".yml");
		this.config = YamlConfiguration.loadConfiguration(this.file);
	}

	public YamlConfiguration getConfig() {
		return this.config;
	}

	public File getFile() {
		return this.file;
	}

	public static pConfig loadConfig(final OfflinePlayer p, final String str) {
		if (!hasConfig(p, str)) {
			createConfig(p, str);
		}
		return new pConfig(p, str);
	}

	public static boolean hasConfig(final OfflinePlayer p, final String str) {
		final String uuid = p.getUniqueId().toString();
		return new File("plugins/" + str + "/players/" + uuid + ".yml").exists();
	}

	public static pConfig createConfig(final OfflinePlayer p, final String str) {
		final String uuid = p.getUniqueId().toString();
		final File file = new File("plugins/" + str + "/players/" + uuid + ".yml");
		if (!file.exists()) {
			try {
				if (!file.exists()) {
					System.out.println("[Helpy] There was an error creating the pConfig for the player " + p.getName() + " \n double check their config and settings to ensure this is not an error. [0]");
					final File folder = new File("plugins/" + str + "/players");
					folder.createNewFile();
					System.out.println("[Helpy] In order to fix that, the folder ''players'' was automatically created.");
				}
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("[Helpy] There was an error creating the pConfig for the player " + p.getName() + " \n double check their config and settings to ensure this is not an error. [1]");
			}
		}
		return new pConfig(p, str);
	}

	public ConfigurationSection getConfigurationSection(final String string) {
		return this.config.getConfigurationSection(string);
	}

	public void setTrue(final String string) {
		this.config.set(string, (Object) true);
	}

	public void setFalse(final String string) {
		this.config.set(string, (Object) false);
	}

	public void setNull(final String string) {
		this.config.set(string, (Object) null);
	}

	public boolean isSet(final String string) {
		return this.config.isSet(string);
	}

	public boolean getBoolean(final String string) {
		return this.config.getBoolean(string);
	}

	public boolean hasWorldID() {
		return this.config.isSet("pWorld.OwningID");
	}

	public int getWorldID() {
		return this.config.getInt("pWorld.OwningID");
	}

	public void setWorldID(final int newWID) {
		this.config.set("pWorld.OwningID", (Object) newWID);
	}

	public boolean createdBefore() {
		return this.config.getBoolean("pWorld.createdBefore");
	}

	public void set(final String string, final Object arg1) {
		this.config.set(string, arg1);
	}

	public String getString(final String string) {
		return this.config.getString(string);
	}

	public List<String> getStringList(final String string) {
		return (List<String>) this.config.getStringList(string);
	}

	public double getTokens() {
		return this.config.isSet("Player.Stats.Tokens") ? this.config.getDouble("Player.Stats.Tokens") : 0.0;
	}

	public Location getLocation(final String loc) {
		return this.config.isSet(loc) ? this.config.getLocation(loc) : null;
	}

	public float getFloat(final String string) {
		return (float) this.config.getDouble(string);
	}

	public int getInt(final String string) {
		return this.config.getInt(string);
	}

	public long getLong(final String string) {
		return this.config.getLong(string);
	}

	public Set<String> getKeys(final boolean deep) {
		return (Set<String>) this.config.getKeys(deep);
	}

	public double getDouble(final String string) {
		return this.config.getDouble(string);
	}

	public void setTokens(double i) {
		i = Math.round(i * 1000);
		this.config.set("Player.Stats.Tokens", i / 1000);
		this.savePCon();
	}

	public void savePCon() {
		try {
			this.config.save(this.file);
		} catch (IOException e) {
			System.out.println("[Helpy] There was an error saving the pConfig for the player " + this.config.getName() + " \n check their config and settings to ensure this is not an error. [2]");
		}
	}

	public void savePConErrorFree() {
		try {
			this.config.save(this.file);
		} catch (IOException ex) {
		}
	}
}
