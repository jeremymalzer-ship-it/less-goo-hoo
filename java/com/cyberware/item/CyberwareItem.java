package com.cyberware.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.UUID;

public class CyberwareItem extends Item {
    private final String cyberwareName;
    private final String description;

    public CyberwareItem(Item.Properties properties, String name, String description) {
        super(properties.stacksTo(1));
        this.cyberwareName = name;
        this.description = description;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        
        if (!level.isClientSide && player.isShiftKeyDown()) {
            activateCyberware(player, itemStack);
            return InteractionResultHolder.success(itemStack);
        }
        
        return InteractionResultHolder.pass(itemStack);
    }

    private void activateCyberware(Player player, ItemStack itemStack) {
        switch(this.cyberwareName) {
            case "sandevistan":
                activateSandevistan(player);
                break;
            case "berserk":
                activateBerserk(player);
                break;
            case "legendary_quickhacking":
                activateQuickhacking(player);
                break;
            case "mantis_blades":
                activateMantisBlades(player);
                break;
            case "gorilla_arms":
                activateGorillaArms(player);
                break;
            case "ping":
                activatePing(player);
                break;
            case "optical_camo":
                activateOpticalCamo(player);
                break;
            case "chrome_body":
                activateChromeBody(player);
                break;
        }
    }

    private void activateSandevistan(Player player) {
        // Zeit-Verlangsamung - alle anderen Entities werden verlangsamt
        player.sendSystemMessage(Component.literal("§c[SANDEVISTAN AKTIV] §7Zeit wird verlangsamt..."));
        
        // Slowness für alle Mobs in der Nähe (40 Blöcke)
        player.level().getEntities(player, player.getBoundingBox().inflate(40))
                .forEach(entity -> {
                    if(entity != player && entity instanceof net.minecraft.world.entity.LivingEntity living) {
                        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 3));
                    }
                });

        // Speed für den Spieler
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1));
    }

    private void activateBerserk(Player player) {
        player.sendSystemMessage(Component.literal("§4[BERSERK MODE AKTIVIERT] §cKAMPFMODUS AKTIV!"));
        
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 3));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 2));
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 1));
        
        // Mobs in der Nähe werden angegriffen
        player.level().getEntities(player, player.getBoundingBox().inflate(50))
                .forEach(entity -> {
                    if(entity instanceof net.minecraft.world.entity.Mob mob && !mob.isAlliedTo(player)) {
                        mob.setTarget(player);
                    }
                });
    }

    private void activateQuickhacking(Player player) {
        player.sendSystemMessage(Component.literal("§b[QUICKHACKING] §3Netrunning-Protokoll gestartet..."));
        
        // Hacked alle Mobs in 60 Blöcken
        player.level().getEntities(player, player.getBoundingBox().inflate(60))
                .forEach(entity -> {
                    if(entity instanceof net.minecraft.world.entity.Mob mob && !mob.isAlliedTo(player)) {
                        mob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 1));
                        // Mob wird zum Spieler gezogen
                        mob.setTarget(null);
                    }
                });
        
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0));
    }

    private void activateMantisBlades(Player player) {
        player.sendSystemMessage(Component.literal("§e[MANTIS BLADES] §6Nahkampf-System aktiviert!"));
        
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 250, 4));
        
        // Gibt dem Spieler temporären Schärfen-Effekt auf Angriffe
        CompoundTag tag = player.getPersistentData();
        tag.putBoolean("mantis_blades_active", true);
        tag.putInt("mantis_timer", 250);
    }

    private void activateGorillaArms(Player player) {
        player.sendSystemMessage(Component.literal("§d[GORILLA ARME] §5Kraft freigesetzt!"));
        
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 3));
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 2));
        
        // Sprung-Boost
        player.setDeltaMovement(player.getDeltaMovement().add(0, 0.5, 0));
    }

    private void activatePing(Player player) {
        player.sendSystemMessage(Component.literal("§9[PING] §1Netzwerk-Scanner aktiv..."));
        
        // Alle Mobs sichtbar machen (Glowing Effect)
        player.level().getEntities(player, player.getBoundingBox().inflate(80))
                .forEach(entity -> {
                    if(entity instanceof net.minecraft.world.entity.Mob) {
                        entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 150, 0));
                    }
                });
        
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 150, 0));
    }

    private void activateOpticalCamo(Player player) {
        player.sendSystemMessage(Component.literal("§8[OPTISCHE TARNUNG] §7Unsichtbarkeit aktiviert..."));
        
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 180, 0));
        
        // Knockback Resistance während Invisibility
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 180, 0));
    }

    private void activateChromeBody(Player player) {
        player.sendSystemMessage(Component.literal("§6[CHROME BODY] §eHyper-Upgrade aktiv!"));
        
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1));
        player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 2));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 3));
    }

    @Override
    public String getDescriptionId() {
        return super.getDescriptionId();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public net.minecraft.world.item.TooltipFlag.Default getTooltipImage() {
        return super.getTooltipImage();
    }
}
