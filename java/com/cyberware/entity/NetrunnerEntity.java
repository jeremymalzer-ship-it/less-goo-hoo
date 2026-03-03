package com.cyberware.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

public class NetrunnerEntity extends Skeleton {
    private int quickhackTimer = 0;

    public NetrunnerEntity(EntityType<? extends Skeleton> entityType, Level level) {
        super(entityType, level);
        
        // Netrunner Attribute - spezialisiert auf Fernkampf und Hacking
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(25.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(5.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.3);
        
        this.setHealth(25.0f);
        
        // Netrunner sind hackbar - Glowing
        this.addEffect(new MobEffectInstance(MobEffects.GLOWING, Integer.MAX_VALUE, 0));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        
        this.quickhackTimer++;
        
        // Alle 3 Sekunden hackt ein Netrunner alle Mobs in der Nähe
        if(this.quickhackTimer >= 60) {
            activateQuickhack();
            this.quickhackTimer = 0;
        }
    }

    private void activateQuickhack() {
        // Alle Entities in 50 Blöcken Umkreis bekommen Confusion-Effekt
        this.level().getEntities(this, this.getBoundingBox().inflate(50))
                .forEach(entity -> {
                    if(entity instanceof net.minecraft.world.entity.Mob mob && mob != this) {
                        mob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 80, 1));
                    }
                });
    }

    @Override
    protected float getStandingEyeHeight(net.minecraft.world.entity.Pose pose, net.minecraft.world.entity.EntityDimensions dimensions) {
        return 1.8f;
    }
}
