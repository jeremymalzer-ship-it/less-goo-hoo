package com.cyberware.network;

import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.cyberware.CyberwareMod;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(CyberwareMod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static void register() {
        INSTANCE.messageBuilder(CyberwareActivatePacket.class, packetId++, NetworkDirection.PLAY_TO_SERVER)
                .decoder(CyberwareActivatePacket::new)
                .encoder(CyberwareActivatePacket::toBytes)
                .consumerMainThread(CyberwareActivatePacket::handle)
                .add();

        INSTANCE.messageBuilder(CyberwareSyncPacket.class, packetId++, NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CyberwareSyncPacket::new)
                .encoder(CyberwareSyncPacket::toBytes)
                .consumerMainThread(CyberwareSyncPacket::handle)
                .add();

        INSTANCE.messageBuilder(ChromeLevelPacket.class, packetId++, NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ChromeLevelPacket::new)
                .encoder(ChromeLevelPacket::toBytes)
                .consumerMainThread(ChromeLevelPacket::handle)
                .add();
    }

    // Paket zum Aktivieren von Cyberware
    public static class CyberwareActivatePacket {
        private String cyberwareName;

        public CyberwareActivatePacket(String cyberwareName) {
            this.cyberwareName = cyberwareName;
        }

        public CyberwareActivatePacket(FriendlyByteBuf buf) {
            this.cyberwareName = buf.readUtf();
        }

        public void toBytes(FriendlyByteBuf buf) {
            buf.writeUtf(cyberwareName);
        }

        public boolean handle(NetworkEvent.Context context) {
            context.enqueueWork(() -> {
                // Server-side handling
                net.minecraft.server.level.ServerPlayer player = context.getSender();
                if(player != null) {
                    CyberwareMod.LOGGER.info("Cyberware aktiviert: " + cyberwareName);
                }
            });
            return true;
        }
    }

    // Paket zum Synchronisieren von Cyberware-Daten
    public static class CyberwareSyncPacket {
        private java.util.Set<String> installedCyberware;

        public CyberwareSyncPacket(java.util.Set<String> installedCyberware) {
            this.installedCyberware = installedCyberware;
        }

        public CyberwareSyncPacket(FriendlyByteBuf buf) {
            int size = buf.readInt();
            this.installedCyberware = new java.util.HashSet<>();
            for(int i = 0; i < size; i++) {
                this.installedCyberware.add(buf.readUtf());
            }
        }

        public void toBytes(FriendlyByteBuf buf) {
            buf.writeInt(installedCyberware.size());
            for(String cyber : installedCyberware) {
                buf.writeUtf(cyber);
            }
        }

        public boolean handle(NetworkEvent.Context context) {
            context.enqueueWork(() -> {
                // Client-side handling
                CyberwareMod.LOGGER.info("Cyberware synchronisiert: " + installedCyberware.size() + " Stück");
            });
            return true;
        }
    }

    // Paket zum Synchronisieren des Chrome-Levels
    public static class ChromeLevelPacket {
        private float chromeLevel;

        public ChromeLevelPacket(float chromeLevel) {
            this.chromeLevel = chromeLevel;
        }

        public ChromeLevelPacket(FriendlyByteBuf buf) {
            this.chromeLevel = buf.readFloat();
        }

        public void toBytes(FriendlyByteBuf buf) {
            buf.writeFloat(chromeLevel);
        }

        public boolean handle(NetworkEvent.Context context) {
            context.enqueueWork(() -> {
                // Client-side handling
                CyberwareMod.LOGGER.debug("Chrome Level: " + chromeLevel);
            });
            return true;
        }
    }
}
