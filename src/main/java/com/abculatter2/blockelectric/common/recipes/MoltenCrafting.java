package com.abculatter2.blockelectric.common.recipes;

import com.abculatter2.blockelectric.common.FiniteFluidHelper;
import com.abculatter2.blockelectric.registry.ModFluids;
import com.abculatter2.blockelectric.registry.ModItems;
import com.google.common.collect.Lists;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.List;

public class MoltenCrafting {

	public static final List<MoltenRecipe> RECIPES;

	public static final MoltenRecipe psteel;
	public static final MoltenRecipe emeraldBlock;

	// These are just for testing... for now. We'll come back to these later one. So ignore their contents.
	static {
		RECIPES = Lists.newArrayList(

				psteel = new MoltenItemRecipe(ModFluids.fluidMoltenIron, 2, Ingredient.fromItems(Items.REDSTONE, Items.BLAZE_POWDER), new ItemStack(ModItems.pSteel)),

				emeraldBlock = new MoltenBlockRecipe(FluidRegistry.WATER, Ingredient.fromItems(Items.BONE, Items.GUNPOWDER, Items.GOLD_INGOT), Blocks.EMERALD_BLOCK.getDefaultState())

		);
	}

	public static abstract class MoltenRecipe {

		protected final Fluid fluid;
		protected final Ingredient inputs;

		protected MoltenRecipe(Fluid fluid, Ingredient inputs) {
			this.fluid = fluid;
			this.inputs = inputs;
		}

		public boolean isIngredient(ItemStack stack) {
			for (ItemStack checkstack : inputs.getMatchingStacks())
				if (stack.isItemEqualIgnoreDurability(checkstack))
					return true;
			return false;
		}

		public boolean check(Fluid fluid, World world, BlockPos pos, IBlockState state) {
			if (this.fluid != fluid || !furtherChecks(world, pos, state))
				return false;
			List<ItemStack> list = Lists.newArrayList(inputs.getMatchingStacks());
			for (EntityItem item : world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)))) {
				if (!item.isDead && !item.getItem().isEmpty()) {
					list.removeIf(i -> item.getItem().isItemEqualIgnoreDurability(i));
				}
			}
			return list.isEmpty();
		}

		protected abstract boolean furtherChecks(World world, BlockPos pos, IBlockState state);

		public abstract void craft(World world, BlockPos pos, IBlockState state);
	}

	public static class MoltenItemRecipe extends MoltenRecipe {

		private final int fluidamount;
		private final ItemStack output;

		public MoltenItemRecipe(Fluid fluid, int fluidamount, Ingredient inputs, ItemStack output) {
			super(fluid, inputs);
			this.fluidamount = fluidamount;
			this.output = output;
		}

		@Override
		protected boolean furtherChecks(World world, BlockPos pos, IBlockState state) {
			return state.getValue(BlockFluidBase.LEVEL) + 1 >= fluidamount;
		}

		public void craft(World world, BlockPos pos, IBlockState state) {
			List<Item> used = Lists.newArrayList();
			for (EntityItem item : world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)))) {
				if (!item.isDead && !item.getItem().isEmpty() && !used.contains(item.getItem().getItem())) {
					used.add(item.getItem().getItem());
					if (inputs.apply(item.getItem()))
						item.getItem().shrink(1);
				}
			}
			if (state.getBlock() instanceof BlockFluidFinite)
				FiniteFluidHelper.drain(world, pos, state, fluidamount);
			else
				world.setBlockToAir(pos);
			InventoryHelper.spawnItemStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, output.copy());
		}

	}

	public static class MoltenBlockRecipe extends MoltenRecipe {

		private final IBlockState output;

		public MoltenBlockRecipe(Fluid fluid, Ingredient inputs, IBlockState output) {
			super(fluid, inputs);
			this.output = output;
		}

		@Override
		protected boolean furtherChecks(World world, BlockPos pos, IBlockState state) {
			return state.getValue(BlockFluidBase.LEVEL) == 0;
		}

		public void craft(World world, BlockPos pos, IBlockState state) {
			List<Item> used = Lists.newArrayList();
			for (EntityItem item : world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)))) {
				if (!item.isDead && !item.getItem().isEmpty() && !used.contains(item.getItem().getItem())) {
					used.add(item.getItem().getItem());
					if (inputs.apply(item.getItem()))
						item.getItem().shrink(1);
				}
			}
			world.setBlockState(pos, output);
		}

	}

}
