package de.marvinleiers.baseplugin.base;

import de.marvinleiers.baseplugin.utils.Schematic;
import de.marvinleiers.customconfig.CustomConfig;
import de.marvinleiers.baseplugin.BasePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Base
{
    private static final CustomConfig customConfig = new CustomConfig(BasePlugin.getInstance().getDataFolder().getPath() +
            "/bases.yml");
    private static final HashMap<String, Base> baseHashMap = new HashMap<>();

    private final Location baseHearth;
    private final UUID uuid;
    private OfflinePlayer leader;

    private Base(Location baseHearth, OfflinePlayer player, UUID uuid)
    {
        this.baseHearth = baseHearth;
        this.leader = player;
        this.uuid = uuid;

        customConfig.setLocation("bases." + uuid + ".baseHearth", baseHearth);
        customConfig.set("bases." + uuid + ".leader", player.getUniqueId().toString());

        if (player.isOnline())
            BasePlugin.getBasePlayer((Player) player).setBase(this);

        baseHashMap.put(uuid.toString(), this);
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public Location getBaseHearth()
    {
        return baseHearth;
    }

    public OfflinePlayer getLeader()
    {
        return leader;
    }

    public void setLeader(Player leader)
    {
        this.leader = leader;
    }

    public static Base fromString(String str)
    {
        return baseHashMap.get(str);
    }

    public static Base create(Location baseHearth, Player player)
    {
        baseHearth.getBlock().setType(Material.CHEST);

        Schematic.paste(baseHearth.getBlockX(), baseHearth.getBlockY(), baseHearth.getBlockZ());
        createBaseHearthChest(baseHearth.getBlock(), BasePlugin.getBasePlayer(player));

        for (Entity entity : baseHearth.getWorld().getNearbyEntities(baseHearth, 3, 3, 3))
        {
            if (entity instanceof ArmorStand)
                entity.remove();
        }

        return new Base(baseHearth, player, UUID.randomUUID());
    }

    private static Chest createBaseHearthChest(Block block, BasePlayer basePlayer)
    {
        if (block.getType() != Material.CHEST)
            throw new UnsupportedOperationException("BaseHearth must be a chest!");

        Chest chest = (Chest) block.getState();
        chest.setCustomName("§6" + basePlayer.getPlayer().getName() + "'s Base");

        return chest;
    }

    public static void loadBases()
    {
        if (!customConfig.isSet("bases"))
            return;

        for (Map.Entry<String, Object> entry : customConfig.getSection("bases").getValues(false).entrySet())
        {
            try
            {
                OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(customConfig.getString("bases." + entry.getKey() + ".leader")));
                Location location = customConfig.getLocation("bases." + entry.getKey() + ".baseHearth");

                new Base(location, player, UUID.fromString(entry.getKey()));

                BasePlugin.getInstance().getLogger().info("Loaded base " + entry.getKey());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                BasePlugin.getInstance().getLogger().severe("Could not load base " + entry.getKey());
            }
        }
    }
}
