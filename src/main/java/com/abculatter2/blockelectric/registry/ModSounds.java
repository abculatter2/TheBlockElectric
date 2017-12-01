package com.abculatter2.blockelectric.registry;

import com.abculatter2.blockelectric.TheBlockElectric;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = TheBlockElectric.MODID)
public class ModSounds {

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {

	}

	private static SoundEvent registerSound(RegistryEvent.Register<SoundEvent> event, String soundName) {
		SoundEvent sound = new SoundEvent(new ResourceLocation(TheBlockElectric.MODID, soundName)).setRegistryName(soundName);
		event.getRegistry().register(sound);
		return sound;
	}
}