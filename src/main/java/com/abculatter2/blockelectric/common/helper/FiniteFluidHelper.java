package com.abculatter2.blockelectric.common.helper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

public class FiniteFluidHelper {

	public static void drain(World world, BlockPos pos, IBlockState state, int amount) {
		int quantaRemaining = state.getValue(BlockFluidBase.LEVEL) + 1;
		quantaRemaining -= amount;
		if (quantaRemaining > 0) {
			world.setBlockState(pos, state.withProperty(BlockFluidBase.LEVEL, quantaRemaining - 1), 2);
		} else {
			world.setBlockToAir(pos);
		}
	}

	public static void fill(World world, BlockPos pos, IBlockState state, int amount) {
		int quantaRemaining = state.getValue(BlockFluidBase.LEVEL) + 1;
		quantaRemaining += amount;
		if (quantaRemaining < 8) {
			world.setBlockState(pos, state.withProperty(BlockFluidBase.LEVEL, quantaRemaining - 1), 2);
		}
	}
}
