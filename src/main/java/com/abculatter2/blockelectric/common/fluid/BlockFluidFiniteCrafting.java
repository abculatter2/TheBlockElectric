package com.abculatter2.blockelectric.common.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class BlockFluidFiniteCrafting extends BlockFluidFinite {

	public BlockFluidFiniteCrafting(Fluid fluid, Material material) {
		super(fluid, material);
	}

	@Override
	public void updateTick(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand) {
		List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));
		for (EntityItem item : items) {
			if (item.getItem().getItem() == Items.BLAZE_POWDER) {
				drain(world, pos, state, 1);
			}
		}
	}

	public void drain(World world, BlockPos pos, IBlockState state, int amount) {
		int quantaRemaining = state.getValue(LEVEL) + 1;
		quantaRemaining -= amount;
		if (quantaRemaining > 0) {
			world.setBlockState(pos, state.withProperty(LEVEL, quantaRemaining - 1), 2);
		} else {
			world.setBlockToAir(pos);
		}
	}

	public void fill(World world, BlockPos pos, IBlockState state, int amount) {
		int quantaRemaining = state.getValue(LEVEL) + 1;
		quantaRemaining += amount;
		if (quantaRemaining < 8) {
			world.setBlockState(pos, state.withProperty(LEVEL, quantaRemaining - 1), 2);
		}
	}
}
