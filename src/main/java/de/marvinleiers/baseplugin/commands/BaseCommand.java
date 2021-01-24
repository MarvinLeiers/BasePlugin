package de.marvinleiers.baseplugin.commands;

import de.marvinleiers.baseplugin.commands.subcommands.AddBaseHeart;
import de.marvinleiers.baseplugin.commands.subcommands.BaseInfo;
import de.marvinleiers.marvinplugin.commands.RootCommand;

public class BaseCommand extends RootCommand
{
    public BaseCommand()
    {
        super("base");

        addSubcommand(new AddBaseHeart());
        addSubcommand(new BaseInfo());
    }
}
