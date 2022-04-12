package de.tdf.helpy.commands.function;

import de.tdf.helpy.helpy.Helpy;
import de.tdf.helpy.methods.lang.Eng;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gm implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!Helpy.survivalMode) {
            if (sender.hasPermission("helpy.gm.self")) {
                if (args.length == 2) {
                    final Player tar = Bukkit.getPlayer(args[1]);
                    if (tar != null) {
                        final GameMode bg = tar.getGameMode();
                        if (args[0].equals("0") || args[0].equalsIgnoreCase("SURVIVAL") || args[0].equalsIgnoreCase("s")) {
                            tar.setGameMode(GameMode.SURVIVAL);
                        }
                        else if (args[0].equals("1") || args[0].equalsIgnoreCase("CREATIVE") || args[0].equalsIgnoreCase("c")) {
                            tar.setGameMode(GameMode.CREATIVE);
                        }
                        else if (args[0].equals("3") || args[0].equalsIgnoreCase("SPECTATOR") || args[0].equalsIgnoreCase("sp")) {
                            tar.setGameMode(GameMode.SPECTATOR);
                        }
                        else if (args[0].equals("2") || args[0].equalsIgnoreCase("ADVENTURE") || args[0].equalsIgnoreCase("a")) {
                            tar.setGameMode(GameMode.ADVENTURE);
                        }
                        else {
                            if (!args[0].equalsIgnoreCase("h") && !args[0].equalsIgnoreCase("help")) {
                                Eng.tryHelp(sender, cmd, true);
                                return true;
                            }
                            Eng.argsUsage(sender, "/gm [<0; 1; 2; 3; a; c; s; sp; Adventure; Creative; Survival; Spectator>] [<player name>]", true);
                        }
                        Eng.atChange(sender, "chat", "§dGameMode", true, bg.toString().toLowerCase(), tar.getGameMode().toString().toLowerCase(), false, true);
                        Eng.atChange((CommandSender)tar, "action", "§dGameMode", true, bg.toString().toLowerCase(), tar.getGameMode().toString().toLowerCase(), false, false);
                    }
                    else {
                        sender.sendMessage(Eng.CMD_TARGET_NOT_EXI_POSSIBLY);
                    }
                    return true;
                }
                if (sender instanceof Player) {
                    final Player p = (Player)sender;
                    final GameMode bg = p.getGameMode();
                    if (args.length == 0) {
                        if (!p.isSneaking() && p.isFlying()) {
                            if (p.getGameMode() == GameMode.ADVENTURE) {
                                p.setGameMode(GameMode.SURVIVAL);
                            }
                            else if (p.getGameMode() == GameMode.SURVIVAL) {
                                p.setGameMode(GameMode.CREATIVE);
                            }
                            else if (p.getGameMode() == GameMode.CREATIVE) {
                                p.setGameMode(GameMode.SPECTATOR);
                            }
                            else if (p.getGameMode() == GameMode.SPECTATOR) {
                                p.setGameMode(GameMode.CREATIVE);
                            }
                        }
                        else if (p.isSneaking() && p.isFlying()) {
                            if (p.getGameMode() == GameMode.SURVIVAL) {
                                p.setGameMode(GameMode.CREATIVE);
                            }
                            else if (p.getGameMode() == GameMode.CREATIVE) {
                                p.setGameMode(GameMode.SPECTATOR);
                            }
                            else if (p.getGameMode() == GameMode.SPECTATOR) {
                                p.setGameMode(GameMode.CREATIVE);
                            }
                            else if (p.getGameMode() == GameMode.SPECTATOR) {
                                p.setGameMode(GameMode.SURVIVAL);
                            }
                        }
                        else if (p.getGameMode() == GameMode.SURVIVAL) {
                            p.setGameMode(GameMode.CREATIVE);
                        }
                        else if (p.getGameMode() == GameMode.CREATIVE) {
                            p.setGameMode(GameMode.SURVIVAL);
                        }
                        else if (p.getGameMode() == GameMode.SPECTATOR) {
                            p.setGameMode(GameMode.SURVIVAL);
                        }
                        else if (p.getGameMode() == GameMode.ADVENTURE) {
                            p.setGameMode(GameMode.SURVIVAL);
                        }
                        Eng.atChange((CommandSender)p, "action", "§dGameMode", true, bg.toString().toLowerCase(), p.getGameMode().toString().toLowerCase(), false, false);
                    }
                    else if (args.length == 1) {
                        if (args[0].equals("0") || args[0].equalsIgnoreCase("SURVIVAL") || args[0].equalsIgnoreCase("s")) {
                            p.setGameMode(GameMode.SURVIVAL);
                        }
                        else if (args[0].equals("1") || args[0].equalsIgnoreCase("CREATIVE") || args[0].equalsIgnoreCase("c")) {
                            p.setGameMode(GameMode.CREATIVE);
                        }
                        else if (args[0].equals("3") || args[0].equalsIgnoreCase("SPECTATOR") || args[0].equalsIgnoreCase("sp")) {
                            p.setGameMode(GameMode.SPECTATOR);
                        }
                        else if (args[0].equals("2") || args[0].equalsIgnoreCase("ADVENTURE") || args[0].equalsIgnoreCase("a")) {
                            p.setGameMode(GameMode.ADVENTURE);
                        }
                        else {
                            if (!args[0].equalsIgnoreCase("h") && !args[0].equalsIgnoreCase("help")) {
                                Eng.tryHelp(sender, cmd, true);
                                return true;
                            }
                            Eng.argsUsage(sender, "/gm [<0; 1; 2; 3; a; c; s; sp; Adventure; Creative; Survival; Spectator>] [<player name>]", true);
                        }
                        Eng.atChange((CommandSender)p, "action", "§dGameMode", true, bg.toString().toLowerCase(), p.getGameMode().toString().toLowerCase(), false, false);
                    }
                    else {
                        p.sendMessage(Eng.CMD_ARGS_LENGHT);
                    }
                }
                else {
                    sender.sendMessage(Eng.CMD_NOT_PLAYER_GM);
                }
            }
            else {
                Eng.permissionShow(sender, "helpy.gm.self");
            }
        }
        else {
            sender.sendMessage(Eng.MECHANIC_SURVIVAL_MODE_TRUE);
        }
        return false;
    }
}
