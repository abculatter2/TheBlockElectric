package com.abculatter2.blockelectric.common.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidFiniteCrafting extends BlockFluidFinite {

	public BlockFluidFiniteCrafting(Fluid fluid, Material material) {
		super(fluid, material);
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
