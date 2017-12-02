package com.abculatter2.blockelectric.registry;

import com.abculatter2.blockelectric.TheBlockElectric;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(TheBlockElectric.MODID)
@Mod.EventBusSubscriber(modid = TheBlockElectric.MODID)
public class ModItems {

	@GameRegistry.ObjectHolder("performance_steel")
	public static final Item pSteel = Items.AIR;
	@GameRegistry.ObjectHolder("obsidisteel")
	public static final Item obsidisteel = Items.AIR;
	@GameRegistry.ObjectHolder("plastic")
	public static final Item plastic = Items.AIR;
	@GameRegistry.ObjectHolder("plasidian")
	public static final Item plasidian = Items.AIR;

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> reg = event.getRegistry();
		reg.register(new Item().setRegistryName("performance_steel").setUnlocalizedName(TheBlockElectric.MODID + ".performance_steel").setCreativeTab(ModCreativeTabs.TAB));
		reg.register(new Item().setRegistryName("obsidisteel").setUnlocalizedName(TheBlockElectric.MODID + ".obsidisteel").setCreativeTab(ModCreativeTabs.TAB));
		reg.register(new Item().setRegistryName("plastic").setUnlocalizedName(TheBlockElectric.MODID + ".plastic").setCreativeTab(ModCreativeTabs.TAB));
		reg.register(new Item().setRegistryName("plasidian").setUnlocalizedName(TheBlockElectric.MODID + ".plasidian").setCreativeTab(ModCreativeTabs.TAB));
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent event) {
		registerModel(pSteel, 0);
		registerModel(obsidisteel, 0);
		registerModel(plastic, 0);
		registerModel(plasidian, 0);
	}

	public static void registerModel(Item item, int meta) {
		if (item.getRegistryName() != null) {
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}

	public static void registerModel(Item item, int meta, String dir) {
		if (item.getRegistryName() != null) {
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(item.getRegistryName().getResourceDomain(), dir + "/" + item.getRegistryName().getResourcePath()), "inventory"));
		}
	}

}
