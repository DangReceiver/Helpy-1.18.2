package de.tdf.helpy.listener.Entities.Player;

import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.block.data.Directional;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Sound;
import org.bukkit.Material;
import de.tdf.helpy.helpy.Helpy;
import org.bukkit.GameMode;
import org.bukkit.block.data.type.Door;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class Doors implements Listener
{
    @EventHandler
    public void onDoorInteract(final PlayerInteractEvent e) {
        if (e.isCancelled()) {
            return;
        }
        final Player p = e.getPlayer();
        if (!e.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK) && !e.getAction().equals((Object)Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        final Block b = e.getClickedBlock();
        if (b.equals(null) || !(b.getBlockData() instanceof Door)) {
            return;
        }
        if (p.getGameMode() != GameMode.SPECTATOR) {
            final FileConfiguration con = Helpy.getPlugin().getConfig();
            if (con.getBoolean("Settings.Permission.DoorClick") && !p.hasPermission("nte.legende")) {
                return;
            }
            if (e.getAction().equals((Object)Action.LEFT_CLICK_BLOCK)) {
                if (b.getType() == Material.IRON_DOOR) {
                    p.playSound(b.getLocation(), Sound.BLOCK_CHAIN_FALL, 2.25f, 0.0f);
                }
                else {
                    p.playSound(b.getLocation(), Sound.BLOCK_LADDER_PLACE, 1.85f, 0.0f);
                }
                return;
            }
            if (b.getType() == Material.IRON_DOOR) {
                return;
            }
            if (e.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) {
                this.matchDoubleDoor(b);
            }
        }
    }
    
    private void matchDoubleDoor(final Block block) {
        Block blockBottom = block.getRelative(BlockFace.DOWN);
        Block blockTop = block.getRelative(BlockFace.UP);
        Block blockNearBottom = blockBottom.getRelative(BlockFace.NORTH);
        Block blockNearTop = blockTop.getRelative(BlockFace.NORTH);
        if (blockBottom.getBlockData() instanceof Door) {
            blockTop = block;
        }
        else {
            blockBottom = block;
        }
        final BlockFace face = ((Directional)blockBottom.getBlockData()).getFacing();
        BlockFace direction = null;
        BlockFace direction2 = null;
        switch (face) {
            case NORTH: {
                direction = BlockFace.EAST;
                direction2 = BlockFace.WEST;
                break;
            }
            case EAST: {
                direction = BlockFace.SOUTH;
                direction2 = BlockFace.NORTH;
                break;
            }
            case SOUTH: {
                direction = BlockFace.WEST;
                direction2 = BlockFace.EAST;
                break;
            }
            case WEST: {
                direction = BlockFace.NORTH;
                direction2 = BlockFace.SOUTH;
                break;
            }
            default: {
                Bukkit.getLogger().log(Level.WARNING, "[Helpy] Construction was reset.");
                return;
            }
        }
        if (((Door)blockTop.getBlockData()).getHinge().equals((Object)Door.Hinge.RIGHT)) {
            blockNearBottom = blockBottom.getRelative(direction2);
            blockNearTop = blockTop.getRelative(direction2);
        }
        else {
            blockNearBottom = blockBottom.getRelative(direction);
            blockNearTop = blockTop.getRelative(direction);
        }
        if (blockNearBottom.getType().equals((Object)block.getType()) && blockNearTop.getType().equals((Object)block.getType())) {
            if (((Door)blockTop.getBlockData()).getHinge() == ((Door)blockNearTop.getBlockData()).getHinge()) {
                return;
            }
            if (((Directional)blockBottom.getBlockData()).getFacing() != ((Directional)blockNearBottom.getBlockData()).getFacing()) {
                return;
            }
            this.changeDoor(blockNearBottom, blockBottom);
        }
    }
    
    private void changeDoor(final Block blockChange, final Block reference) {
        final BlockState changeState = blockChange.getState();
        final Openable doorOpen = (Openable)changeState.getBlockData();
        final Openable referenceD = (Openable)reference.getState().getBlockData();
        doorOpen.setOpen(!referenceD.isOpen());
        changeState.setBlockData((BlockData)doorOpen);
        changeState.update();
    }
}
