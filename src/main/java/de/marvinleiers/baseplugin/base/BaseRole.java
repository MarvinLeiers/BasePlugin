package de.marvinleiers.baseplugin.base;

public enum BaseRole
{
    LEADER("Leader"),
    MEMBER("Member");

    String name;

    BaseRole(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
