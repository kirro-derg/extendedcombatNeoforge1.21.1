package dev.kirro.extendedcombat.block;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.block.custom.WardingStoneBlock;
import dev.kirro.extendedcombat.item.ModItems;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.model.obj.ObjMaterialLibrary;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ExtendedCombat.MOD_ID);

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static final DeferredBlock<Block> NETHER_STEEL_BLOCK = registerBlock("nether_steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(6f).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> WARDING_STONE = registerBlock("warding_stone",
            () -> new WardingStoneBlock(BlockBehaviour.Properties.of()
                    .strength(1f).sound(SoundType.DEEPSLATE_BRICKS).noOcclusion()));


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
