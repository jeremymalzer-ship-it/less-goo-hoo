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
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import java.util.*;

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
        // Slot Management
        boolean installCyberware(String cyberwareId, String slotType);
        boolean uninstallCyberware(String slotType);
        String getInstalledCyberware(String slotType);
        boolean hasCyberware(String slotType);
        
        // Slot Info
        int getMaxSlotsForType(String slotType);
        int getUsedSlotsForType(String slotType);
        boolean canInstallType(String slotType);
        
        // Chrome Level
        void setChrome(float amount);
        float getChrome();
        
        // Persistence
        CompoundTag serializeNBT();
        void deserializeNBT(CompoundTag tag);
    }

    public static class CyberwareData implements ICyberwareData {
        // Slot-Typen und deren Limits
        private static final int OPERATIVE_SLOTS = 3;      // Kampf-Cyberware
        private static final int NETRUNNING_SLOTS = 2;     // Hacking
        private static final int STEALTH_SLOTS = 2;        // Verstecken
        private static final int DEFENSE_SLOTS = 2;        // Verteidigung
        private static final int SENSORY_SLOTS = 3;        // Sinne
        private static final int ICONIC_SLOTS = 1;         // Legendär (nur 1!)
        
        private float chromeLevel = 0.0f;
        
        // Installierte Cyberware pro Slot-Typ
        private final Map<String, String> installedCyberware = new HashMap<>();
        
        public CyberwareData() {
            // Alle Slots mit leer initialisieren
            for(int i = 1; i <= OPERATIVE_SLOTS; i++) {
                installedCyberware.put("operative_" + i, null);
            }
            for(int i = 1; i <= NETRUNNING_SLOTS; i++) {
                installedCyberware.put("netrunning_" + i, null);
            }
            for(int i = 1; i <= STEALTH_SLOTS; i++) {
                installedCyberware.put("stealth_" + i, null);
            }
            for(int i = 1; i <= DEFENSE_SLOTS; i++) {
                installedCyberware.put("defense_" + i, null);
            }
            for(int i = 1; i <= SENSORY_SLOTS; i++) {
                installedCyberware.put("sensory_" + i, null);
            }
            for(int i = 1; i <= ICONIC_SLOTS; i++) {
                installedCyberware.put("iconic_" + i, null);
            }
        }

        @Override
        public boolean installCyberware(String cyberwareId, String slotType) {
            // Finde nächsten freien Slot für diesen Typ
            int maxSlots = getMaxSlotsForType(slotType);
            
            for(int i = 1; i <= maxSlots; i++) {
                String slotKey = slotType + "_" + i;
                if(installedCyberware.get(slotKey) == null) {
                    installedCyberware.put(slotKey, cyberwareId);
                    updateChrome();
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean uninstallCyberware(String slotKey) {
            if(installedCyberware.containsKey(slotKey)) {
                installedCyberware.put(slotKey, null);
                updateChrome();
                return true;
            }
            return false;
        }

        @Override
        public String getInstalledCyberware(String slotType) {
            // Gebe erste installierte Cyberware dieses Typs zurück
            for(int i = 1; i <= getMaxSlotsForType(slotType); i++) {
                String slotKey = slotType + "_" + i;
                String cyber = installedCyberware.get(slotKey);
                if(cyber != null) return cyber;
            }
            return null;
        }

        @Override
        public boolean hasCyberware(String slotType) {
            for(int i = 1; i <= getMaxSlotsForType(slotType); i++) {
                String slotKey = slotType + "_" + i;
                if(installedCyberware.get(slotKey) != null) return true;
            }
            return false;
        }

        @Override
        public int getMaxSlotsForType(String slotType) {
            return switch(slotType) {
                case "operative" -> OPERATIVE_SLOTS;
                case "netrunning" -> NETRUNNING_SLOTS;
                case "stealth" -> STEALTH_SLOTS;
                case "defense" -> DEFENSE_SLOTS;
                case "sensory" -> SENSORY_SLOTS;
                case "iconic" -> ICONIC_SLOTS;
                default -> 0;
            };
        }

        @Override
        public int getUsedSlotsForType(String slotType) {
            int used = 0;
            int max = getMaxSlotsForType(slotType);
            for(int i = 1; i <= max; i++) {
                if(installedCyberware.get(slotType + "_" + i) != null) used++;
            }
            return used;
        }

        @Override
        public boolean canInstallType(String slotType) {
            return getUsedSlotsForType(slotType) < getMaxSlotsForType(slotType);
        }

        @Override
        public void setChrome(float amount) {
            this.chromeLevel = Math.min(100.0f, Math.max(0.0f, amount));
        }

        @Override
        public float getChrome() {
            return chromeLevel;
        }

        private void updateChrome() {
            // Chrome-Level = Anteil der belegten Slots
            int totalSlots = OPERATIVE_SLOTS + NETRUNNING_SLOTS + STEALTH_SLOTS + 
                            DEFENSE_SLOTS + SENSORY_SLOTS + ICONIC_SLOTS;
            int usedSlots = 0;
            
            for(String slot : installedCyberware.keySet()) {
                if(installedCyberware.get(slot) != null) usedSlots++;
            }
            
            chromeLevel = (usedSlots / (float)totalSlots) * 100.0f;
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putFloat("chrome", chromeLevel);
            
            CompoundTag cyberwareTag = new CompoundTag();
            int index = 0;
            for(String slot : installedCyberware.keySet()) {
                String cyber = installedCyberware.get(slot);
                if(cyber != null) {
                    CompoundTag slotTag = new CompoundTag();
                    slotTag.putString("slot_key", slot);
                    slotTag.putString("cyberware", cyber);
                    cyberwareTag.put("slot_" + index, slotTag);
                    index++;
                }
            }
            tag.put("installed", cyberwareTag);
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag tag) {
            chromeLevel = tag.getFloat("chrome");
            
            // Reset all slots
            installedCyberware.replaceAll((k, v) -> null);
            
            CompoundTag cyberwareTag = tag.getCompound("installed");
            for(String key : cyberwareTag.getAllKeys()) {
                CompoundTag slotTag = cyberwareTag.getCompound(key);
                String slotKey = slotTag.getString("slot_key");
                String cyber = slotTag.getString("cyberware");
                if(!slotKey.isEmpty() && !cyber.isEmpty()) {
                    installedCyberware.put(slotKey, cyber);
                }
            }
        }
    }

    public static class CyberwareProvider extends net.minecraftforge.common.capabilities.CapabilityProvider<ICyberwareData> {
        public CyberwareProvider(ICyberwareData instance) {
            super(CYBERWARE_CAP, () -> instance);
        }
    }

    // Hilfsmethode zum Zugreifen
    public static ICyberwareData getCyberwareData(Player player) {
        return player.getCapability(CYBERWARE_CAP).orElse(null);
    }
}
