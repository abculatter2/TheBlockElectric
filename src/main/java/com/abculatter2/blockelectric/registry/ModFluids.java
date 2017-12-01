package com.abculatter2.blockelectric.registry;

import com.abculatter2.blockelectric.TheBlockElectric;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(TheBlockElectric.MODID)
@Mod.EventBusSubscriber(modid = TheBlockElectric.MODID)
public class ModFluids {

	public static Fluid fluidMoltenIron;

	@GameRegistry.ObjectHolder("molteniron")
	public static Block blockMoltenIron = Blocks.AIR;

	static {
		fluidMoltenIron = createFluid(TheBlockElectric.MODID + ".molteniron", "fluids/molteniron/fluid", true, true)

				.setLuminosity(8).setDensity(3000).setViscosity(6000).setTemperature(1783);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new BlockFluidClassic(fluidMoltenIron, Material.LAVA).setRegistryName("molteniron").setUnlocalizedName(TheBlockElectric.MODID + ".molteniron"));
	}


	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {

	}

	private static Fluid createFluid(String name, String textureName, boolean hasFlowIcon, boolean hasBucket) {
		ResourceLocation still = new ResourceLocation(TheBlockElectric.MODID, textureName + "_still");
		ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(TheBlockElectric.MODID, textureName + "_flow") : still;
		Fluid fluid = new Fluid(name, still, flowing);
		if (!FluidRegistry.registerFluid(fluid))
			fluid = FluidRegistry.getFluid(name);
		if (hasBucket)
			FluidRegistry.addBucketForFluid(fluid);
		return fluid;
	}

}
