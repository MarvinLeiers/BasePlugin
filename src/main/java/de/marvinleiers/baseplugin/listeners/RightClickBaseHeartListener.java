package de.marvinleiers.baseplugin.listeners;

import de.marvinleiers.baseplugin.BasePlugin;
import de.marvinleiers.baseplugin.base.Base;
import de.marvinleiers.baseplugin.base.BasePlayer;
import de.marvinleiers.baseplugin.utils.LocationUtil;
import de.marvinleiers.marvinplugin.utils.Messages;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickBaseHeartListener implements Listener
{
    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        if (!event.getAction().toString().contains("BLOCK"))
            return;

        Block block = event.getClickedBlock();

        if (block.getType() != Material.EMERALD_BLOCK)
            return;

        BasePlayer basePlayer = BasePlugin.getBasePlayer(event.getPlayer());

        if (basePlayer.hasBase())
        {
            basePlayer.getPlayer().sendMessage(Messages.get("already-has-base"));
            return;
        }

        for (Location location : LocationUtil.getBaseHeartsLocations())
        {
            if (location.distance(block.getLocation()) < 1)
            {
                Base.create(location, event.getPlayer());
            }
        }
    }
}
