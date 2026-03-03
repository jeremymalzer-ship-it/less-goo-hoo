package com.cyberware.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

public class CyborgWarriorEntity extends Zombie {
    private int cyberwareActivationTimer = 0;

    public CyborgWarriorEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        
        // Cyborg Attribute
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(40.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(8.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.4);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR).setBaseValue(10.0);
        
        this.setHealth(40.0f);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, 
                                        MobSpawnType spawnType, SpawnGroupData spawnData, CompoundTag tag) {
        this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 1));
        this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 0));
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, tag);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        
        this.cyberwareActivationTimer++;
        
        // Alle 5 Sekunden aktiviert der Cyborg einen Cyberware-Effekt
        if(this.cyberwareActivationTimer >= 100) {
            activateCyborgAbility();
            this.cyberwareActivationTimer = 0;
        }
    }

    private void activateCyborgAbility() {
        if(this.getTarget() != null) {
            switch(this.random.nextInt(3)) {
                case 0: // Berserk-Effekt
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2));
                    this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
                    break;
                case 1: // Mantis Blades
                    // Extra Schaden im nächsten Angriff (durch DAMAGE_BOOST)
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 50, 3));
                    break;
                case 2: // Regeneration
                    this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0));
                    break;
            }
        }
    }

    @Override
    protected float getStandingEyeHeight(net.minecraft.world.entity.Pose pose, net.minecraft.world.entity.EntityDimensions dimensions) {
        return 1.8f;
    }
}
