package com.cyberware.item;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import com.cyberware.CyberwareMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CyberwareMod.MOD_ID);

    // Cyberware Items
    public static final RegistryObject<Item> SANDEVISTAN = ITEMS.register("sandevistan",
            () -> new CyberwareItem(new Item.Properties(), "sandevistan", "Zeit-Verlangsamung: Verlangsame Zeit für 10 Sekunden"));

    public static final RegistryObject<Item> BERSERK = ITEMS.register("berserk",
            () -> new CyberwareItem(new Item.Properties(), "berserk", "Kampf-Modus: Erhöhte Kraft und Geschwindigkeit"));

    public static final RegistryObject<Item> LEGENDARY_QUICKHACKING = ITEMS.register("legendary_quickhacking",
            () -> new CyberwareItem(new Item.Properties(), "legendary_quickhacking", "Netrunning: Hacke Mobs aus der Ferne"));

    public static final RegistryObject<Item> MANTIS_BLADES = ITEMS.register("mantis_blades",
            () -> new CyberwareItem(new Item.Properties(), "mantis_blades", "Messer-Arme: Extreme Nahkampf-Schärfe"));

    public static final RegistryObject<Item> GORILLA_ARMS = ITEMS.register("gorilla_arms",
            () -> new CyberwareItem(new Item.Properties(), "gorilla_arms", "Affenkraft: Kraft x2, Sprungkraft erhöht"));

    public static final RegistryObject<Item> PING = ITEMS.register("ping",
            () -> new CyberwareItem(new Item.Properties(), "ping", "Netzwerk-Scanner: Sehe Mobs durch Wände"));

    public static final RegistryObject<Item> OPTICAL_CAMO = ITEMS.register("optical_camo",
            () -> new CyberwareItem(new Item.Properties(), "optical_camo", "Unsichtbarkeit: Werde unsichtbar für Mobs"));

    public static final RegistryObject<Item> CHROME_BODY = ITEMS.register("chrome_body",
            () -> new CyberwareItem(new Item.Properties(), "chrome_body", "Verbesserter Körper: Erhöhte Gesundheit und Rüstung"));

    // Crafting Materials
    public static final RegistryObject<Item> CYBERWARE_CHIP = ITEMS.register("cyberware_chip",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MILITARY_GRADE_CHIPSET = ITEMS.register("military_grade_chipset",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NEURAL_INTERFACE = ITEMS.register("neural_interface",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> QUICKHACKING_PROTOCOL = ITEMS.register("quickhacking_protocol",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EDGE_RUNNE_SHARD = ITEMS.register("edgerunner_shard",
            () -> new Item(new Item.Properties().rarity(net.minecraft.world.item.Rarity.RARE)));

    public static final RegistryObject<Item> QUANTUM_PROCESSOR = ITEMS.register("quantum_processor",
            () -> new Item(new Item.Properties().rarity(net.minecraft.world.item.Rarity.EPIC)));

    // Soul Shards für Conversations
    public static final RegistryObject<Item> LUCY_SOUL_SHARD = ITEMS.register("lucy_soul_shard",
            () -> new Item(new Item.Properties().rarity(net.minecraft.world.item.Rarity.EPIC)));

    public static final RegistryObject<Item> DAVID_SOUL_SHARD = ITEMS.register("david_soul_shard",
            () -> new Item(new Item.Properties().rarity(net.minecraft.world.item.Rarity.EPIC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
