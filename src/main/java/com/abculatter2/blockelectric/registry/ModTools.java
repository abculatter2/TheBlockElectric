package com.abculatter2.blockelectric.registry;

import com.abculatter2.blockelectric.TheBlockElectric;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;


@GameRegistry.ObjectHolder(TheBlockElectric.MODID)
@Mod.EventBusSubscriber(modid = TheBlockElectric.MODID)
public class ModTools {

	@GameRegistry.ObjectHolder("psteel_sword")
	public static final Item psteel_sword = Items.AIR;
	@GameRegistry.ObjectHolder("psteel_pick")
	public static final Item psteel_pick = Items.AIR;
	@GameRegistry.ObjectHolder("psteel_axe")
	public static final Item psteel_axe = Items.AIR;
	@GameRegistry.ObjectHolder("psteel_spade")
	public static final Item psteel_spade = Items.AIR;
	@GameRegistry.ObjectHolder("psteel_hoe")
	public static final Item psteel_hoe = Items.AIR;

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> reg = event.getRegistry();
		reg.register(new ItemSword(ModMaterials.MATERIAL_PSTEEL).setRegistryName("psteel_sword").setUnlocalizedName(TheBlockElectric.MODID + ".psteel_sword").setCreativeTab(ModCreativeTabs.TAB));
		reg.register(new Pickaxe(ModMaterials.MATERIAL_PSTEEL).setRegistryName("psteel_pick").setUnlocalizedName(TheBlockElectric.MODID + ".psteel_pick").setCreativeTab(ModCreativeTabs.TAB));
		reg.register(new Axe(ModMaterials.MATERIAL_PSTEEL, 8.0F, -3.1F).setRegistryName("psteel_axe").setUnlocalizedName(TheBlockElectric.MODID + ".psteel_axe").setCreativeTab(ModCreativeTabs.TAB));
		reg.register(new Spade(ModMaterials.MATERIAL_PSTEEL).setRegistryName("psteel_spade").setUnlocalizedName(TheBlockElectric.MODID + ".psteel_spade").setCreativeTab(ModCreativeTabs.TAB));
		reg.register(new Hoe(ModMaterials.MATERIAL_PSTEEL).setRegistryName("psteel_hoe").setUnlocalizedName(TheBlockElectric.MODID + ".psteel_hoe").setCreativeTab(ModCreativeTabs.TAB));
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent event) {
		ModItems.registerModel(psteel_sword, 0, "tool");
		ModItems.registerModel(psteel_pick, 0, "tool");
		ModItems.registerModel(psteel_axe, 0, "tool");
		ModItems.registerModel(psteel_spade, 0, "tool");
		ModItems.registerModel(psteel_hoe, 0, "tool");
	}

	// Vanilla makes their ctors protected so we have to extend them

	static class Pickaxe extends ItemPickaxe {
		Pickaxe(ToolMaterial material) {
			super(material);
		}
	}

	static class Axe extends ItemAxe {
		Axe(ToolMaterial material, float damage, float speed) {
			super(material, damage, speed);
		}
	}

	static class Spade extends ItemSpade {
		Spade(ToolMaterial material) {
			super(material);
		}
	}

	static class Hoe extends ItemHoe {
		Hoe(ToolMaterial material) {
			super(material);
		}
	}
}
