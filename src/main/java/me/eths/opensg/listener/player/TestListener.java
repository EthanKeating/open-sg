package me.eths.opensg.listener.player;

import me.eths.opensg.SGPlugin;
import me.eths.opensg.game.GameHandler;
import me.eths.opensg.profile.Profile;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestListener implements Listener {

    private final SGPlugin plugin;
    public TestListener(SGPlugin plugin) { this.plugin = plugin; }

    @EventHandler
    private void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(event.getBlock().getChunk().getTileEntities().length + "");

        List<Block> nonVisibleBlocks = new ArrayList<>();
        Chunk chunk = event.getBlock().getChunk();

        int minY = 0; // Minimum Y-coordinate (e.g., -64 in modern versions)
        int maxY = chunk.getWorld().getMaxHeight(); // Maximum Y-coordinate (e.g., 319 in modern versions)

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = minY; y < maxY; y++) {
                    Block block = chunk.getBlock(x, y, z);

                    Block[] adjacentBlocks = {
                            block.getRelative(0, 1, 0),  // Up
                            block.getRelative(0, -1, 0), // Down
                            block.getRelative(1, 0, 0),  // East
                            block.getRelative(-1, 0, 0), // West
                            block.getRelative(0, 0, 1),  // South
                            block.getRelative(0, 0, -1)  // North
                    };

                    if (block.getType() != Material.AIR || block.getType().isSolid()) {

                        boolean isVisible = false;

                        for (Block adjacent : adjacentBlocks) {
                            if (adjacent.getType() == Material.AIR || !adjacent.getType().isSolid()) {
                                isVisible = true;
                            }
                        }
                        if (!isVisible)
                            nonVisibleBlocks.add(block);

                    }

                }
            }
        }

        for(Block block : nonVisibleBlocks) {
            block.setType(Material.AIR);
        }

    }
}
