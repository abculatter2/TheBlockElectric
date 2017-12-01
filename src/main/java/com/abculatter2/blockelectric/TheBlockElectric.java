package com.abculatter2.blockelectric;

import com.abculatter2.blockelectric.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = TheBlockElectric.MODID, name = "The Block Electric", version = TheBlockElectric.VERSION, acceptedMinecraftVersions = "[1.12,)")
public class TheBlockElectric {

	public static final String VERSION = "${version}";
	public static final String MODID = "blockelectric";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	@SidedProxy(clientSide = "com.abculatter2.blockelectric.proxy.ClientProxy", serverSide = "com.abculatter2.blockelectric.proxy.ServerProxy")
	public static CommonProxy proxy;

	@Mod.Instance(TheBlockElectric.MODID)
	public static TheBlockElectric instance;


	@Mod.EventHandler
	public void onPreInitialization(FMLPreInitializationEvent event) {
		proxy.preInit();
	}

	@Mod.EventHandler
	public void onInitialization(FMLInitializationEvent event) {
		proxy.init();
	}

	@Mod.EventHandler
	public void onPostInitialization(FMLPostInitializationEvent event) {
		proxy.postInit();
	}

}
