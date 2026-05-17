package com.witcherywalls.worldgen;

import com.witcherywalls.ModBlocks;
import com.witcherywalls.block.TileEntityVillageWallGen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

public class VillageWallPiece extends ModVillagePiece
{
    private StructureVillagePieces.Start villageStart;
    private List<StructureComponent> villagePieces;
    private boolean hasMadeWallBlock;

    public VillageWallPiece()
    {
    }

    public VillageWallPiece(StructureVillagePieces.Start start, int componentType, Random rand, StructureBoundingBox bounds, int baseMode)
    {
        super(start, componentType);
        applyHorizontalFacing(this, baseMode);
        boundingBox = bounds;
        villageStart = start;
    }

    public static VillageWallPiece create(StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random rand,
                                          int x, int y, int z, int facing, int componentType)
    {
        StructureBoundingBox bounds = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 2, 7, 2, EnumFacing.getHorizontal(facing & 3));
        boolean canCreate = canPlaceAt(bounds) && StructureComponent.findIntersecting(pieces, bounds) == null;

        return canCreate ? new VillageWallPiece(startPiece, componentType, rand, bounds, facing) : null;
    }

    @Override
    public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
    {
        super.buildComponent(component, list, rand);
        villagePieces = list;
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound)
    {
        super.writeStructureToNBT(tagCompound);
        tagCompound.setBoolean("WallBlock", hasMadeWallBlock);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, net.minecraft.world.gen.structure.template.TemplateManager manager)
    {
        super.readStructureFromNBT(tagCompound, manager);
        hasMadeWallBlock = tagCompound.getBoolean("WallBlock");
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox bounds)
    {
        if (averageGroundLvl < 0)
        {
            averageGroundLvl = getAverageGroundLevel(world, bounds);

            if (averageGroundLvl < 0)
            {
                return true;
            }

            boundingBox.offset(0, averageGroundLvl - boundingBox.maxY + 7 - 1, 0);
        }

        if (!hasMadeWallBlock && villagePieces != null)
        {
            int x = 1;
            int z = 1;
            int xCoord = getXWithOffset(x, z);
            int yCoord = getYWithOffset(1);
            int zCoord = getZWithOffset(x, z);

            if (bounds.isVecInside(new BlockPos(xCoord, yCoord, zCoord)))
            {
                hasMadeWallBlock = true;
                world.setBlockState(new BlockPos(xCoord, yCoord, zCoord), ModBlocks.VILLAGE_WALL_GEN.getDefaultState(), 2);

                if (world.getTileEntity(new BlockPos(xCoord, yCoord, zCoord)) instanceof TileEntityVillageWallGen)
                {
                    TileEntityVillageWallGen tile = (TileEntityVillageWallGen) world.getTileEntity(new BlockPos(xCoord, yCoord, zCoord));
                    tile.setStructure(villagePieces, villageStart);
                }
            }
        }

        return true;
    }
}
