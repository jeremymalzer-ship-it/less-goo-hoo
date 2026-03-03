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

    // Ripperdoc NPC - verkauft Cyberware und installiert sie
    public static final RegistryObject<EntityType<?>> RIPPERDOC = ENTITY_TYPES.register("ripperdoc",
            () -> EntityType.Builder.of(RipperdocEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .build("ripperdoc"));

    // Cyborg-Kämpfer - schwieriger Gegner mit Cyberware
    public static final RegistryObject<EntityType<?>> CYBORG_WARRIOR = ENTITY_TYPES.register("cyborg_warrior",
            () -> EntityType.Builder.of(CyborgWarriorEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.8f)
                    .build("cyborg_warrior"));

    // Netrunner - verzauberter Mob, der Quickhacking nutzt
    public static final RegistryObject<EntityType<?>> NETRUNNER = ENTITY_TYPES.register("netrunner",
            () -> EntityType.Builder.of(NetrunnerEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.8f)
                    .build("netrunner"));

    // Lucy-inspirierter Charakter (aus Edgerunners)
    public static final RegistryObject<EntityType<?>> LUCY_CYBORG = ENTITY_TYPES.register("lucy_cyborg",
            () -> EntityType.Builder.of(LucyCyborgEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .build("lucy_cyborg"));

    // David-inspirierter Charakter (aus Edgerunners)
    public static final RegistryObject<EntityType<?>> DAVID_CYBORG = ENTITY_TYPES.register("david_cyborg",
            () -> EntityType.Builder.of(DavidCyborgEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .build("david_cyborg"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
