package com.cyberware.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

public class LucyCyborgEntity extends Villager {
    private boolean hasInteractedWithPlayer = false;

    public LucyCyborgEntity(EntityType<? extends Villager> entityType, Level level) {
        super(entityType, level);
        
        // Lucy Attribute - elegante Kämpferin mit Schnelligkeit
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(30.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(6.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.35);
        
        // Lucy hat Netrunning-Fähigkeiten (Blau Glowing)
        this.addEffect(new MobEffectInstance(MobEffects.GLOWING, Integer.MAX_VALUE, 0));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(!this.level().isClientSide) {
            if(!hasInteractedWithPlayer) {
                player.sendSystemMessage(Component.literal("§b[LUCY] §3Hey there, choom..."));
                player.sendSystemMessage(Component.literal("§3Brauchst du hilfe mit Netrunning?"));
                player.sendSystemMessage(Component.literal("§3Ich kenne alle Quickhacking-Tricks..."));
                hasInteractedWithPlayer = true;
                
                // Lucy gewährt dem Spieler kurzzeitig Netzwerk-Vision
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0));
            } else {
                player.sendSystemMessage(Component.literal("§3...bis dann, V."));
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        
        // Lucy nutzt Quickhacking auf Gegner
        if(this.getTarget() != null && this.tickCount % 80 == 0) {
            this.getTarget().addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60, 1));
        }
    }
}
