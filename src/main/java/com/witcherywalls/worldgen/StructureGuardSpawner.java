package com.witcherywalls.worldgen;

import com.witcherywalls.entity.EntityVillageGuard;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public final class StructureGuardSpawner
{
    private StructureGuardSpawner()
    {
    }

    public static void spawnGuards(World world, StructureBoundingBox bounds, int guardsSpawned, int x, int y, int z, int count,
                                   GuardSpawnCallback callback)
    {
        if (guardsSpawned >= count)
        {
            return;
        }

        for (int guardNumber = guardsSpawned; guardNumber <= count; guardNumber++)
        {
            int worldX = callback.getX(x, z);
            int worldY = callback.getY(y);
            int worldZ = callback.getZ(x, z);

            if (!bounds.isVecInside(new net.minecraft.util.math.BlockPos(worldX, worldY, worldZ)))
            {
                break;
            }

            callback.onSpawned();

            EntityVillageGuard guard = new EntityVillageGuard(world);
            guard.setPosition(worldX + 0.5D, worldY, worldZ + 0.5D);
            guard.onInitialSpawn(world.getDifficultyForLocation(guard.getPosition()), null);
            world.spawnEntity(guard);
            guard.syncEquipmentToClients();
        }
    }

    public interface GuardSpawnCallback
    {
        int getX(int localX, int localZ);

        int getY(int localY);

        int getZ(int localX, int localZ);

        void onSpawned();
    }
}
