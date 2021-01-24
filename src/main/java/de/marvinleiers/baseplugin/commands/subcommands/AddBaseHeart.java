package de.marvinleiers.baseplugin.commands.subcommands;

import de.marvinleiers.baseplugin.utils.LocationUtil;
import de.marvinleiers.marvinplugin.commands.Subcommand;
import de.marvinleiers.marvinplugin.utils.Messages;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class AddBaseHeart extends Subcommand
{
    @Override
    public String getName()
    {
        return "add";
    }

    @Override
    public String getDescription()
    {
        return "Add a Base-Hearth where you stand";
    }

    @Override
    public String getSyntax()
    {
        return "/base add";
    }

    @Override
    public String getPermission()
    {
        return "base.add";
    }

    @Override
    public void execute(Player player, String[] strings)
    {
        Location location = player.getLocation();
        World world = player.getWorld();

        Block block = world.getBlockAt(location);
        block.setType(Material.EMERALD_BLOCK);

        player.teleport(block.getLocation().add(0.5, 1, 0.5));

        LocationUtil.saveAsString(block.getLocation());

        player.sendMessage(Messages.get("heart-created"));
    }
}
