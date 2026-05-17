package com.witcherywalls.block;

import com.witcherywalls.WitcheryWallsMod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Hidden technical block used once per walled village to expand paths into perimeter walls.
 */
public class BlockVillageWallGen extends BlockContainer
{
    public BlockVillageWallGen()
    {
        super(Material.ROCK);
        setRegistryName(WitcheryWallsMod.MODID, "village_wall_gen");
        setUnlocalizedName(WitcheryWallsMod.MODID + ".village_wall_gen");
        setCreativeTab(null);
        setHardness(10000.0F);
        setResistance(6000000.0F);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityVillageWallGen();
    }
}
