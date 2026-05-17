package com.witcherywalls.block;

import com.witcherywalls.worldgen.VillageWallGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.ArrayList;
import java.util.List;

public class TileEntityVillageWallGen extends TileEntity implements ITickable
{
    private List<VillageWallGenerator.StructureBounds> pathBounds;
    private Biome biome;
    private boolean desert;
    private long ticks;

    @Override
    public void update()
    {
        ticks++;

        if (world.isRemote || pathBounds == null)
        {
            if (!world.isRemote && pathBounds == null && ticks > 1000L)
            {
                world.setBlockToAir(pos);
            }
            return;
        }

        if (ticks > 40L)
        {
            VillageWallGenerator.placeWalls(world, pathBounds, pos.getX(), pos.getY(), pos.getZ(), biome, desert);
            pathBounds = null;
            world.setBlockToAir(pos);
        }
        else if (ticks > 1000L)
        {
            pathBounds = null;
            world.setBlockToAir(pos);
        }
    }

    public void setStructure(List<?> pieces, StructureVillagePieces.Start start)
    {
        biome = start.biome;
        desert = biome != null && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY);
        pathBounds = new ArrayList<>();

        for (Object piece : pieces)
        {
            if (piece instanceof StructureVillagePieces.Road)
            {
                pathBounds.add(new VillageWallGenerator.StructureBounds((StructureVillagePieces.Road) piece, 20, 7));
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        pathBounds = null;
    }
}
