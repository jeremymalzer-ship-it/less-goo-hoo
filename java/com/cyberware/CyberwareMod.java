package com.cyberware;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafxmod.FXModLanguageProvider;
import net.minecraftforge.fml.javafxmod.client.FXModsScreen;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafxmod.FXModLanguageProvider;
import com.cyberware.block.ModBlocks;
import com.cyberware.item.ModItems;
import com.cyberware.entity.ModEntities;
import com.cyberware.capabilities.CyberwareCapability;
import com.cyberware.network.PacketHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("cyberware")
public class CyberwareMod {
    public static final String MOD_ID = "cyberware";
    public static final Logger LOGGER = LogManager.getLogger();

    public CyberwareMod() {
        IEventBus modEventBus = net.minecraftforge.fml.javafxmod.FXModLanguageProvider.getFXEventBus();
        
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PacketHandler.register();
            CyberwareCapability.register();
            LOGGER.info("Cyberware Mod Common Setup Complete");
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Cyberware Mod Client Setup Complete");
    }
}
