package dev.kirro.extendedcombat.block;

import dev.kirro.extendedcombat.Config;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.block.custom.BlackAppleBushBlock;
import dev.kirro.extendedcombat.block.custom.FramedGlassPanelBlock;
import dev.kirro.extendedcombat.block.custom.WardingStoneBlock;
import dev.kirro.extendedcombat.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public interface ModBlocks {
    DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ExtendedCombat.MOD_ID);

    private static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    DeferredBlock<Block> NETHER_STEEL_BLOCK = registerBlockWithItem("nether_steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(6f).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)));
    DeferredBlock<Block> ECHO_STEEL_BLOCK = registerBlockWithItem("echo_steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(6f).requiresCorrectToolForDrops().sound(SoundType.SCULK_CATALYST)));

    DeferredBlock<Block> WARDING_STONE = registerBlockWithItem("warding_stone",
            () -> new WardingStoneBlock(BlockBehaviour.Properties.of()
                    .strength(1f).sound(SoundType.DEEPSLATE_BRICKS).noOcclusion().lightLevel(state -> 4)) {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.extendedcombat.warding_stone_ln1"));
                    tooltipComponents.add(Component.translatable("tooltip.extendedcombat.warding_stone_ln2" + Config.wardingStoneActiveRadius + "wsln2E"));
                    tooltipComponents.add(Component.translatable("tooltip.extendedcombat.warding_stone_ln3"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    DeferredBlock<Block> FRAMED_GLASS_PANEL = registerBlockWithItem("framed_glass_panel",
            () -> new FramedGlassPanelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).noOcclusion()));
    DeferredBlock<Block> _STAINED_GLASS_PANEL = registerBlockWithItem("_stained_glass_panel",
            () -> new FramedGlassPanelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).noOcclusion()));
    DeferredBlock<Block> BLACK_APPLE_BUSH = registerBlockWithoutItem("black_apple_bush",
            () -> new BlackAppleBushBlock(BlockBehaviour.Properties.of().strength(0.05f).sound(SoundType.SWEET_BERRY_BUSH).randomTicks().noCollission().pushReaction(PushReaction.DESTROY).noLootTable()));


    static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
