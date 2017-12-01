package com.abculatter2.blockelectric.registry;

import com.abculatter2.blockelectric.TheBlockElectric;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ModCreativeTabs {

	public static final CreativeTabs TAB = new CreativeTabs(TheBlockElectric.MODID) {

		@Nonnull
		@Override
		public ItemStack getTabIconItem() {
			return ItemStack.EMPTY; // TODO
		}

	};

}
