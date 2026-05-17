package net.minecraftforge.fml.relauncher;

/**
 * Shadow of Forge's Side enum for dev runs where IDE-downloaded sources incorrectly add BUKKIT,
 * which breaks FML network channel registration (NetworkRegistry.newChannel NPE).
 */
public enum Side
{
    CLIENT,
    SERVER;

    public boolean isServer()
    {
        return !isClient();
    }

    public boolean isClient()
    {
        return this == CLIENT;
    }
}
