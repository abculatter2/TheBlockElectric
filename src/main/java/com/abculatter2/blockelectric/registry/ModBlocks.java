package com.abculatter2.blockelectric.registry;

import com.abculatter2.blockelectric.TheBlockElectric;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

@Mod.EventBusSubscriber(modid = TheBlockElectric.MODID)
public class ModBlocks {

	private static final List<Item> itemblocks = Lists.newArrayList();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> reg = event.getRegistry();
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> reg = event.getRegistry();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent event) {
		for (Item item : itemblocks)
			if (item.getRegistryName() != null)
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));
	}

	private static void registerItemBlock(IForgeRegistry<Item> reg, Block b) {
		ItemBlock item = new ItemBlock(b);
		if (b.getRegistryName() != null)
			item.setRegistryName(b.getRegistryName());
		reg.register(item);
		itemblocks.add(item);
	}

}
