package com.witcherywalls;

import com.witcherywalls.block.TileEntityVillageWallGen;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModTileEntities
{
    private ModTileEntities()
    {
    }

    public static void register()
    {
        GameRegistry.registerTileEntity(TileEntityVillageWallGen.class, WitcheryWallsMod.MODID + ":village_wall_gen");
    }
}
