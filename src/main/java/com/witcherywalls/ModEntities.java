package com.witcherywalls;

import com.witcherywalls.entity.EntityVillageGuard;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class ModEntities
{
    private static final int VILLAGE_GUARD_ID = 0;
    private static final int VILLAGE_GUARD_EGG_PRIMARY = 0x222222;
    private static final int VILLAGE_GUARD_EGG_SECONDARY = 0x515A2A;

    private ModEntities()
    {
    }

    public static void register()
    {
        EntityRegistry.registerModEntity(
                new ResourceLocation(WitcheryWallsMod.MODID, "village_guard"),
                EntityVillageGuard.class,
                "village_guard",
                VILLAGE_GUARD_ID,
                WitcheryWallsMod.instance,
                80,
                3,
                true,
                VILLAGE_GUARD_EGG_PRIMARY,
                VILLAGE_GUARD_EGG_SECONDARY
        );
    }

    public static void registerSpawnEgg()
    {
    }
}
