package dev.kirro.extendedcombat.item.custom;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class AxeSwordItem extends GreatswordItem {
    public AxeSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(BlockTags.MINEABLE_WITH_AXE)
                || state.is(BlockTags.WOOL)
                || state.is(net.minecraft.world.level.block.Blocks.COBWEB);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(Blocks.COBWEB)) {
            return 15.0f;
        }
        if (state.is(BlockTags.MINEABLE_WITH_AXE) || state.is(BlockTags.WOOL)) {
            return this.getTier().getSpeed();
        }
        return super.getDestroySpeed(stack, state);
    }
}
