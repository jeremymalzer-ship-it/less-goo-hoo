package com.cyberware.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import com.cyberware.capabilities.CyberwareCapability;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CyberwareSlotScreen extends Screen {
    private static final int SLOT_SIZE = 18;
    private static final int PADDING = 10;
    
    private Player player;
    private CyberwareCapability.ICyberwareData cyberwareData;
    
    private int slotStartX;
    private int slotStartY;
    
    public CyberwareSlotScreen(Player player) {
        super(Component.literal("Cyberware Slots"));
        this.player = player;
        this.cyberwareData = CyberwareCapability.getCyberwareData(player);
    }
    
    @Override
    protected void init() {
        super.init();
        this.slotStartX = this.width / 4;
        this.slotStartY = this.height / 4;
    }
    
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        
        // Title
        guiGraphics.drawCenteredString(this.font, "§c§lCYBERWARE MANAGEMENT SYSTEM", 
                this.width / 2, 20, 0xFFFFFF);
        
        // Chrome Level Bar
        float chrome = cyberwareData.getChrome();
        int chromeColor = chrome < 50 ? 0x00FF00 : chrome < 80 ? 0xFFFF00 : 0xFF0000;
        guiGraphics.fill(this.slotStartX, 40, this.slotStartX + 200, 50, 0x333333);
        guiGraphics.fill(this.slotStartX, 40, this.slotStartX + (int)(200 * chrome / 100f), 50, chromeColor);
        guiGraphics.drawString(this.font, "Chrome: " + String.format("%.1f%%", chrome), 
                this.slotStartX + 5, 42, 0xFFFFFF);
        
        // Zeichne Slot-Kategorien
        int yOffset = 70;
        
        drawSlotCategory(guiGraphics, "§9OPERATIVE WARFARE", "operative", 3, yOffset);
        yOffset += 70;
        
        drawSlotCategory(guiGraphics, "§bNETRUNNING", "netrunning", 2, yOffset);
        yOffset += 60;
        
        drawSlotCategory(guiGraphics, "§5STEALTH SUITE", "stealth", 2, yOffset);
        yOffset += 60;
        
        drawSlotCategory(guiGraphics, "§4DEFENSIVE SYSTEMS", "defense", 2, yOffset);
        yOffset += 60;
        
        drawSlotCategory(guiGraphics, "§6SENSORY ENHANCEMENT", "sensory", 3, yOffset);
        yOffset += 70;
        
        drawSlotCategory(guiGraphics, "§c§lICONIC PACKAGES", "iconic", 1, yOffset);
        
        // Info Text
        guiGraphics.drawString(this.font, "§7Rechtsklick zum Installieren | Linksklick zum Deinstallieren", 
                this.slotStartX, this.height - 30, 0x888888);
        
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
    
    private void drawSlotCategory(GuiGraphics guiGraphics, String category, String slotType, 
                                  int maxSlots, int yStart) {
        guiGraphics.drawString(this.font, category, this.slotStartX, yStart, 0xFFFFFF);
        
        int used = cyberwareData.getUsedSlotsForType(slotType);
        int available = maxSlots;
        
        for(int i = 0; i < maxSlots; i++) {
            int x = this.slotStartX + (i * 25);
            int y = yStart + 20;
            
            boolean isInstalled = i < used;
            int slotColor = isInstalled ? 0x00FF00 : 0x444444;
            
            // Slot Box
            guiGraphics.fill(x, y, x + SLOT_SIZE, y + SLOT_SIZE, slotColor);
            guiGraphics.fill(x + 1, y + 1, x + SLOT_SIZE - 1, y + SLOT_SIZE - 1, 0x222222);
            
            // Info Text
            guiGraphics.drawString(this.font, 
                isInstalled ? "✓" : "-", 
                x + 6, y + 5, isInstalled ? 0x00FF00 : 0x888888);
        }
        
        guiGraphics.drawString(this.font, "§7[" + used + "/" + available + "]", 
                this.slotStartX + (maxSlots * 25) + 10, yStart + 25, 0xAAAAA);
    }
    
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return true;
    }
    
    @Override
    public void onClose() {
        this.minecraft.setScreen(null);
    }
    
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

// ============================================================

package com.cyberware.gui;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.systems.RenderSystem;

@Mod.EventBusSubscriber(modid = "cyberware", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CyberwareScreenHandler {
    
    @SubscribeEvent
    public static void onScreenOpen(ScreenEvent.Init.Post event) {
        // Könnte hier Custom-Screens dynamisch rendern
    }
}
