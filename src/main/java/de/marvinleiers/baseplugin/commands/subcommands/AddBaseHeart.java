package de.marvinleiers.baseplugin.commands.subcommands;

import de.marvinleiers.baseplugin.utils.LocationUtil;
import de.marvinleiers.marvinplugin.commands.Subcommand;
import de.marvinleiers.marvinplugin.utils.Messages;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
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

        ArmorStand armorStand = (ArmorStand) world.spawnEntity(block.getLocation().add(0.5, 0, 0.5), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setCustomName(Messages.get("hologram-title"));
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);

        player.teleport(block.getLocation().add(0.5, 1, 0.5));

        LocationUtil.saveAsString(block.getLocation());

        player.sendMessage(Messages.get("heart-created"));
    }
}
