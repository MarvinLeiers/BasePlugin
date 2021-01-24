package de.marvinleiers.baseplugin.utils;

import de.marvinleiers.baseplugin.BasePlugin;
import de.marvinleiers.customconfig.CustomConfig;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationUtil
{
    private static final CustomConfig customConfig = new CustomConfig(BasePlugin.getInstance().getDataFolder().getPath() +
            "/locations.yml");

    private static final ArrayList<Location> baseHeartsLocation = new ArrayList<>();


    public static String saveAsString(Location location)
    {
        List<String> list = customConfig.getConfig().getStringList("locations");
        String locationAsString = location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() +
                "," + location.getPitch() + "," + location.getWorld().getName();

        list.add(locationAsString);

        customConfig.set("locations", list);

        if (!baseHeartsLocation.contains(location))
            baseHeartsLocation.add(location);

        return locationAsString;
    }

    public static ArrayList<Location> getBaseHeartsLocations()
    {
        return baseHeartsLocation;
    }
}
