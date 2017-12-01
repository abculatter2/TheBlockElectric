package com.abculatter2.blockelectric.common.event;

import com.abculatter2.blockelectric.TheBlockElectric;
import com.abculatter2.blockelectric.common.fluid.BlockFluidFiniteCrafting;
import com.abculatter2.blockelectric.common.recipes.MoltenCrafting;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = TheBlockElectric.MODID)
public class FluidCraftingListener {

	@SubscribeEvent
	public static void tick(TickEvent.WorldTickEvent e) {
		if (e.phase == TickEvent.Phase.START)
			return;
		for (EntityItem item : e.world.getEntities(EntityItem.class, input -> input != null && !input.isDead)) {
			boolean flag = true;
			for (MoltenCrafting.MoltenRecipe r : MoltenCrafting.RECIPES)
				if (r.isIngredient(item.getItem())) {
					flag = false;
					break;
				}
			if (flag)
				continue;
			BlockPos pos = new BlockPos(item);
			IBlockState state = e.world.getBlockState(pos);
			if (state.getBlock() instanceof BlockFluidFiniteCrafting)
				for (MoltenCrafting.MoltenRecipe recipe : MoltenCrafting.RECIPES)
					if (recipe.check(e.world, pos, state))
						recipe.craft(e.world, pos, state);
		}
	}

}
