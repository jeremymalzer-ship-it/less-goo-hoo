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
import com.cyberware.conversation.ConversationManager;

public class AdamSmasherEntity extends Zombie {
    private int aggressionLevel = 0;
    private boolean inConversation = false;

    public AdamSmasherEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        
        // Smasher ist der ULTIMATIVE Cyborg
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(100.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(15.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.45);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR).setBaseValue(15.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS).setBaseValue(5.0);
        
        this.setHealth(100.0f);
        
        // Permanente Effekte
        this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 2));
        this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 1));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(!this.level().isClientSide && !inConversation) {
            inConversation = true;
            ConversationManager.startConversation(player, "adam_smasher");
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        
        if(this.getTarget() != null && this.getTarget() instanceof Player) {
            aggressionLevel++;
            
            // Aggressive Attacken
            if(aggressionLevel % 40 == 0) {
                // Heavy Attack
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 4));
            }
            
            // Scream wenn sehr aggressiv
            if(aggressionLevel % 120 == 0) {
                this.level().addParticle(net.minecraft.core.particles.ParticleTypes.explosion,
                        this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            }
        } else {
            aggressionLevel = Math.max(0, aggressionLevel - 1);
            inConversation = false;
        }
    }

    @Override
    public void die(net.minecraft.world.damagesource.DamageSource cause) {
        // Riesige Explosion wenn Smasher stirbt
        for(int i = 0; i < 50; i++) {
            this.level().addParticle(net.minecraft.core.particles.ParticleTypes.explosion,
                    this.getX() + (Math.random() - 0.5) * 5, 
                    this.getY() + Math.random() * 5,
                    this.getZ() + (Math.random() - 0.5) * 5, 0, 0, 0);
        }
        super.die(cause);
    }
}

// ============================================================

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
import com.cyberware.conversation.ConversationManager;

public class MaxTacCyborgEntity extends Zombie {
    private int patrolTimer = 0;

    public MaxTacCyborgEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        
        // MaxTac - Militärische Übermacht
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(60.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(10.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.4);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR).setBaseValue(12.0);
        
        this.setHealth(60.0f);
        
        // Taktische Enhancements
        this.addEffect(new MobEffectInstance(MobEffects.GLOWING, Integer.MAX_VALUE, 0)); // Sichtbar
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(!this.level().isClientSide) {
            ConversationManager.startConversation(player, "maxtac_cyborg");
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        
        patrolTimer++;
        
        // MaxTac patrouilliert methodisch
        if(patrolTimer % 200 == 0) {
            this.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 100, 0));
        }
        
        if(this.getTarget() != null) {
            // Taktische Überlegenheit
            this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 0));
        }
    }
}

// ============================================================

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
import com.cyberware.conversation.ConversationManager;

public class DavidMartinezNPCEntity extends Zombie {
    private int sanityLevel = 100;
    private boolean hasOverClocked = false;

    public DavidMartinezNPCEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        
        // David - Begabter aber überbelasteter Edgerunner
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(45.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(9.0);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.35);
        
        this.setHealth(45.0f);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(!this.level().isClientSide) {
            ConversationManager.startConversation(player, "david_martinez");
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        
        // David wird psychotisch wenn zu viel Chrome nutzt
        sanityLevel = Math.max(0, sanityLevel - 1);
        
        if(this.getTarget() != null) {
            sanityLevel -= 5; // Kampf reduziert Sanität
            
            if(sanityLevel < 30) {
                // Chrome-Psychose aktiviert
                this.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 40, 1));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 3));
                hasOverClocked = true;
            }
        } else {
            sanityLevel = Math.min(100, sanityLevel + 1); // Erholt sich
        }
    }

    @Override
    public void die(net.minecraft.world.damagesource.DamageSource cause) {
        if(hasOverClocked) {
            // Dramatischer Tod wenn er in Chrome-Psychose war
            for(int i = 0; i < 30; i++) {
                this.level().addParticle(net.minecraft.core.particles.ParticleTypes.flame,
                        this.getX() + (Math.random() - 0.5) * 4,
                        this.getY() + Math.random() * 3,
                        this.getZ() + (Math.random() - 0.5) * 4,
                        (Math.random() - 0.5) * 0.1,
                        (Math.random() - 0.5) * 0.1,
                        (Math.random() - 0.5) * 0.1);
            }
        }
        super.die(cause);
    }
}

// ============================================================

// ModEntities.java Erweiterung
package com.cyberware.entity;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import com.cyberware.CyberwareMod;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CyberwareMod.MOD_ID);

    // Original NPCs
    public static final RegistryObject<EntityType<?>> RIPPERDOC = ENTITY_TYPES.register("ripperdoc",
            () -> EntityType.Builder.of(RipperdocEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .build("ripperdoc"));

    public static final RegistryObject<EntityType<?>> CYBORG_WARRIOR = ENTITY_TYPES.register("cyborg_warrior",
            () -> EntityType.Builder.of(CyborgWarriorEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.8f)
                    .build("cyborg_warrior"));

    public static final RegistryObject<EntityType<?>> NETRUNNER = ENTITY_TYPES.register("netrunner",
            () -> EntityType.Builder.of(NetrunnerEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.8f)
                    .build("netrunner"));

    public static final RegistryObject<EntityType<?>> LUCY_CYBORG = ENTITY_TYPES.register("lucy_cyborg",
            () -> EntityType.Builder.of(LucyCyborgEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .build("lucy_cyborg"));

    public static final RegistryObject<EntityType<?>> DAVID_CYBORG = ENTITY_TYPES.register("david_cyborg",
            () -> EntityType.Builder.of(DavidCyborgNPCEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .build("david_cyborg"));

    // NEUE NPCs
    public static final RegistryObject<EntityType<?>> ADAM_SMASHER = ENTITY_TYPES.register("adam_smasher",
            () -> EntityType.Builder.of(AdamSmasherEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 2.0f)
                    .build("adam_smasher"));

    public static final RegistryObject<EntityType<?>> MAXTAC_CYBORG = ENTITY_TYPES.register("maxtac_cyborg",
            () -> EntityType.Builder.of(MaxTacCyborgEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.8f)
                    .build("maxtac_cyborg"));

    public static final RegistryObject<EntityType<?>> DAVID_MARTINEZ_NPC = ENTITY_TYPES.register("david_martinez",
            () -> EntityType.Builder.of(DavidMartinezNPCEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .build("david_martinez"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
