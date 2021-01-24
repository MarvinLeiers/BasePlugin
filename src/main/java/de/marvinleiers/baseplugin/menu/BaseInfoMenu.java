package de.marvinleiers.baseplugin.menu;

import de.marvinleiers.baseplugin.base.Base;
import de.marvinleiers.menuapi.Menu;
import de.marvinleiers.menuapi.MenuUserInformation;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BaseInfoMenu extends Menu
{
    private final Base base;

    public BaseInfoMenu(Base base, MenuUserInformation menuUserInformation)
    {
        super(menuUserInformation);

        this.base = base;
    }

    @Override
    public String getTitle()
    {
        return base.getLeader().getName() + "'s base";
    }

    @Override
    public int getSlots()
    {
        return 9;
    }

    @Override
    public void setItems()
    {
        inventory.addItem(makeItem(Material.PAPER, "§6Leader: " + base.getLeader().getName()));
        inventory.addItem(makeItem(Material.PAPER, "§7Base-ID: "+ base.getUuid().toString()));
    }

    @Override
    public void handleClickActions(InventoryClickEvent inventoryClickEvent)
    {

    }
}
