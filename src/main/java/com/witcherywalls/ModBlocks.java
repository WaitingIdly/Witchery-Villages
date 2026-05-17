package com.witcherywalls;

import com.witcherywalls.block.BlockVillageWallGen;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = WitcheryWallsMod.MODID)
public final class ModBlocks
{
    public static BlockVillageWallGen VILLAGE_WALL_GEN;

    private ModBlocks()
    {
    }

    public static void register()
    {
        // Blocks are registered via RegistryEvent in registerBlocks().
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();
        VILLAGE_WALL_GEN = new BlockVillageWallGen();
        registry.register(VILLAGE_WALL_GEN);
    }
}
