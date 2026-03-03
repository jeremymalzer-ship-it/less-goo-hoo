package com.cyberware.block;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import com.cyberware.CyberwareMod;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CyberwareMod.MOD_ID);

    // Ripperdoc Arbeitsstation - für Cyberware-Installation
    public static final RegistryObject<Block> RIPPERDOC_STATION = BLOCKS.register("ripperdoc_station",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CYBERNETIC_ASSEMBLER = BLOCKS.register("cybernetic_assembler",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(2.5f, 5.0f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> QUICKHACKING_TERMINAL = BLOCKS.register("quickhacking_terminal",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(2.0f, 4.0f)
                    .luminaticValue(15)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> NEURAL_CALIBRATION_POD = BLOCKS.register("neural_calibration_pod",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(2.5f, 5.0f)
                    .requiresCorrectToolForDrops()));

    // Cyberware Storage - für Crafting
    public static final RegistryObject<Block> CYBERWARE_STORAGE = BLOCKS.register("cyberware_storage",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(2.0f, 4.0f)
                    .requiresCorrectToolForDrops()));

    // Dekoration - Neon-Blöcke für Cyberpunk-Atmosphäre
    public static final RegistryObject<Block> NEON_BLUE_BLOCK = BLOCKS.register("neon_blue_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(1.5f, 3.0f)
                    .luminaticValue(15)));

    public static final RegistryObject<Block> NEON_PINK_BLOCK = BLOCKS.register("neon_pink_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(1.5f, 3.0f)
                    .luminaticValue(15)));

    public static final RegistryObject<Block> NEON_PURPLE_BLOCK = BLOCKS.register("neon_purple_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(1.5f, 3.0f)
                    .luminaticValue(15)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
