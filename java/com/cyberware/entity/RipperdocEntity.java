package com.cyberware.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

public class RipperdocEntity extends WanderingTrader {
    
    public RipperdocEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(!this.level().isClientSide) {
            player.sendSystemMessage(Component.literal("§d[RIPPERDOC] §5Willkommen, Choom! Brauchst du neue Chrome?"));
            player.sendSystemMessage(Component.literal("§7Ich kann dir Cyberware installieren und anpassen!"));
            player.sendSystemMessage(Component.literal("§7Nutze Cyberware-Items mit Shift-Klick zum Aktivieren!"));
            
            // Hier könnte ein GUI für den Verkauf implementiert werden
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        
        // Ripperdoc kann Spieler erkennen und grüßen
        if(!this.level().isClientSide && this.tickCount % 100 == 0) {
            for(Player player : this.level().players()) {
                if(this.distanceTo(player) < 20) {
                    // Näher bei Spielern
                }
            }
        }
    }
}
