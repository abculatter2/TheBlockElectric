package com.abculatter2.blockelectric.registry;

import com.abculatter2.blockelectric.TheBlockElectric;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials { // ObjectHolders are dumb so we have to keep these fields seperate

	public static final Item.ToolMaterial MATERIAL_PSTEEL;

	static { // So Intellij doesn't complain
		MATERIAL_PSTEEL = EnumHelper.addToolMaterial(new ResourceLocation(TheBlockElectric.MODID, "MATERIAL_PSTEEL").toString(), 2, 500, 6.0F, 2.0F, 14);
	}
}
