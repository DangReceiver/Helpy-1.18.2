package de.tdf.helpy.methods;

import java.io.File;
import java.util.Date;

import org.bukkit.entity.Monster;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

import java.text.SimpleDateFormat;

import org.bukkit.plugin.Plugin;
import org.bukkit.GameMode;
import de.tdf.helpy.helpy.Helpy;
import org.bukkit.Location;
import org.bukkit.Sound;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.ChatMessageType;

import java.util.Random;

import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Other {

	public double roundD(double d) {
		d = Math.round(d * 1000);
		return d / 1000;
	}

	public static String transHexColor(final String startTag, final String endTag, final String msg) {
		final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
		final Matcher ma = hexPattern.matcher(msg);
		final StringBuffer buffer = new StringBuffer(msg.length() + 32);
		while (ma.find()) {
			final String group = ma.group(1);
			ma.appendReplacement(buffer, "§x§" + group.charAt(0) + '§' + group.charAt(1) + '§' + group.charAt(2) + '§' + group.charAt(3) + '§' + group.charAt(4) + '§' + group.charAt(5));
		}
		return ma.appendTail(buffer).toString();
	}

	public static OfflinePlayer testForValidPlayer(final Player p, final boolean so, final String s, final boolean onlineNeeded) {
		final Player t = Bukkit.getPlayer(s);
		if (t != null) {
			return (OfflinePlayer) t;
		}
		p.sendMessage(Eng.CMD_NOT_PLAYER);
		if (so) {
			System.out.println("The Player " + s + " is not valid");
		}
		final OfflinePlayer t2 = Bukkit.getOfflinePlayer(s);
		if (t == null) {
			p.sendMessage(Eng.CMD_NOT_PLAYER + " §8(§9Offline§8)");
			if (so) {
				System.out.println("The OfflinePlayer " + s + " is not valid");
			}
			return null;
		}
		return t2;
	}

	public static void randTitle(final int ir1, final Player p, final int mulTimes) {
		if (ir1 <= 1 * mulTimes) {
			p.sendTitle("§1\u00e2\u0153º§3\u00e2\u0153¥§2\u00e2\u2122¼§5\u00e2\u017e¡§\u00e2\u0153»", "§6\u00e2\u0161?§7\u00e2\u2122\u201d§9\u00e2\u02dc£§a\u00e2\u0161\u017d§f\u00e2\u2122¯", 2, 10, 6);
		} else if (ir1 <= 3 * mulTimes) {
			p.sendTitle("§c\u00e2\u2122\u017e§b\u00e2\u0152\u02dc§9\u00cf\u0178§3\u00e2\u0153\u017e§\u00e2\u0153\u2030", "§a\u00e2\u02dc¹§e\u00e2\u2014\u02c6§d\u00e2\u20ac¡§4\u00e2\u2014\u2022§5\u00e2\u0153\u201c", 2, 10, 6);
		} else if (ir1 <= 5 * mulTimes) {
			p.sendTitle("§f\u00e2\u0160\u2014§4\u00ce\u201d§a\u00e2\u2026\u203a§2\u00e2?\u2019§c\u00c3·", "§2\u00e2\u2014\u2021§\u00e2?\u017e§1\u00c9¥§4\u00e2\u201e\u2018§6\u00e2\u2030¡", 2, 10, 6);
		} else if (ir1 <= 7 * mulTimes) {
			p.sendTitle("§6\u00ef¹\u2030§5§0\u00e2\u2020\u201d\u00e0¸´§4\u00c3¯§e\u00e2\u201d\u2021", "§5\u00e2\u0153?§4\u00e2\u201d?§c\u00e2\u2030¢§6\u00e2\u2030\u0161§e\u00e2\u2014?", 2, 10, 6);
		} else if (ir1 <= 9 * mulTimes) {
			p.sendTitle("§a\u00e2\u2014¼§2\u00e2\u2122?§c\u00e2?\u2026§b\u00e2\u2020ª§3\u00e2\u2020¾", "§6\u00e2\u2021?§0\u00e2\u2021?§2\u00e2\u2021?§1\u00e2\u0152§§d\u00e2?\u2030", 2, 10, 6);
		} else if (ir1 <= 11 * mulTimes) {
			p.sendTitle("§6\u00e2\u0161?§7\u00e2\u2122\u201d§9\u00e2\u02dc£§a\u00e2\u0161\u017d§f\u00e2\u2122¯", "§1\u00e2\u0153º§3\u00e2\u0153¥§2\u00e2\u2122¼§5\u00e2\u017e¡§\u00e2\u0153»", 2, 10, 6);
		} else if (ir1 <= 13 * mulTimes) {
			p.sendTitle("§a\u00e2\u02dc¹§e\u00e2\u2014\u02c6§d\u00e2\u20ac¡§4\u00e2\u2014\u2022§5\u00e2\u0153\u201c", "§c\u00e2\u2122\u017e§b\u00e2\u0152\u02dc§9\u00cf\u0178§3\u00e2\u0153\u017e§\u00e2\u0153\u2030", 2, 10, 6);
		} else if (ir1 <= 15 * mulTimes) {
			p.sendTitle("§2\u00e2\u2014\u2021§\u00e2?\u017e§1\u00c9¥§4\u00e2\u201e\u2018§6\u00e2\u2030¡", "§f\u00e2\u0160\u2014§4\u00ce\u201d§a\u00e2\u2026\u203a§2\u00e2?\u2019§c\u00c3·", 2, 10, 6);
		} else if (ir1 <= 17 * mulTimes) {
			p.sendTitle("§8\u00e1\u0192»§\u00e2\u2022¨§5{§9\u00e2\u2122±§5\u00e2\u201d¨", "§3\u00e2\u2022±§4\u00e2\u2013\u2019§5\u00e2\u2022°§6\u00e2\u201d´§7\u00e2\u2013\u2018", 2, 10, 6);
		} else if (ir1 <= 19 * mulTimes) {
			p.sendTitle("§5\u00e2\u0153?§4\u00e2\u201d?§c\u00e2\u2030¢§6\u00e2\u2030\u0161§e\u00e2\u2014?", "§6\u00ef¹\u2030§5§0\u00e2\u2020\u201d\u00e0¸´§4\u00c3¯§e\u00e2\u201d\u2021", 2, 10, 6);
		} else if (ir1 <= 21 * mulTimes) {
			p.sendTitle("§6\u00e2\u2021?§0\u00e2\u2021?§2\u00e2\u2021?§1\u00e2\u0152§§d\u00e2?\u2030", "§a\u00e2\u2014¼§2\u00e2\u2122?§c\u00e2?\u2026§b\u00e2\u2020ª§3\u00e2\u2020¾", 2, 10, 6);
		} else if (ir1 > 22 * mulTimes) {
			p.sendTitle("§0\u00e2\u017e¸§a\u00e2\u02dc®§3\u00e2\u02dc»§c¶§b\u00e2\u017e«", "§4\u00e2\u2026\u201c§c\u00e2?\u017e§7\u00e2\u2013\u2018§9\u00e2\u2013§§4\u00e2\u2014?", 2, 10, 6);
		}
	}

	public static float handleDotCord() {
		final Random rand = new Random();
		final int up = 100;
		final float o1 = Float.parseFloat(rand.nextInt(up) + "") / 100.0f;
		return o1;
	}

	public static void actionBar(final Player p, final String type, final String msg, final float vol, final float pit) {
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Eng.PRE + msg));
		if (type.equalsIgnoreCase("neutral")) {
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, vol, pit);
		} else if (type.equalsIgnoreCase("succes")) {
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, vol, pit);
		} else if (!type.equalsIgnoreCase("none")) {
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, vol, pit);
		}
	}

	public static void reTp(final Player p, final Location loc, final float delay, final Boolean forceSurvival, final boolean title) {
		Bukkit.getScheduler().runTaskLater(Helpy.getPlugin(), () -> {
			p.teleport(loc);
			if (title) {
				p.sendTitle(Eng.PRE, "§4§oTrapped", 1, 5, 2);
			}
			if (forceSurvival) {
				p.setGameMode(GameMode.SURVIVAL);
			}
		}, (long) delay);
	}

	public static String CurrentTime() {
		final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(System.currentTimeMillis());
	}

	public static String formTimeSlim(final long l) {
		final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(l);
	}

	public static boolean createFolder(String path) {
		if (path == null)
			path = "plugins/Helpy/players";
		File f1 = new File(path);
		if (f1.exists()) {
			System.out.println("The folder already exists.");
			return false;
		}
		if (!f1.mkdir()) {
			System.out.println("The folder could not be created.");
			return false;
		}
		return true;
	}

	public static boolean isMonster(final Entity e) {
		return (e instanceof Creature || e.getType() == EntityType.SLIME) && (e instanceof Monster || e.getType() == EntityType.SLIME);
	}

	public static String formTime(final long l) {
		final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd.MM.yyyy");
		return sdf.format(l);
	}

	public static String mcToTime(final long time) {
		final long gameTime = time;
		long hours = gameTime / 1000L + 6L;
		final long minutes = gameTime % 1000L * 60L / 1000L;
		String ampm = "AM";
		String mm = "0" + minutes;
		if (hours >= 12L) {
			hours -= 12L;
			ampm = "PM";
		}
		if (hours >= 12L) {
			hours -= 12L;
			ampm = "AM";
		}
		if (hours == 0L) {
			hours = 12L;
		}
		mm = mm.substring(mm.length(), mm.length());
		return hours + ":" + mm + 2 + " " + ampm;
	}

	public static String toTime(final long time) {
		final Long ct = time;
		final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		final Date date = new Date(ct);
		final String out = sdf.format(date);
		return out;
	}
}
