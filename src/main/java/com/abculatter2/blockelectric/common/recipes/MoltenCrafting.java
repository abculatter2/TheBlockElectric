package com.abculatter2.blockelectric.common.recipes;

import com.abculatter2.blockelectric.common.fluid.BlockFluidFiniteCrafting;
import com.abculatter2.blockelectric.registry.ModFluids;
import com.abculatter2.blockelectric.registry.ModItems;
import com.google.common.collect.Lists;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

import java.util.List;

public class MoltenCrafting {

	public static final List<MoltenRecipe> RECIPES;

	public static final MoltenRecipe psteel;

	static {
		RECIPES = Lists.newArrayList(

				psteel = new MoltenRecipe(ModFluids.fluidMoltenIron, 2, Ingredient.fromItems(Items.REDSTONE, Items.BLAZE_POWDER), new ItemStack(ModItems.pSteel))

		);
	}

	public static class MoltenRecipe {

		private final Fluid fluid;
		private final int fluidamount;
		private final Ingredient inputs;
		private final ItemStack output;

		public MoltenRecipe(Fluid fluid, int fluidamount, Ingredient inputs, ItemStack output) {
			this.fluid = fluid;
			this.fluidamount = fluidamount;
			this.inputs = inputs;
			this.output = output;
		}

		public boolean isIngredient(ItemStack stack) {
			for (ItemStack checkstack : inputs.getMatchingStacks())
				if (stack.isItemEqualIgnoreDurability(checkstack))
					return true;
			return false;
		}

		public boolean check(World world, BlockPos pos, IBlockState state) {
			if (state.getBlock() instanceof BlockFluidFiniteCrafting)
				if (((BlockFluidFiniteCrafting) state.getBlock()).getFluid() != fluid || state.getValue(BlockFluidBase.LEVEL) + 1 < fluidamount)
					return false;
			List<ItemStack> list = Lists.newArrayList(inputs.getMatchingStacks());
			for (EntityItem item : world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)))) {
				if (!item.isDead && !item.getItem().isEmpty()) {
					list.removeIf(i -> item.getItem().isItemEqualIgnoreDurability(i));
				}
			}
			return list.isEmpty();
		}

		public void craft(World world, BlockPos pos, IBlockState state) {
			List<Item> used = Lists.newArrayList();
			for (EntityItem item : world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)))) {
				if (!item.isDead && !item.getItem().isEmpty() && !used.contains(item.getItem().getItem())) {
					used.add(item.getItem().getItem());
					item.getItem().shrink(1);
				}
			}
			if (state.getBlock() instanceof BlockFluidFiniteCrafting)
				((BlockFluidFiniteCrafting) state.getBlock()).drain(world, pos, state, 1);
			InventoryHelper.spawnItemStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, output.copy());
		}

	}

}
