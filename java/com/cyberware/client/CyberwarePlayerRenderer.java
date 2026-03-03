package com.cyberware.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import com.cyberware.capabilities.CyberwareCapability;
import com.mojang.blaze3d.vertex.PoseStack;

@Mod.EventBusSubscriber(modid = "cyberware", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CyberwarePlayerRenderer {
    
    /**
     * Rendert Cyberware-Skins auf dem Spieler
     */
    @SubscribeEvent
    public static void onPlayerRender(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        CyberwareCapability.ICyberwareData cyberwareData = CyberwareCapability.getCyberwareData(player);
        
        if(cyberwareData == null) return;
        
        float chrome = cyberwareData.getChrome();
        if(chrome <= 0) return; // Keine Veränderungen wenn keine Chrome
        
        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource bufferSource = event.getMultiBufferSource();
        int packedLight = event.getPackedLight();
        
        // Zeichne Cyberware-Effekte basierend auf Chrome-Level
        renderCyberwareEffects(player, poseStack, bufferSource, packedLight, chrome);
    }
    
    private static void renderCyberwareEffects(Player player, PoseStack poseStack, 
                                               MultiBufferSource bufferSource, int packedLight, 
                                               float chrome) {
        poseStack.pushPose();
        
        // Chrome-Effekt: Metallischer Glanz
        if(chrome > 30) {
            renderMetallicSheen(player, poseStack, chrome);
        }
        
        // Neon-Glow bei hohem Chrome-Level
        if(chrome > 60) {
            renderNeonGlow(player, poseStack, chrome);
        }
        
        // Full Cyborg bei 100%
        if(chrome >= 100) {
            renderFullCyborgMode(player, poseStack, chrome);
        }
        
        poseStack.popPose();
    }
    
    /**
     * Rendert metallischen Glanz-Effekt
     */
    private static void renderMetallicSheen(Player player, PoseStack poseStack, float chrome) {
        // Glanz-Partikel überall auf dem Körper
        float intensity = chrome / 100f;
        
        // Kopf-Glanz
        renderGlassBox(poseStack, -0.3f, 0.5f, -0.3f, 0.3f, 1.0f, 0.3f, 0x888888, intensity);
        
        // Körper-Glanz
        renderGlassBox(poseStack, -0.4f, -0.5f, -0.2f, 0.4f, 0.5f, 0.2f, 0x999999, intensity);
        
        // Arme-Glanz
        renderGlassBox(poseStack, -0.8f, -0.5f, -0.2f, -0.4f, 0.5f, 0.2f, 0xAAAAAA, intensity);
        renderGlassBox(poseStack, 0.4f, -0.5f, -0.2f, 0.8f, 0.5f, 0.2f, 0xAAAAAA, intensity);
    }
    
    /**
     * Rendert Neon-Glow um den Spieler
     */
    private static void renderNeonGlow(Player player, PoseStack poseStack, float chrome) {
        float intensity = (chrome - 60) / 40f; // 0-1 für chrome 60-100
        
        // Wähle Glow-Farbe basierend auf installierter Cyberware
        int glowColor = determineGlowColor(player);
        
        // Wende Glow-Effekt an
        poseStack.pushPose();
        
        // Skaliere leicht für Glow
        float scale = 1.0f + (intensity * 0.1f);
        poseStack.scale(scale, scale, scale);
        
        // Glowing-Effekt (ähnlich wie Betweenlands)
        renderAura(poseStack, glowColor, intensity);
        
        poseStack.popPose();
    }
    
    /**
     * Vollständiger Cyborg-Modus bei 100% Chrome
     */
    private static void renderFullCyborgMode(Player player, PoseStack poseStack, float chrome) {
        // Spieler ist jetzt ein voller Cyborg!
        
        // Augen leuchten
        poseStack.pushPose();
        poseStack.translate(0, 1.7, 0);
        renderEyeGlow(poseStack, 0xFF00FF); // Magenta Augen
        poseStack.popPose();
        
        // Body-Transformation
        poseStack.pushPose();
        
        // Chrome-Aura
        renderCyborgAura(poseStack, 0x00FFFF, 0xFF00FF); // Cyan + Magenta
        
        poseStack.popPose();
    }
    
    /**
     * Bestimme Glow-Farbe basierend auf Cyberware
     */
    private static int determineGlowColor(Player player) {
        CyberwareCapability.ICyberwareData cyber = CyberwareCapability.getCyberwareData(player);
        
        // Basierend auf installierter Cyberware verschiedene Farben
        if(cyber.hasCyberware("operative")) {
            return 0xFF0000; // Rot für Operative
        }
        if(cyber.hasCyberware("netrunning")) {
            return 0x0000FF; // Blau für Netrunning
        }
        if(cyber.hasCyberware("stealth")) {
            return 0x888888; // Grau für Stealth
        }
        if(cyber.hasCyberware("defense")) {
            return 0xCCCCCC; // Silber für Defense
        }
        if(cyber.hasCyberware("sensory")) {
            return 0xFFFF00; // Gelb für Sensory
        }
        
        return 0x00FFFF; // Default Cyan
    }
    
    // ============ HELPER RENDER FUNKTIONEN ============
    
    private static void renderGlassBox(PoseStack poseStack, float x1, float y1, float z1,
                                       float x2, float y2, float z2, int color, float alpha) {
        // Rendert eine durchsichtige Box (Platzhalter für echtes Rendering)
        // In echtem Code würde man hier das BufferBuilder nutzen
    }
    
    private static void renderAura(PoseStack poseStack, int color, float intensity) {
        // Rendert eine leuchtende Aura um den Spieler
        // Würde Partikel oder Shader nutzen
    }
    
    private static void renderEyeGlow(PoseStack poseStack, int color) {
        // Leuchte aus den Augen
        // Zwei kleine leuchtende Punkte
    }
    
    private static void renderCyborgAura(PoseStack poseStack, int color1, int color2) {
        // Doppel-Farb Aura für vollen Cyborg
    }
}

// ============================================================

package com.cyberware.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ComputeFossMetaEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import com.cyberware.capabilities.CyberwareCapability;

@OnlyIn(Dist.CLIENT)
public class CyberwareArmorModel extends HumanoidModel<LivingEntity> {
    
    public CyberwareArmorModel(net.minecraft.client.model.geom.ModelPart modelPart) {
        super(modelPart);
    }
    
    /**
     * Modifiziert den Spieler-Körper basierend auf Chrome-Level
     */
    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, 
                         float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        
        if(entity instanceof AbstractClientPlayer player) {
            CyberwareCapability.ICyberwareData cyber = CyberwareCapability.getCyberwareData(player);
            
            if(cyber == null) return;
            
            float chrome = cyber.getChrome();
            
            // Verändere Körper basierend auf Chrome
            if(chrome > 0) {
                // Arme werden rigider
                this.rightArm.xRot += (chrome / 100f) * 0.1f;
                this.leftArm.xRot += (chrome / 100f) * 0.1f;
                
                // Beine werden schneller
                this.rightLeg.xRot *= (1.0f + chrome / 200f);
                this.leftLeg.xRot *= (1.0f + chrome / 200f);
                
                // Bei hohem Chrome: Körper ist stärker
                if(chrome > 70) {
                    // Breitere Schultern (optisch)
                    this.rightArm.xScale = 1.1f;
                    this.leftArm.xScale = 1.1f;
                }
            }
        }
    }
}

// ============================================================

package com.cyberware.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.world.entity.LivingEntity;
import com.cyberware.entity.CyborgWarriorEntity;

@OnlyIn(Dist.CLIENT)
public class CyborgWarriorRenderer extends HumanoidMobRenderer<CyborgWarriorEntity, CyberwareArmorModel> {
    
    public CyborgWarriorRenderer(EntityRendererProvider.Context context) {
        super(context, new CyberwareArmorModel(context.bakeLayer(
            net.minecraft.client.model.geom.ModelLayers.VILLAGER
        )), 0.5f);
    }
    
    @Override
    public net.minecraft.resources.ResourceLocation getTextureLocation(CyborgWarriorEntity entity) {
        return new net.minecraft.resources.ResourceLocation("cyberware", "textures/entity/cyborg_warrior.png");
    }
}
