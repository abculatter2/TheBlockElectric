package com.abculatter2.blockelectric.registry;

import com.abculatter2.blockelectric.TheBlockElectric;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials { // ObjectHolders are dumb so we have to keep these fields seperate

	public static final Item.ToolMaterial TOOL_MATERIAL_PSTEEL;

	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_PSTEEL;

	static { // So Intellij doesn't complain
		TOOL_MATERIAL_PSTEEL = EnumHelper.addToolMaterial(new ResourceLocation(TheBlockElectric.MODID, "TOOL_MATERIAL_PSTEEL").toString(), 2, 500, 6.0F, 2.0F, 14);

		ResourceLocation psteel = new ResourceLocation(TheBlockElectric.MODID, "psteel");
		ARMOR_MATERIAL_PSTEEL = EnumHelper.addArmorMaterial(psteel.toString(), psteel.toString(), 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	}
}
