package de.marvinleiers.baseplugin.utils;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import de.marvinleiers.baseplugin.BasePlugin;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Schematic
{
    private static Clipboard clipboard;

    public static void loadSchematics()
    {
        File file = new File(BasePlugin.getInstance().getDataFolder().getPath() + "/schematics/test.schematic");

        ClipboardFormat format = ClipboardFormats.findByFile(file);

        try (ClipboardReader reader = format.getReader(new FileInputStream(file)))
        {
            clipboard = reader.read();
            System.out.println("loaded schematic");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void paste(int x, int y, int z)
    {
        try (EditSession editSession = WorldEdit.getInstance().newEditSession(new BukkitWorld(Bukkit.getWorld("world"))))
        {
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(x, y, z))
                    .ignoreAirBlocks(false)
                    .build();

            Operations.complete(operation);
        }
        catch (WorldEditException e)
        {
            e.printStackTrace();
        }
    }
}
