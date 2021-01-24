package de.marvinleiers.baseplugin.base;

import de.marvinleiers.baseplugin.BasePlugin;
import de.marvinleiers.customconfig.CustomConfig;
import org.bukkit.entity.Player;

public class BasePlayer
{
    private final CustomConfig customConfig;
    private final Player player;
    private Base base;

    public BasePlayer(Player player)
    {
        this.customConfig = new CustomConfig(BasePlugin.getInstance().getDataFolder().getPath() + "/players/" +
                player.getUniqueId().toString() + ".yml");

        this.base = Base.fromString(customConfig.getString("base"));

        if (!this.customConfig.isSet("name") || !this.customConfig.getString("name").equals(player.getName()))
            this.customConfig.set("name", player.getName());

        this.player = player;
    }

    public Base getBase()
    {
        return base;
    }

    public void setBase(Base base)
    {
        this.base = base;

        customConfig.set("base", base.getUuid().toString());
    }

    public Player getPlayer()
    {
        return player;
    }

    public CustomConfig getCustomConfig()
    {
        return customConfig;
    }
}
