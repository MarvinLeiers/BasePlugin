package de.marvinleiers.baseplugin;

import de.marvinleiers.baseplugin.base.Base;
import de.marvinleiers.baseplugin.commands.BaseCommand;
import de.marvinleiers.baseplugin.listeners.RightClickBaseHeartListener;
import de.marvinleiers.baseplugin.utils.Schematic;
import de.marvinleiers.customconfig.CustomConfig;
import de.marvinleiers.baseplugin.base.BasePlayer;
import de.marvinleiers.baseplugin.listeners.CreateBasePlayer;
import de.marvinleiers.marvinplugin.MarvinPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public final class BasePlugin extends MarvinPlugin
{
    private static final HashMap<Player, BasePlayer> basePlayerHashMap = new HashMap<>();
    private static CustomConfig customConfig;

    //TODO: make base terrain not editable (config)
    //TODO: leaders can add/remove players to/from the base

    @Override
    public void onEnable()
    {
        super.onEnable();

        customConfig = new CustomConfig(getInstance().getDataFolder().getPath() +
                "/config.yml");

        this.getServer().getPluginManager().registerEvents(new CreateBasePlayer(), this);
        this.getServer().getPluginManager().registerEvents(new RightClickBaseHeartListener(), this);

        new BaseCommand();

        add("heart-created", "&aHeart has been created!");
        add("you-dont-have-a-base", "&cYou don't have a base!");
        add("already-has-base", "&cYou already have a base!");
        add("hologram-title", "&aThis base is free to claim!");

        for (Player player : Bukkit.getOnlinePlayers())
            addPlayer(player);

        Base.loadBases();
        Schematic.loadSchematics();
    }

    public static void addPlayer(Player player)
    {
        basePlayerHashMap.put(player, new BasePlayer(player));
    }

    public static BasePlayer getBasePlayer(Player player)
    {
        return basePlayerHashMap.get(player);
    }

    public static CustomConfig getCustomConfig()
    {
        return customConfig;
    }
}
