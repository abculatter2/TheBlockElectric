package com.abculatter2.blockelectric;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = TheBlockElectric.MODID)
@Config(modid = TheBlockElectric.MODID)
public class TheBlockElectricConfig {

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(TheBlockElectric.MODID))
			ConfigManager.sync(TheBlockElectric.MODID, Config.Type.INSTANCE);
	}

}
