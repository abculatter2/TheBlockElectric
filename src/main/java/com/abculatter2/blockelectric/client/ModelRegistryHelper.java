package com.abculatter2.blockelectric.client;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class ModelRegistryHelper {

	@SideOnly(Side.CLIENT)
	public static void registerFluidModel(Block b) {
		if (!(b instanceof BlockFluidBase))
			return;
		BlockFluidBase fluid = (BlockFluidBase) b;
		final Item item = Item.getItemFromBlock(fluid);
		net.minecraft.client.renderer.block.model.ModelBakery.registerItemVariants(item);
		String domain = fluid.getRegistryName() == null ? "minecraft" : fluid.getRegistryName().getResourceDomain();
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(new ResourceLocation(domain, "fluids"), fluid.getFluid().getName());
		ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> modelResourceLocation));
		ModelLoader.setCustomStateMapper(fluid, new StateMapperBase() {
			@Nonnull
			@Override
			protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
				return modelResourceLocation;
			}
		});
	}

	@SideOnly(Side.CLIENT)
	public interface MeshDefinitionFix extends ItemMeshDefinition {
		// Helper method to easily create lambda instances of this class
		static ItemMeshDefinition create(MeshDefinitionFix lambda) {
			return lambda;
		}

		ModelResourceLocation getLocation(ItemStack stack);

		@Nonnull
		@Override
		default ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
			return getLocation(stack);
		}
	}

}
