package com.abculatter2.blockelectric.registry;

import com.abculatter2.blockelectric.TheBlockElectric;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
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
public class ModArmors {

	@GameRegistry.ObjectHolder("psteel_helm")
	public static final Item psteel_helm = Items.AIR;
	@GameRegistry.ObjectHolder("psteel_chest")
	public static final Item psteel_chest = Items.AIR;
	@GameRegistry.ObjectHolder("psteel_legs")
	public static final Item psteel_legs = Items.AIR;
	@GameRegistry.ObjectHolder("psteel_boots")
	public static final Item psteel_boots = Items.AIR;

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> reg = event.getRegistry();
		reg.register(new ItemArmor(ModMaterials.ARMOR_MATERIAL_PSTEEL, 0, EntityEquipmentSlot.HEAD).setRegistryName("psteel_helm").setUnlocalizedName(TheBlockElectric.MODID + ".psteel_helm").setCreativeTab(ModCreativeTabs.TAB));
		reg.register(new ItemArmor(ModMaterials.ARMOR_MATERIAL_PSTEEL, 0, EntityEquipmentSlot.CHEST).setRegistryName("psteel_chest").setUnlocalizedName(TheBlockElectric.MODID + ".psteel_chest").setCreativeTab(ModCreativeTabs.TAB));
		reg.register(new ItemArmor(ModMaterials.ARMOR_MATERIAL_PSTEEL, 0, EntityEquipmentSlot.LEGS).setRegistryName("psteel_legs").setUnlocalizedName(TheBlockElectric.MODID + ".psteel_legs").setCreativeTab(ModCreativeTabs.TAB));
		reg.register(new ItemArmor(ModMaterials.ARMOR_MATERIAL_PSTEEL, 0, EntityEquipmentSlot.FEET).setRegistryName("psteel_boots").setUnlocalizedName(TheBlockElectric.MODID + ".psteel_boots").setCreativeTab(ModCreativeTabs.TAB));
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent event) {
		ModItems.registerModel(psteel_helm, 0, "armor");
		ModItems.registerModel(psteel_chest, 0, "armor");
		ModItems.registerModel(psteel_legs, 0, "armor");
		ModItems.registerModel(psteel_boots, 0, "armor");
	}

}
