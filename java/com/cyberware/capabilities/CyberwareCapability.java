package com.cyberware.capabilities;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

@Mod.EventBusSubscriber(modid = "cyberware")
public class CyberwareCapability {
    
    public static final Capability<ICyberwareData> CYBERWARE_CAP = CapabilityManager.get(new CapabilityToken<>(){});
    
    public static void register() {
        // Capability wird automatisch registriert
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Player> event) {
        CyberwareData data = new CyberwareData();
        event.addCapability(
                new ResourceLocation("cyberware", "cyberware_data"),
                new CyberwareProvider(data)
        );
    }

    public interface ICyberwareData {
        boolean hasCyberware(String name);
        void addCyberware(String name);
        void removeCyberware(String name);
        int getCyberwareCount();
        int getMaxCyberwareSlots();
        boolean canInstallCyberware();
        void setChrome(float amount);
        float getChrome();
        CompoundTag serializeNBT();
        void deserializeNBT(CompoundTag tag);
    }

    public static class CyberwareData implements ICyberwareData {
        private static final int MAX_CYBERWARE_SLOTS = 5;
        private final java.util.Set<String> installedCyberware = new java.util.HashSet<>();
        private float chromeLevel = 0.0f; // 0-100, wie viel Cyberware installiert ist
        
        @Override
        public boolean hasCyberware(String name) {
            return installedCyberware.contains(name);
        }

        @Override
        public void addCyberware(String name) {
            if(installedCyberware.size() < MAX_CYBERWARE_SLOTS) {
                installedCyberware.add(name);
                chromeLevel += 20.0f;
                if(chromeLevel > 100.0f) chromeLevel = 100.0f;
            }
        }

        @Override
        public void removeCyberware(String name) {
            if(installedCyberware.remove(name)) {
                chromeLevel -= 20.0f;
                if(chromeLevel < 0.0f) chromeLevel = 0.0f;
            }
        }

        @Override
        public int getCyberwareCount() {
            return installedCyberware.size();
        }

        @Override
        public int getMaxCyberwareSlots() {
            return MAX_CYBERWARE_SLOTS;
        }

        @Override
        public boolean canInstallCyberware() {
            return installedCyberware.size() < MAX_CYBERWARE_SLOTS && chromeLevel < 100.0f;
        }

        @Override
        public void setChrome(float amount) {
            this.chromeLevel = Math.min(100.0f, Math.max(0.0f, amount));
        }

        @Override
        public float getChrome() {
            return chromeLevel;
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putFloat("chrome", chromeLevel);
            
            CompoundTag cyberwareTag = new CompoundTag();
            int index = 0;
            for(String cyber : installedCyberware) {
                cyberwareTag.putString("cyber_" + index, cyber);
                index++;
            }
            tag.put("installed", cyberwareTag);
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag tag) {
            chromeLevel = tag.getFloat("chrome");
            installedCyberware.clear();
            
            CompoundTag cyberwareTag = tag.getCompound("installed");
            for(int i = 0; i < MAX_CYBERWARE_SLOTS; i++) {
                if(cyberwareTag.contains("cyber_" + i)) {
                    installedCyberware.add(cyberwareTag.getString("cyber_" + i));
                }
            }
        }
    }

    public static class CyberwareProvider extends net.minecraftforge.common.capabilities.CapabilityProvider<ICyberwareData> {
        public CyberwareProvider(ICyberwareData instance) {
            super(CYBERWARE_CAP, () -> instance);
        }
    }
}
