package de.marvinleiers.baseplugin.listeners;

import de.marvinleiers.baseplugin.BasePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CreateBasePlayer implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        BasePlugin.addPlayer(event.getPlayer());
    }
}
