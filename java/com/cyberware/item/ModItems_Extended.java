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

    // ============= OPERATIVE CYBERWARE (Kampf) =============
    public static final RegistryObject<Item> SANDEVISTAN = ITEMS.register("sandevistan",
            () -> new CyberwareItem(new Item.Properties(), "sandevistan", "operative", "Zeit-Verlangsamung: Verlangsame Zeit für 10 Sekunden"));

    public static final RegistryObject<Item> BERSERK = ITEMS.register("berserk",
            () -> new CyberwareItem(new Item.Properties(), "berserk", "operative", "Kampf-Modus: Erhöhte Kraft und Geschwindigkeit"));

    public static final RegistryObject<Item> MANTIS_BLADES = ITEMS.register("mantis_blades",
            () -> new CyberwareItem(new Item.Properties(), "mantis_blades", "operative", "Messer-Arme: Extreme Nahkampf-Schärfe"));

    public static final RegistryObject<Item> GORILLA_ARMS = ITEMS.register("gorilla_arms",
            () -> new CyberwareItem(new Item.Properties(), "gorilla_arms", "operative", "Affenkraft: Kraft x2, Sprungkraft erhöht"));

    public static final RegistryObject<Item> MONOWIRE = ITEMS.register("monowire",
            () -> new CyberwareItem(new Item.Properties(), "monowire", "operative", "Mono-Draht: Klinge aus reinem Monomol-Material"));

    public static final RegistryObject<Item> TESLA_COILS = ITEMS.register("tesla_coils",
            () -> new CyberwareItem(new Item.Properties(), "tesla_coils", "operative", "Tesla-Spulen: Elektrische Blitze um dich herum"));

    public static final RegistryObject<Item> PROJECTILE_LAUNCHER = ITEMS.register("projectile_launcher",
            () -> new CyberwareItem(new Item.Properties(), "projectile_launcher", "operative", "Raketen-Werfer: Schieße Raketen"));

    public static final RegistryObject<Item> KERENZIKOV = ITEMS.register("kerenzikov",
            () -> new CyberwareItem(new Item.Properties(), "kerenzikov", "operative", "Kerenzikov: Reaktionszeit x3 erhöht"));

    // ============= NETRUNNING CYBERWARE (Hacking) =============
    public static final RegistryObject<Item> QUICKHACKING = ITEMS.register("quickhacking",
            () -> new CyberwareItem(new Item.Properties(), "quickhacking", "netrunning", "Quickhacking: Hacke Mobs aus der Ferne"));

    public static final RegistryObject<Item> BREACH_PROTOCOL = ITEMS.register("breach_protocol",
            () -> new CyberwareItem(new Item.Properties(), "breach_protocol", "netrunning", "Breach Protocol: Hacke Netzwerke"));

    public static final RegistryObject<Item> PING = ITEMS.register("ping",
            () -> new CyberwareItem(new Item.Properties(), "ping", "netrunning", "Netzwerk-Scanner: Sehe Mobs durch Wände"));

    public static final RegistryObject<Item> VULNERABILITY_SCANNER = ITEMS.register("vulnerability_scanner",
            () -> new CyberwareItem(new Item.Properties(), "vulnerability_scanner", "netrunning", "Scanne Schwachstellen von Zielen"));

    public static final RegistryObject<Item> PING_QUICKHACK = ITEMS.register("ping_quickhack",
            () -> new CyberwareItem(new Item.Properties(), "ping_quickhack", "netrunning", "Erweiterte Ping-Quickhacks"));

    public static final RegistryObject<Item> BREACH_AND_HACK = ITEMS.register("breach_and_hack",
            () -> new CyberwareItem(new Item.Properties(), "breach_and_hack", "netrunning", "Durchbrechen & Hacken kombiniert"));

    // ============= STEALTH CYBERWARE (Verstecken) =============
    public static final RegistryObject<Item> OPTICAL_CAMO = ITEMS.register("optical_camo",
            () -> new CyberwareItem(new Item.Properties(), "optical_camo", "stealth", "Unsichtbarkeit: Werde unsichtbar für Mobs"));

    public static final RegistryObject<Item> CAMOUFLAGE = ITEMS.register("camouflage",
            () -> new CyberwareItem(new Item.Properties(), "camouflage", "stealth", "Tarnung: Blend in mit Umgebung"));

    public static final RegistryObject<Item> SILENT_STEPS = ITEMS.register("silent_steps",
            () -> new CyberwareItem(new Item.Properties(), "silent_steps", "stealth", "Lautlose Schritte: Keine Geräusche"));

    public static final RegistryObject<Item> SHADOW_CLOAK = ITEMS.register("shadow_cloak",
            () -> new CyberwareItem(new Item.Properties(), "shadow_cloak", "stealth", "Schatten-Umhang: Verstecke dich in Dunkelheit"));

    // ============= DEFENSE CYBERWARE (Verteidigung) =============
    public static final RegistryObject<Item> CHROME_BODY = ITEMS.register("chrome_body",
            () -> new CyberwareItem(new Item.Properties(), "chrome_body", "defense", "Verbesserter Körper: Erhöhte Gesundheit und Rüstung"));

    public static final RegistryObject<Item> DERMAL_ARMOR = ITEMS.register("dermal_armor",
            () -> new CyberwareItem(new Item.Properties(), "dermal_armor", "defense", "Haut-Panzerung: Extra Schutz"));

    public static final RegistryObject<Item> KINETIC_DAMPENING = ITEMS.register("kinetic_dampening",
            () -> new CyberwareItem(new Item.Properties(), "kinetic_dampening", "defense", "Kinetische Dämpfung: Weniger Schaden"));

    public static final RegistryObject<Item> REGENERATION_SYSTEM = ITEMS.register("regeneration_system",
            () -> new CyberwareItem(new Item.Properties(), "regeneration_system", "defense", "Heilungssystem: Regeneriere kontinuierlich"));

    public static final RegistryObject<Item> QUICKDODGE = ITEMS.register("quickdodge",
            () -> new CyberwareItem(new Item.Properties(), "quickdodge", "defense", "Quick-Dodge: Weiche Angriffen automatisch aus"));

    public static final RegistryObject<Item> FORTIFIED_SKELETON = ITEMS.register("fortified_skeleton",
            () -> new CyberwareItem(new Item.Properties(), "fortified_skeleton", "defense", "Verstärktes Skelett: +Rüstung dauerhaft"));

    // ============= SENSORY CYBERWARE (Sinne) =============
    public static final RegistryObject<Item> TACTICAL_SCANNER = ITEMS.register("tactical_scanner",
            () -> new CyberwareItem(new Item.Properties(), "tactical_scanner", "sensory", "Taktischer Scanner: Alle Info über Ziele"));

    public static final RegistryObject<Item> THERMAL_VISION = ITEMS.register("thermal_vision",
            () -> new CyberwareItem(new Item.Properties(), "thermal_vision", "sensory", "Wärmesicht: Sehe Wärme-Signaturen"));

    public static final RegistryObject<Item> ZOOM_OPTICS = ITEMS.register("zoom_optics",
            () -> new CyberwareItem(new Item.Properties(), "zoom_optics", "sensory", "Zoom-Optik: Zoom bis 4x"));

    public static final RegistryObject<Item> EXPANDED_LUNGS = ITEMS.register("expanded_lungs",
            () -> new CyberwareItem(new Item.Properties(), "expanded_lungs", "sensory", "Erweiterte Lungen: Atme unter Wasser"));

    public static final RegistryObject<Item> REINFORCED_LEGS = ITEMS.register("reinforced_legs",
            () -> new CyberwareItem(new Item.Properties(), "reinforced_legs", "sensory", "Verstärkte Beine: Schneller laufen"));

    // ============= ICONIC CYBERWARE (Legendär) =============
    public static final RegistryObject<Item> SMASHER_OVERSEER = ITEMS.register("smasher_overseer",
            () -> new CyberwareItem(new Item.Properties(), "smasher_overseer", "iconic", "Adam Smashers Systemüberwachung"));

    public static final RegistryObject<Item> EDGERUNNER_CUSTOM = ITEMS.register("edgerunner_custom",
            () -> new CyberwareItem(new Item.Properties(), "edgerunner_custom", "iconic", "David Martinez Custom Build"));

    public static final RegistryObject<Item> LUCY_NETRUNNING = ITEMS.register("lucy_netrunning",
            () -> new CyberwareItem(new Item.Properties(), "lucy_netrunning", "iconic", "Lucys Pro-Netrunning Setup"));

    public static final RegistryObject<Item> MAXTAC_COMBAT = ITEMS.register("maxtac_combat",
            () -> new CyberwareItem(new Item.Properties(), "maxtac_combat", "iconic", "MaxTac Combat Package"));

    public static final RegistryObject<Item> CORPO_ELITE = ITEMS.register("corpo_elite",
            () -> new CyberwareItem(new Item.Properties(), "corpo_elite", "iconic", "Corporate Elite Package"));

    public static final RegistryObject<Item> FIXER_PACKAGE = ITEMS.register("fixer_package",
            () -> new CyberwareItem(new Item.Properties(), "fixer_package", "iconic", "Fixer's Complete Package"));

    // ============= CRAFTING MATERIALS =============
    public static final RegistryObject<Item> CYBERWARE_CHIP = ITEMS.register("cyberware_chip",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MILITARY_GRADE_CHIPSET = ITEMS.register("military_grade_chipset",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NEURAL_INTERFACE = ITEMS.register("neural_interface",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> QUICKHACKING_PROTOCOL = ITEMS.register("quickhacking_protocol",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EDGERUNNER_SHARD = ITEMS.register("edgerunner_shard",
            () -> new Item(new Item.Properties().rarity(net.minecraft.world.item.Rarity.RARE)));

    public static final RegistryObject<Item> QUANTUM_PROCESSOR = ITEMS.register("quantum_processor",
            () -> new Item(new Item.Properties().rarity(net.minecraft.world.item.Rarity.EPIC)));

    public static final RegistryObject<Item> TITANIUM_HOUSING = ITEMS.register("titanium_housing",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYNTHETIC_MUSCLE = ITEMS.register("synthetic_muscle",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> OPTICAL_LENS = ITEMS.register("optical_lens",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NEURAL_CONNECTOR = ITEMS.register("neural_connector",
            () -> new Item(new Item.Properties()));

    // ============= SOUL SHARDS (für Conversations) =============
    public static final RegistryObject<Item> LUCY_SOUL_SHARD = ITEMS.register("lucy_soul_shard",
            () -> new Item(new Item.Properties().rarity(net.minecraft.world.item.Rarity.EPIC)));

    public static final RegistryObject<Item> DAVID_SOUL_SHARD = ITEMS.register("david_soul_shard",
            () -> new Item(new Item.Properties().rarity(net.minecraft.world.item.Rarity.EPIC)));

    public static final RegistryObject<Item> SMASHER_SOUL_SHARD = ITEMS.register("smasher_soul_shard",
            () -> new Item(new Item.Properties().rarity(net.minecraft.world.item.Rarity.LEGENDARY)));

    public static final RegistryObject<Item> MAXTAC_SOUL_SHARD = ITEMS.register("maxtac_soul_shard",
            () -> new Item(new Item.Properties().rarity(net.minecraft.world.item.Rarity.RARE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
