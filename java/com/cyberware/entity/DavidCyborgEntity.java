package com.cyberware.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.nbt.CompoundTag;

public class DavidCyborgEntity extends Zombie {
    private int berserkCounter = 0;
    private boolean isBerserking = false;

    public DavidCyborgEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        
        // David Attribute - extreme Cyberware, junger und aggressiv
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(50.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(10.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.38);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR).setBaseValue(8.0);
        
        this.setHealth(50.0f);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(!this.level().isClientSide) {
            player.sendSystemMessage(Component.literal("§c[DAVID] §4Ich werde nicht verlieren!"));
            player.sendSystemMessage(Component.literal("§cMeine Cyberware ist stärker als alles!"));
            
            // David wird sofort aggressiv
            this.setTarget(player);
            this.berserkCounter = 0;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        
        this.berserkCounter++;
        
        if(this.getTarget() != null) {
            // Wenn David einen Ziel sieht, wird er berserking
            if(this.berserkCounter > 60 && !isBerserking) {
                activateBerserkMode();
                this.isBerserking = true;
            }
            
            // Berserk-Mode gibt ihm konstante Boosts
            if(this.isBerserking && this.berserkCounter % 20 == 0) {
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2));
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
            }
        } else {
            // Wenn kein Ziel, gehe aus dem Berserk-Modus heraus
            isBerserking = false;
            berserkCounter = 0;
        }
    }

    private void activateBerserkMode() {
        this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 3));
        this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 2));
        this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, Integer.MAX_VALUE, 1));
        
        // Kleine visuelle Änderung - Feuer-Partikel (Falls implementiert)
    }

    @Override
    public void die(net.minecraft.world.damagesource.DamageSource cause) {
        // David stirbt dramatisch
        for(int i = 0; i < 20; i++) {
            this.level().addParticle(net.minecraft.core.particles.ParticleTypes.EXPLOSION, 
                    this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        }
        super.die(cause);
    }

    @Override
    protected float getStandingEyeHeight(net.minecraft.world.entity.Pose pose, net.minecraft.world.entity.EntityDimensions dimensions) {
        return 1.8f;
    }
}
