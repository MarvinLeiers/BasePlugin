package de.marvinleiers.baseplugin.commands.subcommands;

import de.marvinleiers.baseplugin.BasePlugin;
import de.marvinleiers.baseplugin.base.BasePlayer;
import de.marvinleiers.baseplugin.menu.BaseInfoMenu;
import de.marvinleiers.marvinplugin.commands.Subcommand;
import de.marvinleiers.marvinplugin.utils.Messages;
import de.marvinleiers.menuapi.MenuAPI;
import org.bukkit.entity.Player;

public class BaseInfo extends Subcommand
{
    @Override
    public String getName()
    {
        return "info";
    }

    @Override
    public String getDescription()
    {
        return "View information about your base";
    }

    @Override
    public String getSyntax()
    {
        return "/base info";
    }

    @Override
    public String getPermission()
    {
        return null;
    }

    @Override
    public void execute(Player player, String[] strings)
    {
        BasePlayer bp = BasePlugin.getBasePlayer(player);

        if (bp.getBase() == null)
        {
            player.sendMessage(Messages.get("you-dont-have-a-base"));
            return;
        }

        new BaseInfoMenu(bp.getBase(), MenuAPI.getMenuUserInformation(player)).open();
    }
}
