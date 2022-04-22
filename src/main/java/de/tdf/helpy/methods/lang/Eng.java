package de.tdf.helpy.methods.lang;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.Other;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Eng implements Listener {

	public static String PRE = Helpy.getPlugin().getConfig().getString("cStrings.Prefix");

	public static String SPACE = " §0 ", UP_SPACE = " §0 \n §0 \n §0 ", LINE_SPACE = "§8§m§l------------------------",
			CHAT_CLEARED = PRE + "The §6chat §7was §3cleared§8.",
			MECHANIC_SURVIVAL_MODE_TRUE = PRE + "Since the §aSurvival §2Mode §7is §aactivated §8(§6server sided§8) §7this §9command §cisn§8'§ct §eavailabe§8.",
			PRE_JOIN_STARTING = PRE + "The §6server §7is still §3starting §8§l| |\n§7Please be §opatient §7and wait a §efew more seconds§8.",
			ANY_INPUT_DELAY_EXPIRED = PRE + "Your §5action §7was §ccancelled§8, §7since it §ccouldn§8'§Ct §7be §2validated§8.",
			LIS_GROWED_PERM = PRE + "You §cneed §7the §6Premium §5rank§8, §7to use this §1feature§8.",
			CMD_NOT_PLAYER_GM = PRE + "I §cdon§8'§ct §7know§8, §7how to §bchange §7a §6non§8-§eplayer§8'§es §3gamemode§8.",
			CMD_NOT_PLAYER = PRE + "This §9command §7is only §2available §7for §eplayers§8.",
			CMD_ARGS_LENGHT = PRE + "You§8'§7ve used to §6many §8/ §cless §darguments§8.",
			CMD_ARG_INVALID = PRE + "§cInvalid §dargument§8.",
			CMD_INVALID_WORLD_NAME = PRE + "The chosen world name is invalid",
			CMD_TARGET_NOT_EXI_SAFE = PRE + "The given §6player §cisn§8'§ct §aonline§8.",
			CMD_TARGET_NOT_EXI_POSSIBLY = Eng.CMD_TARGET_NOT_EXI_SAFE.replace(".", "§8, §7") + "or §cdoesn§8'§ct §7exist§8.",
			CMD_EVERYONE_HEALED = PRE + "§9Everyone §7was §dhealed§8.",
			CMD_EZ = PRE + "§7Just use §6this§8, §cwithout §5arguments§8.",
			CMD_ENCH_CAUSE_ZERO = PRE + "Since you chose §8'§b0§8', §7the §9enchantments §7will be §ehidden§8.",
			CMD_ENCHANT_INVALID = PRE + "This §8enchantment §ccouldn§8'§ct §7be found§8.",
			CMD_SPAWN_UNWHITELISTED_WORLD = PRE + "The §2world §fWhitelist §7is §6toggled§8. §7The §2world §7you are §ecurrently §7in §7does §c§onot §callow §7this §9command§8.",
			CMD_LORE_SUCCESSFULLY_UPDATED = PRE + "The §9item§8'§9s §1lore §7was §osuccesfully §aupdated§8.",
			CMD_ECCLEAR_CONFIRM_SELF = PRE + "Are you §6sure §7you want to §4clear §cyour §9Ender Chest§8?",
			CMD_ECCLEAR_CONFIRM_OTHER = PRE + "Are you §6sure §6you want to §4clear §7the §9Ender Chest §7of ",
			CMD_ECCLEAR_TYPE_TO_CONFIRM = PRE + "Type §8''§6/§eEcClear §cconfirm§8'' §7to §aconfirm §7this §5action§8.",
			CMD_ACTION_CONFIRMED = PRE + "You §5action §7was §aconfirmed§8.",
			CMD_ECCLEAR_ANNOUNCE_SUCCES_TO_TARGET = PRE + "Your §9Ender Chest $7was §4cleared§8.",
			CMD_MAINTENANCE_NOW = PRE + "§7The §9Maintenance§8-§bmode §7is now§8: §e",
			CMD_MAINTENANCE_TITLE = PRE + "°°§7The §9Maintenance§8-§bmode §7was §6toggled§8!",
			CMD_MAINTENANCE_TARGET_ALREADY_LISTED = PRE + "The given §eplayer is §aalready §7at the §bTrusted Player §7list§8.",
			CMD_MAINTENANCE_TARGET_ALREADY_UNLISTED = PRE + "The given §eplayer is §cnot §7on the §bTrusted Player §7list§8.",
			CMD_MAINTENANCE_LIST_HEADER = PRE + "§7A list of §btrusted players §7will follow§8; ",
			CMD_MAINTENANCE_LIST_FOOTER = PRE + "§8§m§l------------------------";

	public static void permissionShow(final CommandSender send, final String perm) {
		send.sendMessage(PRE
				+ "You are §clacking§7 the §3permission §b" + perm + "§8, §7to execute this §9command§8.");
	}

	public static void argsUsage(final CommandSender send, final String usage, final boolean icolor) {
		String CAU = PRE
				+ "Please use§8: §e" + usage + "§8.";
		if (icolor) {
			CAU = CAU.replace("<", "§e<§6").replace(">", "§e>").replace("[", "§8[").replace("]", "§8]").replace("/", "§7/§6").replace(";", "§e;§6");
		}
		send.sendMessage(CAU);
		if (CAU.contains("[") || CAU.contains("]")) {
			send.sendMessage(PRE + "§8''§3§o['' §7§oand §8''§3§o]§8'' §7§oindicates§9§o, §7§othat an §d§oargument §7§ois §e§oadditional§8§o.");
		}
	}

	public static void entryType(final CommandSender send, final String varNum, final String type) {
		send.sendMessage(PRE
				+ "§dArgument §3#§9" + varNum + " §7should be an §d" + type + " §5value§8.");
	}

	public static void numbHighLow(final CommandSender send, final String varNum, final String type, final String max, final boolean notHigh) {
		String t = "§6higher";
		if (notHigh) {
			t = "§elower";
		}
		send.sendMessage(PRE
				+ "§dArgument §3#§9" + varNum + " §7should be an §d" + type + " §5value§8, §7and §cnot " + t + " §7than §e" + max);
	}

	public static void newLocation(final CommandSender sen, final Location loc) {
		sen.sendMessage(PRE
				+ "The §9location §7was set to §eX§8: §6" + loc.getBlockX() + " §eY§8: §6" + loc.getY() + " §eZ§8: §6" + loc.getZ() + " §8[§e§oWorld§8§o: §6§o" + loc.getWorld().getName() + "§8].");
	}

	public static void booleanValueUpdated(final CommandSender sen, final String insert, final boolean result) {
		sen.sendMessage(PRE
				+ "The §dvalue §7for §9" + insert + " §7was §aupdated §7to§8: §e" + result);
	}

	public static void atChange(final CommandSender send, final String sType, final String att, final boolean was, String bef, String af, final boolean lcru, final boolean hasTarget) {
		String w = " §7were ";
		String y = "§7Your ";
		if (was) {
			w = " §7was ";
		}
		if (hasTarget) {
			y = "§7The §etarget§8'§es §7";
		}
		if (lcru) {
			bef = bef.toLowerCase().replace("_", " ");
			af = af.toLowerCase().replace("_", " ");
		}
		if (send instanceof Player) {
			if (sType.equalsIgnoreCase("chat") || sType.equalsIgnoreCase("msg") || sType.equalsIgnoreCase("normal")) {
				send.sendMessage(PRE
						+ y + att + w + "changed from §3" + bef + " §7to §b" + af + "§8.");
			} else if (sType.equalsIgnoreCase("action") || sType.equalsIgnoreCase("actionbar")) {
				Other.actionBar((Player) send, "neutral", y + att + w + "changed from §3" + bef + " §7to §b" + af + "§8.", 1.0f, 1.0f);
			} else {
				send.sendMessage(PRE
						+ y + att + w + "changed from §3" + bef + " §7to §b" + af + "§8.");
			}
		} else {
			send.sendMessage(PRE + y + att + w + "changed from §3" + bef + " §7to §b" + af + "§8.");
		}
	}

	public static void tryHelp(final CommandSender send, final Command cmd, final boolean re) {
		send.sendMessage(PRE + "Try§8: §e/" + cmd.getName() + " §6help§8.");
		if (re)
			return;
	}
}
